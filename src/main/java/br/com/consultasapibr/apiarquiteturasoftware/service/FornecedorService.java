package br.com.consultasapibr.apiarquiteturasoftware.service;

import br.com.consultasapibr.apiarquiteturasoftware.dto.EnderecoFornecedorDTO;
import br.com.consultasapibr.apiarquiteturasoftware.dto.FornecedorConsultaApiDTO;
import br.com.consultasapibr.apiarquiteturasoftware.exception.BadRequestException;
import br.com.consultasapibr.apiarquiteturasoftware.exception.ExternalApiException;
import br.com.consultasapibr.apiarquiteturasoftware.model.EnderecoFornecedor;
import br.com.consultasapibr.apiarquiteturasoftware.model.Fornecedor;
import br.com.consultasapibr.apiarquiteturasoftware.model.Uf;
import br.com.consultasapibr.apiarquiteturasoftware.repository.FornecedorRepository;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

@Service
public class FornecedorService {

    private final FornecedorRepository fornecedorRepository;
    private final EnderecoFornecedorService enderecoFornecedorService;
    private final UfService ufService;
    private final HttpClient httpClient;
    private final ObjectMapper objectMapper;

    public FornecedorService(FornecedorRepository fornecedorRepository,
    EnderecoFornecedorService enderecoFornecedorService,
                             UfService ufService) {
        this.fornecedorRepository = fornecedorRepository;
        this.enderecoFornecedorService = enderecoFornecedorService;
        this.ufService = ufService;
        this.httpClient = HttpClient.newHttpClient();
        this.objectMapper = new ObjectMapper();
    }

    public Fornecedor cadastrarFornecedor(FornecedorConsultaApiDTO dto) {
        String cnpj = limparCnpj(dto.getCnpj());

        if (!validarCNPJ(cnpj)) {
            throw new BadRequestException("CNPJ inválido");
        }

        if (fornecedorRepository.existsByCnpj(cnpj)) {
            throw new BadRequestException("Fornecedor com este CNPJ já existe.");
        }

        Uf uf = ufService.buscarPorSigla(dto.getEndereco().getUf());

        Fornecedor fornecedor = new Fornecedor(
                cnpj,
                dto.getRazaoSocial(),
                dto.getNomeFantasia(),
                dto.getCnae() != null ? dto.getCnae() : ""
        );

        try {
            fornecedor = fornecedorRepository.save(fornecedor);

            EnderecoFornecedor endereco = new EnderecoFornecedor(
                    fornecedor,
                    uf,
                    dto.getEndereco()
            );
            enderecoFornecedorService.salvar(endereco);

            return fornecedor;
        } catch (Exception e) {
            throw new ExternalApiException("Erro ao salvar fornecedor no banco de dados", e);
        }
    }

    public FornecedorConsultaApiDTO buscaCnpj(String cnpj) {
        String cnpjLimpo = limparCnpj(cnpj);
        if (!validarCNPJ(cnpjLimpo)) {
            throw new BadRequestException("CNPJ inválido");
        }

        try {
            String url = "https://brasilapi.com.br/api/cnpj/v1/" + cnpjLimpo;
            HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).GET().build();
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

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
                cnpjLimpo,
                    root.path("razao_social").asText(),
                    root.path("nome_fantasia").asText(),
                    root.path("cnae").asText(),
                    endereco
            );

        } catch (IOException | InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new ExternalApiException("Erro ao consultar CNPJ na API externa", e);
        }
    }

    public List<FornecedorConsultaApiDTO> listarTodos() {
        return fornecedorRepository.findAll()
                .stream()
                .map(fornecedor -> {
                    EnderecoFornecedor endereco = enderecoFornecedorService.buscarPorFornecedor(fornecedor)
                            .orElse(null);
                    return new FornecedorConsultaApiDTO(fornecedor, endereco);
                })
                .toList();
    }

    private String limparCnpj(String cnpj) {
        return cnpj != null ? cnpj.replaceAll("[^\\d]", "") : null;
    }

    private boolean validarCNPJ(String cnpj) {
        return cnpj != null && cnpj.matches("\\d{14}");
    }
}
