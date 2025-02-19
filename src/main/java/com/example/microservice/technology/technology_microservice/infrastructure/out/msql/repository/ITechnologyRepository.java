package com.example.microservice.technology.technology_microservice.infrastructure.out.msql.repository;

import com.example.microservice.technology.technology_microservice.infrastructure.out.msql.entity.TechnologyEntity;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface ITechnologyRepository extends R2dbcRepository<TechnologyEntity, Long> {

    Mono<Boolean> existsTechnologyEntitiesByName(String name);
    Flux<TechnologyEntity> findAllBy(PageRequest pageable);
    Mono<Long> countByIdIn(List<Long> ids);

    @Query("SELECT t.* FROM technology t " +
            "INNER JOIN technology_capacity tc ON t.id = tc.id_technology " +
            "WHERE tc.id_capacity = :capacityId")
    Flux<TechnologyEntity> findTechnologiesByCapacityId(Long capacityId);
}
