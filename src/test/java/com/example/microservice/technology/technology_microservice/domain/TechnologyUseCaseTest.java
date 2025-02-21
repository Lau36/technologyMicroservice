package com.example.microservice.technology.technology_microservice.domain;

import com.example.microservice.technology.technology_microservice.domain.exceptions.AlreadyExistsException;
import com.example.microservice.technology.technology_microservice.domain.exceptions.DescriptionTooLongException;
import com.example.microservice.technology.technology_microservice.domain.exceptions.NameTooLongException;
import com.example.microservice.technology.technology_microservice.domain.model.*;
import com.example.microservice.technology.technology_microservice.domain.ports.out.ITechnologyPersistencePort;
import com.example.microservice.technology.technology_microservice.domain.utils.*;
import com.example.microservice.technology.technology_microservice.domain.useCase.TechnologyUseCase;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
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

        Pagination pagination = new Pagination(0, 10, SortDirection.ASC);

        List<TechnologyModel> technologies = List.of(
                new TechnologyModel(1L, "React", "Frontend Framework"),
                new TechnologyModel(2L, "Spring Boot", "Backend Framework")
        );

        PaginatedTechnologies expectedPage = new PaginatedTechnologies(
                technologies, 0, 2L, 1
        );

        when(technologyPersistencePort.getAllTechnologies(pagination))
                .thenReturn(Mono.just(expectedPage));

        Mono<PaginatedTechnologies> result = technologyUseCase.listTechnologies(pagination);

        StepVerifier.create(result)
                .expectNext(expectedPage)
                .verifyComplete();

        verify(technologyPersistencePort, times(1)).getAllTechnologies(pagination);
    }

    @Test
    void ifTechnologiesExist_shouldReturnTrue(){
        List<Long> listTechnologiesIds = List.of(1L, 2L);

        Mockito.when(technologyPersistencePort.existTechnologiesByIds(listTechnologiesIds)).thenReturn(Mono.just(true));

        Mono<Boolean> result = technologyUseCase.existTechnologiesByIds(listTechnologiesIds);

        StepVerifier.create(result)
                .expectNext(true)
                .verifyComplete();

        verify(technologyPersistencePort, times(1)).existTechnologiesByIds(listTechnologiesIds);
    }

    @Test
    void ifTechnologiesExist_shouldReturnFalse(){
        List<Long> listTechnologiesIds = List.of(1L, 2L);

        Mockito.when(technologyPersistencePort.existTechnologiesByIds(listTechnologiesIds)).thenReturn(Mono.just(false));

        Mono<Boolean> result = technologyUseCase.existTechnologiesByIds(listTechnologiesIds);

        StepVerifier.create(result)
                .expectNext(false)
                .verifyComplete();

        verify(technologyPersistencePort, times(1)).existTechnologiesByIds(listTechnologiesIds);
    }

    @Test
    void getAllTechnologies_shouldReturnTechnologies(){
        TechnologyWithIdAndName technology = new TechnologyWithIdAndName(1L, "Technology 1");
        Long capacityId = 1L;

        Mockito.when(technologyPersistencePort.getAllTechnologiesByCapacityId(capacityId)).thenReturn(Flux.just(technology));

        Flux<TechnologyWithIdAndName> result = technologyUseCase.getAllTechnologiesByCapacityId(capacityId);

        StepVerifier.create(result).expectNext(technology).verifyComplete();

        verify(technologyPersistencePort, times(1)).getAllTechnologiesByCapacityId(capacityId);
    }

    @Test
    void associateTechnologiesAndCapacities_Success() {

        List<Long> techIds = List.of(1L, 2L, 3L);
        Long capacityId = 100L;
        Long technologyId = 10L;
        TechnologiesCapacity inputModel = new TechnologiesCapacity(technologyId, capacityId, techIds);

        Mockito.when(technologyPersistencePort.existTechnologiesByIds(techIds)).thenReturn(Mono.just(true));
        Mockito.when(technologyPersistencePort.saveAll(Mockito.anyList())).thenReturn(Mono.empty());


        Mono<Void> result = technologyUseCase.associateTechnologiesAndCapacities(inputModel);


        StepVerifier.create(result)
                .verifyComplete();

        verify(technologyPersistencePort, times(1)).existTechnologiesByIds(techIds);
        verify(technologyPersistencePort, times(1)).saveAll(anyList());
    }

    @Test
    void associateTechnologiesAndCapacities_Fail_TechnologiesNotFound() {

        List<Long> techIds = List.of(1L, 2L, 3L);
        Long capacityId = 100L;
        Long technologyId = 10L;
        TechnologiesCapacity inputModel = new TechnologiesCapacity(technologyId, capacityId, techIds);

        when(technologyPersistencePort.existTechnologiesByIds(techIds)).thenReturn(Mono.just(false));


        Mono<Void> result = technologyUseCase.associateTechnologiesAndCapacities(inputModel);


        StepVerifier.create(result)
                .expectError(IllegalArgumentException.class)
                .verify();

        verify(technologyPersistencePort, times(1)).existTechnologiesByIds(techIds);
    }

    @Test
    void associateTechnologiesAndCapacities_Fail_SaveAllThrowsError() {

        List<Long> techIds = List.of(1L, 2L, 3L);
        Long capacityId = 100L;
        Long technologyId = 10L;
        TechnologiesCapacity inputModel = new TechnologiesCapacity(technologyId, capacityId, techIds);

        when(technologyPersistencePort.existTechnologiesByIds(techIds)).thenReturn(Mono.just(true));
        when(technologyPersistencePort.saveAll(anyList())).thenReturn(Mono.error(new RuntimeException("DB Error")));


        Mono<Void> result = technologyUseCase.associateTechnologiesAndCapacities(inputModel);


        StepVerifier.create(result)
                .expectError(RuntimeException.class)
                .verify();

        verify(technologyPersistencePort, times(1)).existTechnologiesByIds(techIds);
        verify(technologyPersistencePort, times(1)).saveAll(anyList());
    }



}
