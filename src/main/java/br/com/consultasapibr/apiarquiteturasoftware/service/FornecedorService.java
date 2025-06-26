package br.com.consultasapibr.apiarquiteturasoftware.service;

import br.com.consultasapibr.apiarquiteturasoftware.model.Fornecedor;
import br.com.consultasapibr.apiarquiteturasoftware.model.UF;
import br.com.consultasapibr.apiarquiteturasoftware.repository.FornecedorRepository;
import br.com.consultasapibr.apiarquiteturasoftware.repository.UFRepository;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;


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

    public Fornecedor cadastrarFornecedor(String cnpj) throws Exception {
        if (!validarCNPJ(cnpj)) {
            throw new IllegalArgumentException("CNPJ inválido");
        }

        String url = "https://brasilapi.com.br/api/cnpj/v1/" + cnpj;
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).GET().build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() != 200 || response.body().contains("\"message\"")) {
            throw new IllegalArgumentException("CNPJ não encontrado");
        }

        JsonNode root = objectMapper.readTree(response.body());
        UF entidadeUf = ufRepository.findByUf(root.path("uf").asText())
                .orElseThrow(() -> new IllegalArgumentException("UF não encontrada"));

        Fornecedor fornecedor = new Fornecedor(
                cnpj,
                root.path("razao_social").asText(),
                root.path("nome_fantasia").asText(),
                root.path("logradouro").asText(),
                root.path("municipio").asText(),
                entidadeUf
        );

        return fornecedorRepository.save(fornecedor); // 🔹 salvando no banco
    }

    public List<Fornecedor> listarTodos() {
        return fornecedorRepository.findAll(); // 🔹 buscando do banco
    }

    private boolean validarCNPJ(String cnpj) {
        return cnpj != null && cnpj.matches("\\d{14}");
    }
}

