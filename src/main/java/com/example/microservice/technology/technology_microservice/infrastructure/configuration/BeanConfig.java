package com.example.microservice.technology.technology_microservice.infrastructure.configuration;

import com.example.microservice.technology.technology_microservice.application.handler.ITechnologyRestHandler;
import com.example.microservice.technology.technology_microservice.application.handler.impl.TechnologyRestHandlerImpl;
import com.example.microservice.technology.technology_microservice.application.mapper.TechnologyMapperApplication;
import com.example.microservice.technology.technology_microservice.domain.ports.in.ITechnologyServicePort;
import com.example.microservice.technology.technology_microservice.domain.ports.out.ITechnologyPersistencePort;
import com.example.microservice.technology.technology_microservice.domain.useCase.TechnologyUseCase;
import com.example.microservice.technology.technology_microservice.infrastructure.out.msql.adapter.TechnologyAdapter;
import com.example.microservice.technology.technology_microservice.infrastructure.out.msql.mapper.TechnologyMapper;
import com.example.microservice.technology.technology_microservice.infrastructure.out.msql.repository.ITechnologyCapacityRepository;
import com.example.microservice.technology.technology_microservice.infrastructure.out.msql.repository.ITechnologyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BeanConfig {
    private final ITechnologyRepository technologyRepository;
    private final ITechnologyCapacityRepository technologyCapacityRepository;
    private final TechnologyMapper technologyMapper;

    private final TechnologyMapperApplication technologyMapperApplication;


    @Bean
    public ITechnologyServicePort technologyServicePort() {
        return new TechnologyUseCase(technologyPersistencePort());
    }

    @Bean
    public ITechnologyPersistencePort technologyPersistencePort() {
        return new TechnologyAdapter(technologyRepository, technologyCapacityRepository, technologyMapper);
    }

    @Bean
    public ITechnologyRestHandler technologyRestHandler() {
        return new TechnologyRestHandlerImpl(technologyServicePort(), technologyMapperApplication);
    }
}
