package com.example.microservice.technology.technology_microservice.infrastructure.out.msql.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("technology")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TechnologyEntity {

    @Id
    private Long id;
    private String name;
    private String description;
}
