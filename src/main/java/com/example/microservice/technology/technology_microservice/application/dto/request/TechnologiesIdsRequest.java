package com.example.microservice.technology.technology_microservice.application.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class TechnologiesIdsRequest {
    private List<String> technologiesIds;
}
