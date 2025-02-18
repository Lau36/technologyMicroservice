package com.example.microservice.technology.technology_microservice.infrastructure.in;

import com.example.microservice.technology.technology_microservice.application.dto.request.TechnologyRequest;
import com.example.microservice.technology.technology_microservice.application.handler.ITechnologyRestHandler;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

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
}
