package com.example.microservice.technology.technology_microservice.domain.ports.in;

import com.example.microservice.technology.technology_microservice.domain.model.*;
import com.example.microservice.technology.technology_microservice.domain.utils.PaginatedTechnologies;
import com.example.microservice.technology.technology_microservice.domain.utils.Pagination;
import com.example.microservice.technology.technology_microservice.domain.utils.TechnologiesCapacity;
import com.example.microservice.technology.technology_microservice.domain.utils.TechnologyWithIdAndName;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface ITechnologyServicePort {
    Mono<Void> createTechnology(TechnologyModel technologyModel);
    Mono<PaginatedTechnologies> listTechnologies(Pagination pagination);
    Mono<Boolean> existTechnologiesByIds(List<Long> technologiesId);
    Mono<Void> associateTechnologiesAndCapacities(TechnologiesCapacity technologiesCapacity);
    Flux<TechnologyWithIdAndName> getAllTechnologiesByCapacityId(Long capacityId);
}
