package com.example.microservice.technology.technology_microservice.infrastructure.out.msql.mapper;

import com.example.microservice.technology.technology_microservice.domain.model.TechnologyModel;
import com.example.microservice.technology.technology_microservice.infrastructure.out.msql.entity.TechnologyEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE
)
public interface TechnologyMapper {

    TechnologyEntity toTechnologyEntity(TechnologyModel technologyModel);
    TechnologyModel toTechnologyModel(TechnologyEntity technologyEntity);
}
