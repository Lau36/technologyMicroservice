package com.example.microservice.technology.technology_microservice.domain.ports.out;

import com.example.microservice.technology.technology_microservice.domain.model.TechnologyModel;
import reactor.core.publisher.Mono;

public interface ITechnologyPersistencePort {
    Mono<Void> saveTechnology(TechnologyModel technologyModel);
    Mono<Boolean> existTechnologyByName(String technologyName);
}
