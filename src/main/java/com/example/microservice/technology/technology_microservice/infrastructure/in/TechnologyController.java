package com.example.microservice.technology.technology_microservice.infrastructure.in;

import com.example.microservice.technology.technology_microservice.application.dto.request.TechnologiesCapacityRequest;
import com.example.microservice.technology.technology_microservice.application.dto.request.TechnologiesIdsRequest;
import com.example.microservice.technology.technology_microservice.application.dto.request.TechnologyRequest;
import com.example.microservice.technology.technology_microservice.application.handler.ITechnologyRestHandler;
import com.example.microservice.technology.technology_microservice.domain.model.PaginatedTechnologiesModel;
import com.example.microservice.technology.technology_microservice.domain.model.PaginationModel;
import com.example.microservice.technology.technology_microservice.domain.utils.SortDirection;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;


import java.util.List;
import java.util.stream.Collectors;

import static com.example.microservice.technology.technology_microservice.infrastructure.utils.constans.ConstansInfra.TECHNOLOGY_PATH;

@RestController()
@AllArgsConstructor
@RequestMapping(TECHNOLOGY_PATH)
public class TechnologyController {

    private final ITechnologyRestHandler technologyRestHandler;

    @PostMapping
    public Mono<ResponseEntity<Void>> technology(@RequestBody TechnologyRequest technologyRequest) {
        return technologyRestHandler.createTechnology(technologyRequest).then(Mono.just(ResponseEntity.status(HttpStatus.CREATED).build()));
    }

    @GetMapping
    public Mono<PaginatedTechnologiesModel> getTechnologiesPaginated(@RequestParam int page,
                                                                     @RequestParam int size,
                                                                     @RequestParam String sortDirection) {
        PaginationModel pagination = new PaginationModel(page, size, SortDirection.valueOf(sortDirection.toUpperCase()));
        return technologyRestHandler.getTechnologies(pagination);
    }

    @PostMapping("/exists")
    public Mono<Boolean> existTechnologies(@RequestBody TechnologiesIdsRequest technologiesIdsRequest) {
        List<Long> ids = technologiesIdsRequest.getTechnologiesIds().stream().map(Long::parseLong).collect(Collectors.toList());
        return technologyRestHandler.existTechnologiesByIds(ids);
    }

    @PostMapping("/asociate")
    public Mono<ResponseEntity<Void>> associateTechnologies(@RequestBody TechnologiesCapacityRequest request) {
        return technologyRestHandler.associateTechnologiesAndCapacities(request)
                .then(Mono.just(ResponseEntity.status(HttpStatus.CREATED).build()));

    }
}
