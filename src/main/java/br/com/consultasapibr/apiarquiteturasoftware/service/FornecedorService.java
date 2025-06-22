package br.com.consultasapibr.apiarquiteturasoftware.service;

import br.com.consultasapibr.apiarquiteturasoftware.model.Fornecedor;
import br.com.consultasapibr.apiarquiteturasoftware.repository.FornecedorRepository;
// import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class FornecedorService {

    private final FornecedorRepository repo;
    private final HttpClient client;

    public FornecedorService(FornecedorRepository repo) {
        this.repo = repo;
        this.client = HttpClient.newHttpClient();
    }

    public Fornecedor cadastrarFornecedor(String cnpj) throws Exception {
        if (!validarCNPJ(cnpj)) {
            throw new IllegalArgumentException("CNPJ inválido");
        }

        String url = "https://brasilapi.com.br/api/cnpj/v1/" + cnpj;

        HttpRequest req = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();

        HttpResponse<String> res = client.send(req, HttpResponse.BodyHandlers.ofString());
        String json = res.body();

        if (json.contains("\"message\"")) {
            throw new IllegalArgumentException("CNPJ não encontrado");
        }

        // Parse simplificado (sem Gson)
        String razao = extrair(json, "razao_social");
        String fantasia = extrair(json, "nome_fantasia");
        String logradouro = extrair(json, "logradouro");
        String municipio = extrair(json, "municipio");
        String uf = extrair(json, "uf");

        Fornecedor f = new Fornecedor(cnpj, razao, fantasia, logradouro, municipio, uf);
        repo.salvar(f); // ou repo.save(f) se for Spring Data JPA
        return f;
    }

    public Iterable<Fornecedor> listarTodos() {
        return repo.listarTodos(); // ou repo.findAll() se for Spring Data
    }

    private String extrair(String json, String campo) {
        Pattern p = Pattern.compile("\"" + campo + "\"\\s*:\\s*\"([^\"]*)\"");
        Matcher m = p.matcher(json);
        return m.find() ? m.group(1) : "";
    }

    private boolean validarCNPJ(String cnpj) {
        return cnpj != null && cnpj.matches("\\d{14}");
    }
}
