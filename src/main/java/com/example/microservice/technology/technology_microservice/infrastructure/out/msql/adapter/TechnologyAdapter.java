package com.example.microservice.technology.technology_microservice.infrastructure.out.msql.adapter;

import com.example.microservice.technology.technology_microservice.domain.model.PaginatedTechnologiesModel;
import com.example.microservice.technology.technology_microservice.domain.model.PaginationModel;
import com.example.microservice.technology.technology_microservice.domain.model.TechnologyModel;
import com.example.microservice.technology.technology_microservice.domain.ports.out.ITechnologyPersistencePort;
import com.example.microservice.technology.technology_microservice.infrastructure.out.msql.entity.TechnologyEntity;
import com.example.microservice.technology.technology_microservice.infrastructure.out.msql.mapper.TechnologyMapper;
import com.example.microservice.technology.technology_microservice.infrastructure.out.msql.repository.ITechnologyRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import reactor.core.publisher.Mono;

import java.util.List;

import static com.example.microservice.technology.technology_microservice.infrastructure.utils.constans.ConstansInfra.SORT;

@AllArgsConstructor
public class TechnologyAdapter implements ITechnologyPersistencePort {

    private final ITechnologyRepository repository;
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

    public TechnologyModel toModel(TechnologyEntity technologyEntity) {
        return new TechnologyModel(technologyEntity.getId(), technologyEntity.getName(), technologyEntity.getDescription());
    }


}
