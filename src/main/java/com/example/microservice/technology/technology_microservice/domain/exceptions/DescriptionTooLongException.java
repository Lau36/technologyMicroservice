package com.example.microservice.technology.technology_microservice.domain.exceptions;

public class DescriptionTooLongException extends RuntimeException {
    public DescriptionTooLongException(String message) {
        super(message);
    }
}
