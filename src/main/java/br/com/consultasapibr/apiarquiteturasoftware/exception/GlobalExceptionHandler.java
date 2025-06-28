package br.com.consultasapibr.apiarquiteturasoftware.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@ControllerAdvice
public class GlobalExceptionHandler {
    private static final String ERRO_PREFIXO_JSON = "{\"erro\":\"";
    private static final String ERRO_SUFIXO_JSON = "\"}";

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<String> handleBadRequest(BadRequestException e) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ERRO_PREFIXO_JSON + e.getMessage() + ERRO_SUFIXO_JSON);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> handleNotFound(ResourceNotFoundException e) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ERRO_PREFIXO_JSON + e.getMessage() + ERRO_SUFIXO_JSON);
    }

    @ExceptionHandler(ExternalApiException.class)
    public ResponseEntity<String> handleExternalApi(ExternalApiException e) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ERRO_PREFIXO_JSON + e.getMessage() + ERRO_SUFIXO_JSON);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("{\"erro\":\"Erro interno do servidor.\"}");
    }
}
