package br.com.gestaoproducao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class gestaoproducao {
    private static final Logger logger = LoggerFactory.getLogger(gestaoproducao.class);

    public static void main(String[] args) {
        SpringApplication.run(gestaoproducao.class, args);
        logger.info("Servidor Spring Boot iniciado com sucesso!");
        logger.info("Inicie as chamadas aos endpoints desejados");
        logger.info("Se preferir, use o swagger: \"http://localhost:8080/swagger-ui.html\"");
    }
}