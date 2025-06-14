package controller;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import dto.FornecedorDTO;
import model.Fornecedor;
import service.FornecedorService;
import util.JsonUtil;

import java.io.IOException;
import java.util.Scanner;

public class FornecedorController implements HttpHandler {
    private final FornecedorService service = new FornecedorService();

    @Override
    public void handle(HttpExchange ex) throws IOException {
        String metodo = ex.getRequestMethod();
        if ("POST".equalsIgnoreCase(metodo)) {
            Scanner sc = new Scanner(ex.getRequestBody()).useDelimiter("\\A");
            String body = sc.hasNext() ? sc.next() : "";
              sc.close();          
            try {
                FornecedorDTO dto = JsonUtil.converterParaDTO(body);
                Fornecedor fornecedor = service.cadastrarFornecedor(dto.cnpj);
                JsonUtil.responderJson(ex, 201, JsonUtil.converterParaJson(fornecedor));
            } catch (Exception e) {
                JsonUtil.responderJson(ex, 400, "{\"erro\":\"" + e.getMessage() + "\"}");
            }

        } else if ("GET".equalsIgnoreCase(metodo)) {
            String json = JsonUtil.converterListaParaJson(service.listarTodos());
            JsonUtil.responderJson(ex, 200, json);

        } else {
            JsonUtil.responderJson(ex, 405, "{\"erro\":\"Método não permitido\"}");
        }
    }
}
