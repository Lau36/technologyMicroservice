package com.example.microservice.technology.technology_microservice.domain.exceptions;

public class NameTooLongException extends RuntimeException {
    public NameTooLongException(String message) {
        super(message);
    }
}
