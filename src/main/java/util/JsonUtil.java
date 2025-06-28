package util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;

import br.com.consultasapibr.apiarquiteturasoftware.dto.FornecedorDTO;
import br.com.consultasapibr.apiarquiteturasoftware.exception.ExternalApiException;
import br.com.consultasapibr.apiarquiteturasoftware.model.Fornecedor;

import java.io.IOException;
import java.io.OutputStream;

public class JsonUtil {

    private JsonUtil() {
        throw new UnsupportedOperationException("Classe utilitária não pode ser instanciada");
    }

    public static FornecedorDTO converterParaDTO(String json) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(json, FornecedorDTO.class);
        } catch (IOException e) {
            throw new ExternalApiException("Erro ao converter JSON para DTO", e);
        }
    }

    public static String converterParaJson(Fornecedor f) {
        return String.format(
                "{ \"cnpj\":\"%s\", \"razao_social\":\"%s\", \"nome_fantasia\":\"%s\", " +
                        "\"logradouro\":\"%s\", \"municipio\":\"%s\", \"uf\":\"%s\" }",
                f.getCnpj(), f.getRazaoSocial(), f.getNomeFantasia(),
                f.getLogradouro(), f.getMunicipio(), f.getUf());
    }

    public static String converterListaParaJson(Iterable<Fornecedor> lista) {
        StringBuilder sb = new StringBuilder("[");
        for (Fornecedor f : lista) {
            sb.append(converterParaJson(f)).append(",");
        }
        if (sb.length() > 1)
            sb.setLength(sb.length() - 1);
        return sb.append("]").toString();
    }

    public static void responderJson(HttpExchange ex, int status, String body) throws IOException {
        ex.getResponseHeaders().set("Content-Type", "application/json");
        ex.sendResponseHeaders(status, body.getBytes().length);
        OutputStream os = ex.getResponseBody();
        os.write(body.getBytes());
        os.close();
    }
}
