package br.com.consultasapibr.apiarquiteturasoftware.service;

import br.com.consultasapibr.apiarquiteturasoftware.model.Fornecedor;
import br.com.consultasapibr.apiarquiteturasoftware.model.UF;
import br.com.consultasapibr.apiarquiteturasoftware.repository.FornecedorRepository;
import br.com.consultasapibr.apiarquiteturasoftware.repository.UFRepository;
import br.com.consultasapibr.apiarquiteturasoftware.dto.FornecedorConsultaApiDTO;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import br.com.consultasapibr.apiarquiteturasoftware.exception.ResourceNotFoundException;
import br.com.consultasapibr.apiarquiteturasoftware.exception.BadRequestException;
import br.com.consultasapibr.apiarquiteturasoftware.exception.ExternalApiException;

@Service
public class FornecedorService {

    private final FornecedorRepository fornecedorRepository;
    private final UFRepository ufRepository;
    private final HttpClient client;
    private final ObjectMapper objectMapper;

    public FornecedorService(FornecedorRepository fornecedorRepository, UFRepository ufRepository) {
        this.fornecedorRepository = fornecedorRepository;
        this.ufRepository = ufRepository;
        this.client = HttpClient.newHttpClient();
        this.objectMapper = new ObjectMapper();
    }

    public Fornecedor cadastrarFornecedor(FornecedorConsultaApiDTO dto) {
        if (!validarCNPJ(dto.getCnpj())) {
            throw new BadRequestException("CNPJ inválido");
        }

        UF entidadeUf = ufRepository.findByufSigla(dto.getUF())
                .orElseThrow(() -> new ResourceNotFoundException("UF não encontrada"));

        Fornecedor fornecedor = new Fornecedor(
                dto.getCnpj(),
                dto.getRazaoSocial(),
                dto.getNomeFantasia(),
                dto.getLogradouro(),
                dto.getMunicipio(),
                entidadeUf);
        try {
            return fornecedorRepository.save(fornecedor);
        } catch (DataIntegrityViolationException e) {
            // Erro típico: cnpj já cadastrado (duplicado)
            throw new BadRequestException("Fornecedor com este CNPJ já existe.");
        } catch (Exception e) {
            throw new ExternalApiException("Erro ao salvar fornecedor no banco de dados", e);
        }
    }

    public FornecedorConsultaApiDTO buscaCep(String cnpj) {

        if (!validarCNPJ(cnpj)) {
            throw new BadRequestException("CNPJ inválido");
        }

        try {
            String url = "https://brasilapi.com.br/api/cnpj/v1/" + cnpj;
            HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).GET().build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() != 200 || response.body().contains("\"message\"")) {
                throw new BadRequestException("CNPJ não encontrado");
            }
            JsonNode root = objectMapper.readTree(response.body());

            return new FornecedorConsultaApiDTO(
                    cnpj,
                    root.path("razao_social").asText(),
                    root.path("nome_fantasia").asText(),
                    root.path("logradouro").asText(),
                    root.path("municipio").asText(),
                    root.path("uf").asText());

        } catch (IOException | InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new ExternalApiException("Erro ao consultar CNPJ na API externa", e);
        }
    }

    public List<Fornecedor> listarTodos() {
        return fornecedorRepository.findAll();
    }

    private boolean validarCNPJ(String cnpj) {
        return cnpj != null && cnpj.matches("\\d{14}");
    }
}