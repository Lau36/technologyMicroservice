package com.example.microservice.technology.technology_microservice.application.handler.impl;

import com.example.microservice.technology.technology_microservice.application.dto.request.TechnologiesCapacityRequest;
import com.example.microservice.technology.technology_microservice.application.dto.request.TechnologyRequest;
import com.example.microservice.technology.technology_microservice.application.handler.ITechnologyRestHandler;
import com.example.microservice.technology.technology_microservice.application.mapper.TechnologyMapperApplication;
import com.example.microservice.technology.technology_microservice.domain.model.*;
import com.example.microservice.technology.technology_microservice.domain.ports.in.ITechnologyServicePort;
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
    public Mono<PaginatedTechnologiesModel> getTechnologies(PaginationModel paginationModel) {
        return technologyServicePort.listTechnologies(paginationModel);
    }

    @Override
    public Mono<Boolean> existTechnologiesByIds(List<Long> technologiesId) {
        return technologyServicePort.existTechnologiesByIds(technologiesId);
    }

    @Override
    public Mono<Void> associateTechnologiesAndCapacities(TechnologiesCapacityRequest technologiesCapacityRequest) {
        TechnologiesCapacityModel model = new TechnologiesCapacityModel(
                null,
                technologiesCapacityRequest.getCapacityId(),
                technologiesCapacityRequest.getTechnologiesId()
        );
        return technologyServicePort.associateTechnologiesAndCapacities(model);
    }

    @Override
    public Flux<TechnologyWithNameModel> getAllTechnologiesByCapacityId(Long capacityId) {
        return technologyServicePort.getAllTechnologiesByCapacityId(capacityId);
    }
}
