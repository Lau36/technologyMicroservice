package com.example.microservice.technology.technology_microservice.application.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
public class TechnologyRequest {
    private Long id;
    private String name;
    private String description;
}
