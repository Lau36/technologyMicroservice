package com.example.microservice.technology.technology_microservice.domain.ports.out;

import com.example.microservice.technology.technology_microservice.domain.model.*;
import com.example.microservice.technology.technology_microservice.domain.utils.PaginatedTechnologies;
import com.example.microservice.technology.technology_microservice.domain.utils.Pagination;
import com.example.microservice.technology.technology_microservice.domain.utils.TechnologyCapacity;
import com.example.microservice.technology.technology_microservice.domain.utils.TechnologyWithIdAndName;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface ITechnologyPersistencePort {
    Mono<Void> saveTechnology(TechnologyModel technologyModel);
    Mono<Boolean> existTechnologyByName(String technologyName);
    Mono<PaginatedTechnologies> getAllTechnologies(Pagination pagination);
    Mono<Boolean> existTechnologiesByIds(List<Long> technologiesId);
    Mono<Void> saveAll(List<TechnologyCapacity> technologiesCapacityModelList);
    Flux<TechnologyWithIdAndName> getAllTechnologiesByCapacityId(Long capacityId);
}
