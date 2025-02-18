package com.example.microservice.technology.technology_microservice.infrastructure.out.msql.repository;

import com.example.microservice.technology.technology_microservice.infrastructure.out.msql.entity.TechnologyEntity;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ITechnologyRepository extends R2dbcRepository<TechnologyEntity, Long> {

    Mono<Boolean> existsTechnologyEntitiesByName(String name);
    Flux<TechnologyEntity> findAllBy(PageRequest pageable);

}
