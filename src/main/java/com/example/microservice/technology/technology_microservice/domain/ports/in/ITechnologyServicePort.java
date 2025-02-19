package com.example.microservice.technology.technology_microservice.domain.ports.in;

import com.example.microservice.technology.technology_microservice.domain.model.PaginatedTechnologiesModel;
import com.example.microservice.technology.technology_microservice.domain.model.PaginationModel;
import com.example.microservice.technology.technology_microservice.domain.model.TechnologiesCapacityModel;
import com.example.microservice.technology.technology_microservice.domain.model.TechnologyModel;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface ITechnologyServicePort {
    Mono<Void> createTechnology(TechnologyModel technologyModel);
    Mono<PaginatedTechnologiesModel> listTechnologies(PaginationModel paginationModel);
    Mono<Boolean> existTechnologiesByIds(List<Long> technologiesId);
    Mono<Void> associateTechnologiesAndCapacities(TechnologiesCapacityModel technologiesCapacityModel);
}
