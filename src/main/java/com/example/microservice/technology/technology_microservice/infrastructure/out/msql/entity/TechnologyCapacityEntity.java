package com.example.microservice.technology.technology_microservice.infrastructure.out.msql.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@Setter
@AllArgsConstructor
@Table("technology_capacity")
public class TechnologyCapacityEntity {
    @Id
    private Long id;
    private Long id_technology;
    private Long id_capacity;
}
