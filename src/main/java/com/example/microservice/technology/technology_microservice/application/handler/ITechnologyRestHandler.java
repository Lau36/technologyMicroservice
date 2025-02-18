package com.example.microservice.technology.technology_microservice.application.handler;

import com.example.microservice.technology.technology_microservice.application.dto.request.TechnologyRequest;
import com.example.microservice.technology.technology_microservice.domain.model.PaginatedTechnologiesModel;
import com.example.microservice.technology.technology_microservice.domain.model.PaginationModel;
import reactor.core.publisher.Mono;

public interface ITechnologyRestHandler {
    Mono<Void> createTechnology(TechnologyRequest technologyRequest);
    Mono<PaginatedTechnologiesModel> getTechnologies(PaginationModel paginationModel);
}
