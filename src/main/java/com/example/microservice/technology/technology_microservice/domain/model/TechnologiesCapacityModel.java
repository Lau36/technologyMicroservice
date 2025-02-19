package com.example.microservice.technology.technology_microservice.domain.model;

import java.util.List;

public class TechnologiesCapacityModel {
    public List<Long> getTechnologiesId() {
        return technologiesId;
    }

    public Long getCapacityId() {
        return capacityId;
    }

    public void setCapacityId(Long capacityId) {
        this.capacityId = capacityId;
    }

    public void setTechnologiesId(List<Long> technologiesId) {
        this.technologiesId = technologiesId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TechnologiesCapacityModel(Long id, Long capacityId, List<Long> technologiesId) {
        this.id = id;
        this.capacityId = capacityId;
        this.technologiesId = technologiesId;
    }

    private Long id;
    private Long capacityId;
    private List<Long> technologiesId;
}
