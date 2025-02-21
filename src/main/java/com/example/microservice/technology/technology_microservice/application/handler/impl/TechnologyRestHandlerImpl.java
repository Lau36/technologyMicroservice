package com.example.microservice.technology.technology_microservice.application.handler.impl;

import com.example.microservice.technology.technology_microservice.application.dto.request.TechnologiesCapacityRequest;
import com.example.microservice.technology.technology_microservice.application.dto.request.TechnologyRequest;
import com.example.microservice.technology.technology_microservice.application.handler.ITechnologyRestHandler;
import com.example.microservice.technology.technology_microservice.application.mapper.TechnologyMapperApplication;
import com.example.microservice.technology.technology_microservice.domain.model.*;
import com.example.microservice.technology.technology_microservice.domain.ports.in.ITechnologyServicePort;
import com.example.microservice.technology.technology_microservice.domain.utils.PaginatedTechnologies;
import com.example.microservice.technology.technology_microservice.domain.utils.Pagination;
import com.example.microservice.technology.technology_microservice.domain.utils.TechnologiesCapacity;
import com.example.microservice.technology.technology_microservice.domain.utils.TechnologyWithIdAndName;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;


public class TechnologyRestHandlerImpl implements ITechnologyRestHandler {

    private final ITechnologyServicePort technologyServicePort;

    private final TechnologyMapperApplication technologyMapperApplication;

    public TechnologyRestHandlerImpl(ITechnologyServicePort technologyServicePort, TechnologyMapperApplication technologyMapperApplication) {
        this.technologyServicePort = technologyServicePort;
        this.technologyMapperApplication = technologyMapperApplication;
    }


    @Override
    public Mono<Void> createTechnology(TechnologyRequest technologyRequest) {
        TechnologyModel technologyModel = new TechnologyModel(technologyRequest.getId(), technologyRequest.getName(), technologyRequest.getDescription());
        return technologyServicePort.createTechnology(technologyModel);
    }

    @Override
    public Mono<PaginatedTechnologies> getTechnologies(Pagination pagination) {
        return technologyServicePort.listTechnologies(pagination);
    }

    @Override
    public Mono<Boolean> existTechnologiesByIds(List<Long> technologiesId) {
        return technologyServicePort.existTechnologiesByIds(technologiesId);
    }

    @Override
    public Mono<Void> associateTechnologiesAndCapacities(TechnologiesCapacityRequest technologiesCapacityRequest) {
        TechnologiesCapacity model = new TechnologiesCapacity(
                null,
                technologiesCapacityRequest.getCapacityId(),
                technologiesCapacityRequest.getTechnologiesId()
        );
        return technologyServicePort.associateTechnologiesAndCapacities(model);
    }

    @Override
    public Flux<TechnologyWithIdAndName> getAllTechnologiesByCapacityId(Long capacityId) {
        return technologyServicePort.getAllTechnologiesByCapacityId(capacityId);
    }
}
