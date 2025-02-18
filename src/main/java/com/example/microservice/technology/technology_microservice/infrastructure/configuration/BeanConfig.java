package com.example.microservice.technology.technology_microservice.infrastructure.configuration;

import com.example.microservice.technology.technology_microservice.domain.ports.in.ITechnologyServicePort;
import com.example.microservice.technology.technology_microservice.domain.ports.out.ITechnologyPersistencePort;
import com.example.microservice.technology.technology_microservice.domain.useCase.TechnologyUseCase;
import com.example.microservice.technology.technology_microservice.infrastructure.out.msql.adapter.TechnologyAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BeanConfig {

    @Bean
    public ITechnologyServicePort technologyServicePort() {
        return new TechnologyUseCase();
    }

    @Bean
    public ITechnologyPersistencePort technologyPersistencePort() {
        return new TechnologyAdapter();
    }
}
