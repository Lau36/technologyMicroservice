package com.example.microservice.technology.technology_microservice.infrastructure.out.msql.repository;

import com.example.microservice.technology.technology_microservice.infrastructure.out.msql.entity.TechnologyCapacityEntity;
import com.example.microservice.technology.technology_microservice.infrastructure.out.msql.entity.TechnologyEntity;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface ITechnologyCapacityRepository extends R2dbcRepository<TechnologyCapacityEntity, Long> {
    Flux<TechnologyCapacityEntity> saveAll(List<TechnologyCapacityEntity> technologyEntities);
}
