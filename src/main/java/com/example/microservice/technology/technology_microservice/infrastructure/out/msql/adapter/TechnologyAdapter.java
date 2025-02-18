package com.example.microservice.technology.technology_microservice.infrastructure.out.msql.adapter;

import com.example.microservice.technology.technology_microservice.domain.model.TechnologyModel;
import com.example.microservice.technology.technology_microservice.domain.ports.out.ITechnologyPersistencePort;
import com.example.microservice.technology.technology_microservice.infrastructure.out.msql.entity.TechnologyEntity;
import com.example.microservice.technology.technology_microservice.infrastructure.out.msql.mapper.TechnologyMapper;
import com.example.microservice.technology.technology_microservice.infrastructure.out.msql.repository.ITechnologyRepository;
import lombok.AllArgsConstructor;
import reactor.core.publisher.Mono;

@AllArgsConstructor
public class TechnologyAdapter implements ITechnologyPersistencePort {

    private final ITechnologyRepository repository;
    private final TechnologyMapper mapper;

    @Override
    public Mono<Void> saveTechnology(TechnologyModel technologyModel) {
        TechnologyEntity entity = new TechnologyEntity(technologyModel.getId(), technologyModel.getName(), technologyModel.getDescription());
        return repository.save(entity).then();
    }

    @Override
    public Mono<Boolean> existTechnologyByName(String technologyName) {
        return repository.existsTechnologyEntitiesByName(technologyName);
    }
}
