package br.com.consultasapibr.apiarquiteturasoftware.exception;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
