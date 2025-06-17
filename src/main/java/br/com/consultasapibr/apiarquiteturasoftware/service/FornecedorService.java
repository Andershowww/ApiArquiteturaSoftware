package br.com.consultasapibr.apiarquiteturasoftware.service;

import br.com.consultasapibr.apiarquiteturasoftware.model.Fornecedor;
import br.com.consultasapibr.apiarquiteturasoftware.repository.FornecedorRepository;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.regex.*;

public class FornecedorService {
    private final FornecedorRepository repo = new FornecedorRepository();

    public Fornecedor cadastrarFornecedor(String cnpj) throws Exception {
        if (!validarCNPJ(cnpj))
            throw new IllegalArgumentException("CNPJ inválido");

        String url = "https://brasilapi.com.br/api/cnpj/v1/" + cnpj;
        HttpClient client = HttpClient.newHttpClient();

        HttpRequest req = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();

        HttpResponse<String> res = client.send(req, HttpResponse.BodyHandlers.ofString());
        String json = res.body();

        if (json.contains("\"message\""))
            throw new IllegalArgumentException("CNPJ não encontrado");

        // Parse simplificado (sem Gson)
        String razao = extrair(json, "razao_social");
        String fantasia = extrair(json, "nome_fantasia");
        String logradouro = extrair(json, "logradouro");
        String municipio = extrair(json, "municipio");
        String uf = extrair(json, "uf");

        Fornecedor f = new Fornecedor(cnpj, razao, fantasia, logradouro, municipio, uf);
        repo.salvar(f);
        return f;
    }

    public Iterable<Fornecedor> listarTodos() {
        return repo.listarTodos();
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
