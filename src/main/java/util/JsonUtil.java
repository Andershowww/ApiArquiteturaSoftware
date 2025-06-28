package util;

import com.sun.net.httpserver.HttpExchange;
import br.com.consultasapibr.apiarquiteturasoftware.dto.FornecedorDTO;
import br.com.consultasapibr.apiarquiteturasoftware.model.Fornecedor;

import java.io.IOException;
import java.io.OutputStream;

public class JsonUtil {

    private JsonUtil() {
        throw new UnsupportedOperationException("Classe utilitária não pode ser instanciada");
    }

    public static FornecedorDTO converterParaDTO(String json) {
        FornecedorDTO dto = new FornecedorDTO();
        // Remover espaços, tabulações, quebras de linha e ajustar o texto
        json = json.replaceAll("\\s+", "");

        // Expressão robusta para capturar o CNPJ
        if (json.matches(".*\"cnpj\":\"\\d{14}\".*")) {
            dto.setCnpj(json.replaceAll(".*\"cnpj\":\"(\\d{14})\".*", "$1"));

        } else {
            dto.setCnpj(null);
        }

        return dto;
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
