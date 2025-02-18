package com.example.microservice.technology.technology_microservice.application.handler.impl;

import com.example.microservice.technology.technology_microservice.application.dto.request.TechnologyRequest;
import com.example.microservice.technology.technology_microservice.application.handler.ITechnologyRestHandler;
import com.example.microservice.technology.technology_microservice.application.mapper.TechnologyMapperApplication;
import com.example.microservice.technology.technology_microservice.domain.model.TechnologyModel;
import com.example.microservice.technology.technology_microservice.domain.ports.in.ITechnologyServicePort;
import lombok.AllArgsConstructor;
import reactor.core.publisher.Mono;

@AllArgsConstructor
public class TechnologyRestHandlerImpl implements ITechnologyRestHandler {

    private final ITechnologyServicePort technologyServicePort;
    private final TechnologyMapperApplication technologyMapperApplication;

    @Override
    public Mono<Void> createTechnology(TechnologyRequest technologyRequest) {
        TechnologyModel technologyModel = new TechnologyModel(technologyRequest.getId(), technologyRequest.getName(), technologyRequest.getDescription());
        return technologyServicePort.createTechnology(technologyModel);
    }
}
