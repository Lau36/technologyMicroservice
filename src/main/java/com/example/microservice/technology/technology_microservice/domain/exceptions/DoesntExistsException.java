package com.example.microservice.technology.technology_microservice.domain.exceptions;

public class DoesntExistsException extends RuntimeException {
    public DoesntExistsException(String message) {
        super(message);
    }
}
