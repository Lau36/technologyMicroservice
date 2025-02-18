package com.example.microservice.technology.technology_microservice.infrastructure.exceptionHandler;

import com.example.microservice.technology.technology_microservice.domain.exceptions.AlreadyExistsException;
import com.example.microservice.technology.technology_microservice.domain.exceptions.DescriptionTooLongException;
import com.example.microservice.technology.technology_microservice.domain.exceptions.NameTooLongException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import reactor.core.publisher.Mono;

@ControllerAdvice
public class ControllerAdvisor {
    @ExceptionHandler(NameTooLongException.class)
    public Mono<ResponseEntity<String>> handleNameTooLong(NameTooLongException ex) {
        return Mono.just(ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage()));
    }

    @ExceptionHandler(DescriptionTooLongException.class)
    public Mono<ResponseEntity<String>> handleDescriptionTooLong(DescriptionTooLongException ex) {
        return Mono.just(ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage()));
    }

    @ExceptionHandler(AlreadyExistsException.class)
    public Mono<ResponseEntity<String>> handleNameAlreadyExist(AlreadyExistsException ex) {
        return Mono.just(ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage()));
    }
}
