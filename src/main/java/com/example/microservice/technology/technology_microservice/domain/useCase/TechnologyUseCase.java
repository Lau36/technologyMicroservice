package com.example.microservice.technology.technology_microservice.domain.useCase;

import com.example.microservice.technology.technology_microservice.domain.exceptions.AlreadyExistsException;
import com.example.microservice.technology.technology_microservice.domain.exceptions.DescriptionTooLongException;
import com.example.microservice.technology.technology_microservice.domain.exceptions.NameTooLongException;
import com.example.microservice.technology.technology_microservice.domain.model.PaginatedTechnologiesModel;
import com.example.microservice.technology.technology_microservice.domain.model.PaginationModel;
import com.example.microservice.technology.technology_microservice.domain.model.TechnologyModel;
import com.example.microservice.technology.technology_microservice.domain.ports.in.ITechnologyServicePort;
import com.example.microservice.technology.technology_microservice.domain.ports.out.ITechnologyPersistencePort;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static com.example.microservice.technology.technology_microservice.domain.utils.constants.ConstansDomain.MAX_LENGTH_TECHNOLOGY_DESCRIPTION;
import static com.example.microservice.technology.technology_microservice.domain.utils.constants.ConstansDomain.MAX_LENGTH_TECHNOLOGY_NAME;

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
                        return Mono.error(new AlreadyExistsException("Technology already exists"));
                    }
                    return technologyPersistencePort.saveTechnology(technologyModel);
                });
    }


    public Mono<Void> validateTechnologyAndNameLenght(TechnologyModel technologyModel) {
        if(technologyModel.getName().length() > MAX_LENGTH_TECHNOLOGY_NAME){
            return Mono.error(new NameTooLongException("Nombre de la technology excede " + MAX_LENGTH_TECHNOLOGY_NAME));
        }
        if(technologyModel.getDescription().length() > MAX_LENGTH_TECHNOLOGY_DESCRIPTION){
            return Mono.error(new DescriptionTooLongException("Descripción excede " + MAX_LENGTH_TECHNOLOGY_DESCRIPTION));
        }
        return Mono.empty();
    }

    public Mono<Boolean> existTechnology(String technologyName) {
        Mono<Boolean> bolean = technologyPersistencePort.existTechnologyByName(technologyName);
        return bolean;
    }

    @Override
    public Mono<PaginatedTechnologiesModel> listTechnologies(PaginationModel paginationModel) {
        return technologyPersistencePort.getAllTechnologies(paginationModel);
    }

}
