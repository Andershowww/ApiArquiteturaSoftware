package br.com.consultasapibr;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ApiArquiteturaSoftwareApplication {
    private static final Logger logger = LoggerFactory.getLogger(ApiArquiteturaSoftwareApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(ApiArquiteturaSoftwareApplication.class, args);
        logger.info("Servidor Spring Boot iniciado com sucesso!");
        logger.info("Acesse: http://localhost:8080/fornecedores");
    }
}