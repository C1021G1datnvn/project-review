/**
 * 
 */
package gg.fresher.demo.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import gg.fresher.demo.dtos.ExampleDto;
import gg.fresher.demo.entities.Example;
import gg.fresher.demo.mappers.ExampleMapper;
import gg.fresher.demo.repositories.ExampleJpaRepository;
import gg.fresher.demo.service.ExampleService;
import gg.fresher.demo.service.model.ExampleModel;
import gg.fresher.demo.utils.Paging;
import lombok.RequiredArgsConstructor;

/**
 * @created Mar 2, 2023
 *
 */
@Service
@RequiredArgsConstructor
public class ExampleServiceImpl implements ExampleService {

    private final ExampleJpaRepository repository;
    private final ExampleMapper mapper;

    @Override
    public ExampleDto create(ExampleModel model) {
        Example newEntity = repository.save(mapper.toEntity(model));
        return mapper.toDto(newEntity);
    }

    @Override
    public Paging<ExampleDto> getList(Pageable pageable) {
        Page<ExampleDto> result = repository.findAll(pageable).map(mapper::toDto);
        return Paging.of(result);
    }

}
