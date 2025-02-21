package com.example.microservice.technology.technology_microservice.application.handler;

import com.example.microservice.technology.technology_microservice.application.dto.request.TechnologiesCapacityRequest;
import com.example.microservice.technology.technology_microservice.application.dto.request.TechnologyRequest;
import com.example.microservice.technology.technology_microservice.domain.utils.PaginatedTechnologies;
import com.example.microservice.technology.technology_microservice.domain.utils.Pagination;
import com.example.microservice.technology.technology_microservice.domain.utils.TechnologyWithIdAndName;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface ITechnologyRestHandler {
    Mono<Void> createTechnology(TechnologyRequest technologyRequest);
    Mono<PaginatedTechnologies> getTechnologies(Pagination pagination);
    Mono<Boolean> existTechnologiesByIds(List<Long> technologiesId);
    Mono<Void> associateTechnologiesAndCapacities(TechnologiesCapacityRequest technologiesCapacityRequest);
    Flux<TechnologyWithIdAndName> getAllTechnologiesByCapacityId(Long capacityId);
}
