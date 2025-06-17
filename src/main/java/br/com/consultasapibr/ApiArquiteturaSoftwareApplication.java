package br.com.consultasapibr.apiarquiteturasoftware;

import com.sun.net.httpserver.HttpServer;
import br.com.consultasapibr.apiarquiteturasoftware.controller.FornecedorController;

import java.net.InetSocketAddress;

public class ApiArquiteturaSoftwareApplication {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
        server.createContext("/fornecedores", new FornecedorController());
        server.setExecutor(null);
        server.start();
        System.out.println("Servidor iniciado em http://localhost:8080/fornecedores");
    }
}
