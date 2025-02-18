package com.example.microservice.technology.technology_microservice.application.handler;

import com.example.microservice.technology.technology_microservice.application.dto.request.TechnologyRequest;
import reactor.core.publisher.Mono;

public interface ITechnologyRestHandler {
    Mono<Void> createTechnology(TechnologyRequest technologyRequest);
}
