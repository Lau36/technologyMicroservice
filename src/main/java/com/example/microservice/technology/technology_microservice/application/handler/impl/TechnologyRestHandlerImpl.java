package com.example.microservice.technology.technology_microservice.application.handler.impl;

import com.example.microservice.technology.technology_microservice.application.dto.request.TechnologyRequest;
import com.example.microservice.technology.technology_microservice.application.handler.ITechnologyRestHandler;
import com.example.microservice.technology.technology_microservice.application.mapper.TechnologyMapperApplication;
import com.example.microservice.technology.technology_microservice.domain.model.PaginatedTechnologiesModel;
import com.example.microservice.technology.technology_microservice.domain.model.PaginationModel;
import com.example.microservice.technology.technology_microservice.domain.model.TechnologyModel;
import com.example.microservice.technology.technology_microservice.domain.ports.in.ITechnologyServicePort;
import reactor.core.publisher.Mono;


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
}
