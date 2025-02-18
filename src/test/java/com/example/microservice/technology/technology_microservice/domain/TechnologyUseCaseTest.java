package com.example.microservice.technology.technology_microservice.domain;

import com.example.microservice.technology.technology_microservice.domain.exceptions.AlreadyExistsException;
import com.example.microservice.technology.technology_microservice.domain.exceptions.DescriptionTooLongException;
import com.example.microservice.technology.technology_microservice.domain.exceptions.NameTooLongException;
import com.example.microservice.technology.technology_microservice.domain.model.PaginatedTechnologiesModel;
import com.example.microservice.technology.technology_microservice.domain.model.PaginationModel;
import com.example.microservice.technology.technology_microservice.domain.model.TechnologyModel;
import com.example.microservice.technology.technology_microservice.domain.ports.out.ITechnologyPersistencePort;
import com.example.microservice.technology.technology_microservice.domain.useCase.TechnologyUseCase;
import com.example.microservice.technology.technology_microservice.domain.utils.SortDirection;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TechnologyUseCaseTest {

    @Mock
    private ITechnologyPersistencePort technologyPersistencePort;

    @InjectMocks
    private TechnologyUseCase technologyUseCase;


    @Test
    void createTechnologyTest(){
        TechnologyModel newTechnology = new TechnologyModel(1L, "React", "Frontend Framework");

        Mockito.when(technologyPersistencePort.existTechnologyByName(newTechnology.getName())).thenReturn(Mono.just(false));
        Mockito.when(technologyPersistencePort.saveTechnology(newTechnology)).thenReturn(Mono.empty());

        Mono<Void> result = technologyUseCase.createTechnology(newTechnology);

        StepVerifier.create(result).verifyComplete();

        Mockito.verify(technologyPersistencePort, times(1)).existTechnologyByName("React");
        Mockito.verify(technologyPersistencePort, times(1)).saveTechnology(newTechnology);
    }

    @Test
    void createTechnologyTest_shoulThrowAlreadyExistsException(){
        TechnologyModel newTechnology = new TechnologyModel(1L, "React", "Frontend Framework");

        Mockito.when(technologyPersistencePort.existTechnologyByName(newTechnology.getName())).thenReturn(Mono.just(true));

        Mono<Void> result = technologyUseCase.createTechnology(newTechnology);

        StepVerifier.create(result).expectError(AlreadyExistsException.class).verify();

        Mockito.verify(technologyPersistencePort, times(1)).existTechnologyByName("React");
        Mockito.verify(technologyPersistencePort, never()).saveTechnology(newTechnology);
    }

    @Test
    void createTechnologyTest_shoulThrowNameTooLongException(){
        String longName = "React".repeat(100);
        TechnologyModel newTechnology = new TechnologyModel(1L, longName, "Frontend Framework");

        Mockito.when(technologyPersistencePort.existTechnologyByName(newTechnology.getName())).thenReturn(Mono.just(false));
        Mono<Void> result = technologyUseCase.createTechnology(newTechnology);

        StepVerifier.create(result).expectError(NameTooLongException.class).verify();

        Mockito.verify(technologyPersistencePort, never()).saveTechnology(Mockito.any());
    }

    @Test
    void createTechnologyTest_shoulThrowDescriptionTooLongException(){
        String longDescription = "Frontend Framework".repeat(100);
        TechnologyModel newTechnology = new TechnologyModel(1L, "React", longDescription);

        Mockito.when(technologyPersistencePort.existTechnologyByName(newTechnology.getName())).thenReturn(Mono.just(false));
        Mono<Void> result = technologyUseCase.createTechnology(newTechnology);

        StepVerifier.create(result).expectError(DescriptionTooLongException.class).verify();

        Mockito.verify(technologyPersistencePort, never()).saveTechnology(Mockito.any());
    }

    @Test
    void listAllTechnologies_shouldReturnPaginatedData(){

        PaginationModel paginationModel = new PaginationModel(0, 10, SortDirection.ASC);

        List<TechnologyModel> technologies = List.of(
                new TechnologyModel(1L, "React", "Frontend Framework"),
                new TechnologyModel(2L, "Spring Boot", "Backend Framework")
        );

        PaginatedTechnologiesModel expectedPage = new PaginatedTechnologiesModel(
                technologies, 0, 2L, 1
        );

        when(technologyPersistencePort.getAllTechnologies(paginationModel))
                .thenReturn(Mono.just(expectedPage));

        Mono<PaginatedTechnologiesModel> result = technologyUseCase.listTechnologies(paginationModel);

        StepVerifier.create(result)
                .expectNext(expectedPage)
                .verifyComplete();

        verify(technologyPersistencePort, times(1)).getAllTechnologies(paginationModel);
    }
}
