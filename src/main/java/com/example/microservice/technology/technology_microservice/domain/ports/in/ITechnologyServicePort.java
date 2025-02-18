package com.example.microservice.technology.technology_microservice.domain.ports.in;

import com.example.microservice.technology.technology_microservice.domain.model.TechnologyModel;
import reactor.core.publisher.Mono;

public interface ITechnologyServicePort {
    Mono<Void> createTechnology(TechnologyModel technologyModel);
}
