package com.example.microservice.technology.technology_microservice.infrastructure.out.msql.adapter;

import com.example.microservice.technology.technology_microservice.domain.model.*;
import com.example.microservice.technology.technology_microservice.domain.ports.out.ITechnologyPersistencePort;
import com.example.microservice.technology.technology_microservice.infrastructure.out.msql.entity.TechnologyCapacityEntity;
import com.example.microservice.technology.technology_microservice.infrastructure.out.msql.entity.TechnologyEntity;
import com.example.microservice.technology.technology_microservice.infrastructure.out.msql.mapper.TechnologyMapper;
import com.example.microservice.technology.technology_microservice.infrastructure.out.msql.repository.ITechnologyCapacityRepository;
import com.example.microservice.technology.technology_microservice.infrastructure.out.msql.repository.ITechnologyRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

import static com.example.microservice.technology.technology_microservice.infrastructure.utils.constans.ConstansInfra.SORT;

@AllArgsConstructor
public class TechnologyAdapter implements ITechnologyPersistencePort {

    private final ITechnologyRepository repository;
    private final ITechnologyCapacityRepository technologyCapacityRepository;
    private final TechnologyMapper mapper;

    @Override
    public Mono<Void> saveTechnology(TechnologyModel technologyModel) {
        TechnologyEntity entity = new TechnologyEntity(technologyModel.getId(), technologyModel.getName(), technologyModel.getDescription());
        return repository.save(entity).then();
    }

    @Override
    public Mono<Boolean> existTechnologyByName(String technologyName) {
        return repository.existsTechnologyEntitiesByName(technologyName);
    }

    @Override
    public Mono<PaginatedTechnologiesModel> getAllTechnologies(PaginationModel paginationModel) {
        Sort sort = Sort.by(Sort.Direction.fromString(paginationModel.getSortDirection().name()), SORT);
        PageRequest pageable = PageRequest.of(paginationModel.getPage(), paginationModel.getSize(), sort);
        Mono<List<TechnologyModel>> technologies =
                repository.findAllBy(pageable)
                        .map(this::toModel)
                        .collectList();

        Mono<Long> totalElements = repository.count();

        return Mono.zip(technologies, totalElements).map(
                tuple -> new PaginatedTechnologiesModel(
                        tuple.getT1(),
                        paginationModel.getPage(),
                        tuple.getT2(),
                        (int) Math.ceil((double) tuple.getT2()) / paginationModel.getSize()
                ));
    }

    @Override
    public Mono<Boolean> existTechnologiesByIds(List<Long> technologiesId) {
        return repository.countByIdIn(technologiesId)
                .map(count -> count == technologiesId.size());
    }

    @Override
    public Mono<Void> saveAll(List<TechnologyCapacityModel> technologiesCapacityModelList) {

        List<TechnologyCapacityEntity> entities = technologiesCapacityModelList.stream().map(
                model -> new TechnologyCapacityEntity(model.getId(), model.getTechnologyId(), model.getCapacityId())
        ).toList();

        return technologyCapacityRepository.saveAll(entities).then();
    }

    @Override
    public Flux<TechnologyWithNameModel> getAllTechnologiesByCapacityId(Long capacityId) {
        return repository.findTechnologiesByCapacityId(capacityId)
                .map(technology ->
                        new TechnologyWithNameModel(
                                technology.getId(),
                                technology.getName()
                        ));
    }

    public TechnologyModel toModel(TechnologyEntity technologyEntity) {
        return new TechnologyModel(technologyEntity.getId(), technologyEntity.getName(), technologyEntity.getDescription());
    }


}
