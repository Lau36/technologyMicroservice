package com.example.microservice.technology.technology_microservice.domain.useCase;

import com.example.microservice.technology.technology_microservice.domain.exceptions.AlreadyExistsException;
import com.example.microservice.technology.technology_microservice.domain.exceptions.DescriptionTooLongException;
import com.example.microservice.technology.technology_microservice.domain.exceptions.NameTooLongException;
import com.example.microservice.technology.technology_microservice.domain.model.*;
import com.example.microservice.technology.technology_microservice.domain.ports.in.ITechnologyServicePort;
import com.example.microservice.technology.technology_microservice.domain.ports.out.ITechnologyPersistencePort;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

import static com.example.microservice.technology.technology_microservice.domain.utils.constants.ConstansDomain.*;

public class TechnologyUseCase implements ITechnologyServicePort {

    public TechnologyUseCase(ITechnologyPersistencePort technologyPersistencePort) {
        this.technologyPersistencePort = technologyPersistencePort;
    }

    private final ITechnologyPersistencePort technologyPersistencePort;

    @Override
    public Mono<Void> createTechnology(TechnologyModel technologyModel) {
        return validateTechnologyAndNameLenght(technologyModel)
                .then(existTechnology(technologyModel.getName()))
                .flatMap(exists -> {
                    if (exists) {
                        return Mono.error(new AlreadyExistsException(TECHNOLOGY_NAME_ALREADY_EXISTS));
                    }
                    return technologyPersistencePort.saveTechnology(technologyModel);
                });
    }

    public Mono<Void> validateTechnologyAndNameLenght(TechnologyModel technologyModel) {
        if(technologyModel.getName().length() > MAX_LENGTH_TECHNOLOGY_NAME){
            return Mono.error(new NameTooLongException(String.format(TECHNOLOGY_NAME_TOO_LONG, MAX_LENGTH_TECHNOLOGY_NAME)));
        }
        if(technologyModel.getDescription().length() > MAX_LENGTH_TECHNOLOGY_DESCRIPTION){
            return Mono.error(new DescriptionTooLongException(String.format(TECHNOLOGY_DESCRIPTION_TOO_LONG, MAX_LENGTH_TECHNOLOGY_DESCRIPTION)));
        }
        return Mono.empty();
    }

    public Mono<Boolean> existTechnology(String technologyName) {
        return technologyPersistencePort.existTechnologyByName(technologyName);
    }

    @Override
    public Mono<PaginatedTechnologiesModel> listTechnologies(PaginationModel paginationModel) {
        return technologyPersistencePort.getAllTechnologies(paginationModel);
    }

    @Override
    public Mono<Boolean> existTechnologiesByIds(List<Long> technologiesId) {
        return technologyPersistencePort.existTechnologiesByIds(technologiesId);
    }

    @Override
    public Mono<Void> associateTechnologiesAndCapacities(TechnologiesCapacityModel technologiesCapacityModel) {
        return technologyPersistencePort.existTechnologiesByIds(technologiesCapacityModel.getTechnologiesId())
                .flatMap(exist -> {
                    if (!exist) {
                        return Mono.error(new IllegalArgumentException(SOME_TECHNOLOGIES_DOESNT_EXISTS));
                    }

                    List<TechnologyCapacityModel> associations = technologiesCapacityModel.getTechnologiesId().stream()
                            .map(techId -> new TechnologyCapacityModel(null, techId, technologiesCapacityModel.getCapacityId())).toList();

                    return technologyPersistencePort.saveAll(associations).then();
                });
    }

    @Override
    public Flux<TechnologyWithNameModel> getAllTechnologiesByCapacityId(Long capacityId) {
        return technologyPersistencePort.getAllTechnologiesByCapacityId(capacityId);
    }

}
