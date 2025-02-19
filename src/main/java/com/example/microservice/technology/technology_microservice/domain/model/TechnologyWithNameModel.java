package com.example.microservice.technology.technology_microservice.domain.model;

public class TechnologyWithNameModel {
    public TechnologyWithNameModel(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    private Long id;
    private String name;
}
