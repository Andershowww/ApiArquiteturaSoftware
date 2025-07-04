package br.com.consultasapibr.apiarquiteturasoftware.service;

import br.com.consultasapibr.apiarquiteturasoftware.model.EnderecoFornecedor;
import br.com.consultasapibr.apiarquiteturasoftware.model.Fornecedor;
import br.com.consultasapibr.apiarquiteturasoftware.model.UF;
import br.com.consultasapibr.apiarquiteturasoftware.repository.IEnderecoFornecedorRepository;
import br.com.consultasapibr.apiarquiteturasoftware.repository.IFornecedorRepository;
import br.com.consultasapibr.apiarquiteturasoftware.repository.IUFRepository;
import br.com.consultasapibr.apiarquiteturasoftware.dto.EnderecoFornecedorDTO;
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

    private final IFornecedorRepository fornecedorRepository;
    private final IUFRepository ufRepository;
    private final HttpClient client;
    private final ObjectMapper objectMapper;
    private final IEnderecoFornecedorRepository enderecoRepository;

    public FornecedorService(IFornecedorRepository fornecedorRepository, IUFRepository ufRepository,
            IEnderecoFornecedorRepository enderecoRepository) {
        this.fornecedorRepository = fornecedorRepository;
        this.ufRepository = ufRepository;
        this.enderecoRepository = enderecoRepository;
        this.client = HttpClient.newHttpClient();
        this.objectMapper = new ObjectMapper();
    }

    private String limparCnpj(String cnpj) {
        if (cnpj == null)
            return null;
        return cnpj.replaceAll("[^\\d]", "");
    }

    public Fornecedor cadastrarFornecedor(FornecedorConsultaApiDTO dto) {
        String cnpjLimpo = limparCnpj(dto.getCnpj());
        if (!validarCNPJ(cnpjLimpo)) {
            throw new BadRequestException("CNPJ inválido");
        }

        UF entidadeUf = ufRepository.findByufSigla(dto.getEndereco().getUf())
                .orElseThrow(() -> new ResourceNotFoundException("UF não encontrada"));

        Fornecedor fornecedor = new Fornecedor(
                cnpjLimpo,
                dto.getRazaoSocial(),
                dto.getNomeFantasia(),
                dto.getCnae()!= null ? dto.getCnae() : "");
        try {
            fornecedor = fornecedorRepository.save(fornecedor);

            EnderecoFornecedor endereco = new EnderecoFornecedor(
                    fornecedor,
                    entidadeUf,
                    dto.getEndereco());
            enderecoRepository.save(endereco);
           
            return fornecedor;
        } catch (DataIntegrityViolationException e) {
            throw new BadRequestException("Fornecedor com este CNPJ já existe.");
        } catch (Exception e) {
            throw new ExternalApiException("Erro ao salvar fornecedor no banco de dados", e);
        }
    }

    public FornecedorConsultaApiDTO buscaCnpj(String cnpj) {

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

            EnderecoFornecedorDTO endereco = new EnderecoFornecedorDTO();
            endereco.setLogradouro(root.path("logradouro").asText());
            endereco.setNumero(root.path("numero").asText(""));
            endereco.setComplemento(root.path("complemento").asText(""));
            endereco.setBairro(root.path("bairro").asText(""));
            endereco.setMunicipio(root.path("municipio").asText());
            endereco.setUf(root.path("uf").asText());
            endereco.setCep(root.path("cep").asText());
            return new FornecedorConsultaApiDTO(
                    cnpj,
                    root.path("razao_social").asText(),
                    root.path("nome_fantasia").asText(),
                    root.path("cnae").asText(),
                    endereco);

        } catch (IOException | InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new ExternalApiException("Erro ao consultar CNPJ na API externa", e);
        }
    }

    public List<FornecedorConsultaApiDTO> listarTodos() {
        List<Fornecedor> fornecedores = fornecedorRepository.findAll();

        return fornecedores.stream().map(fornecedor -> {
            EnderecoFornecedor endereco = enderecoRepository
                    .findByFornecedor(fornecedor)
                    .orElse(null); // ou lançar exceção se obrigatório
            return new FornecedorConsultaApiDTO(fornecedor, endereco);
        }).toList();
    }

    private boolean validarCNPJ(String cnpj) {
        return cnpj != null && cnpj.matches("\\d{14}");
    }

}