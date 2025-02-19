package com.example.microservice.technology.technology_microservice.domain.model;

public class TechnologyCapacityModel {

    public TechnologyCapacityModel(Long id, Long technologyId, Long capacityId) {
        this.id = id;
        TechnologyId = technologyId;
        CapacityId = capacityId;
    }

    public Long getId() {
        return id;
    }

    public Long getTechnologyId() {
        return TechnologyId;
    }

    public Long getCapacityId() {
        return CapacityId;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTechnologyId(Long technologyId) {
        TechnologyId = technologyId;
    }

    public void setCapacityId(Long capacityId) {
        CapacityId = capacityId;
    }

    private Long id;
    private Long TechnologyId;
    private Long CapacityId;
}
