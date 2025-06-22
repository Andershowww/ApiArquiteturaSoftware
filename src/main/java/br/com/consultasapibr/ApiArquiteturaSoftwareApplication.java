package br.com.consultasapibr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })

public class ApiArquiteturaSoftwareApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiArquiteturaSoftwareApplication.class, args);
        System.out.println("✅ Servidor Spring Boot iniciado com sucesso!");
        System.out.println("🔗 Acesse: http://localhost:8080/fornecedores");
    }
}