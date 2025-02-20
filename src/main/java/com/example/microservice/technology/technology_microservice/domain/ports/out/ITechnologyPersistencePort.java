package com.example.microservice.technology.technology_microservice.domain.ports.out;

import com.example.microservice.technology.technology_microservice.domain.model.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface ITechnologyPersistencePort {
    Mono<Void> saveTechnology(TechnologyModel technologyModel);
    Mono<Boolean> existTechnologyByName(String technologyName);
    Mono<PaginatedTechnologiesModel> getAllTechnologies(PaginationModel paginationModel);
    Mono<Boolean> existTechnologiesByIds(List<Long> technologiesId);
    Mono<Void> saveAll(List<TechnologyCapacityModel> technologiesCapacityModelList);
    Flux<TechnologyWithNameModel> getAllTechnologiesByCapacityId(Long capacityId);
}
