package com.example.microservice.technology.technology_microservice.infrastructure.controller;

import com.example.microservice.technology.technology_microservice.application.handler.ITechnologyRestHandler;
import com.example.microservice.technology.technology_microservice.domain.model.PaginatedTechnologiesModel;
import com.example.microservice.technology.technology_microservice.domain.model.PaginationModel;
import com.example.microservice.technology.technology_microservice.domain.model.TechnologyModel;
import com.example.microservice.technology.technology_microservice.domain.utils.SortDirection;
import com.example.microservice.technology.technology_microservice.infrastructure.in.TechnologyController;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import java.util.List;

import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
@WebFluxTest(TechnologyController.class)
class TechnologyControllerTest {
//
//    @Autowired
//    private WebTestClient webTestClient;
//
//    @Mock
//    private ITechnologyRestHandler technologyRestHandler;
//
//    @InjectMocks
//    private TechnologyController technologyController;
//
//    @Test
//    void getAllTechnologiesPaginated(){
//        // Arrange
//        int page = 0;
//        int size = 10;
//        String sortDirection = "ASC";
//
//        PaginationModel paginationModel = new PaginationModel(page, size, SortDirection.valueOf(sortDirection));
//
//        List<TechnologyModel> technologies = List.of(
//                new TechnologyModel(1L, "React", "Frontend Framework"),
//                new TechnologyModel(2L, "Spring Boot", "Backend Framework")
//        );
//
//        PaginatedTechnologiesModel expectedResponse = new PaginatedTechnologiesModel(
//                technologies, 0, 2L, 1
//        );
//
//        Mockito.when(technologyRestHandler.getTechnologies(paginationModel))
//                .thenReturn(Mono.just(expectedResponse));
//
//        webTestClient.get()
//                .uri(uriBuilder -> uriBuilder.path("api/v1/technology")
//                        .queryParam("page", page)
//                        .queryParam("size", size)
//                        .queryParam("sortDirection", sortDirection)
//                        .build())
//                .exchange()
//                .expectStatus().isOk()
//                .expectBody(PaginatedTechnologiesModel.class)
//                .isEqualTo(expectedResponse);
//
//        Mockito.verify(technologyRestHandler, times(1)).getTechnologies(paginationModel);
//    }
}
