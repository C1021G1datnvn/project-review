package gg.fresher.demo.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.querydsl.jpa.impl.JPAQueryFactory;

import gg.fresher.demo.dtos.ContinentDto;
import gg.fresher.demo.entities.Continent;
import gg.fresher.demo.entities.QContinent;
import gg.fresher.demo.mappers.ContinentMapper;
import gg.fresher.demo.repositories.ContinentJpaRepository;
import gg.fresher.demo.service.ContinentService;
import gg.fresher.demo.service.model.ContinentModel;
import gg.fresher.demo.utils.Paging;
import lombok.RequiredArgsConstructor;
/**
 * @created Mar 13, 2023
 *
 */
@Service
@RequiredArgsConstructor
public class ContinentServiceImpl implements ContinentService{

    @PersistenceContext
    EntityManager entityManager;
    
    private final ContinentJpaRepository repository;
    
    private final ContinentMapper mapper;

    
    /**
     * Thực hiện chức năng create ...
     * @requestbody ContinentMdel
     * @return continentDto
     */
    @Override
    public ContinentDto create(ContinentModel model) {
        model.setCode(model.getCode().toUpperCase());
        Continent newEntity = repository.save(mapper.toEntity(model));
        return mapper.toDto(newEntity);
    }

    
    /**
     * Thực hiện chức năng update ...
     * @requestbody ContinentMdel, 
     * @pathvariable id,
     * @return ResponseEntity
     */
    @Override
    public ResponseEntity<?> update(ContinentModel model, Long id) {
        ContinentDto continentDto = mapper.toDto(repository.findById(id).get());
        if (continentDto == null) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Record not found");
        } else {
            continentDto.setName(model.getName());
            continentDto.setCode(model.getCode().toUpperCase());
            continentDto.setDescription(model.getDescription());
            repository.save(mapper.toModel(continentDto));
            return new ResponseEntity<>(continentDto, HttpStatus.OK);  
        }
    }

    
    /**
     * Thực hiện chức năng detail ...
     * @pathvariable id
     * @return ResponseEntity
     */
    @Override
    public ResponseEntity<?> detail(Long id) {
        ContinentDto continentDto = mapper.toDto(repository.findById(id).get());
        if (continentDto == null) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Record not found");
        } else {
            return new ResponseEntity<>(continentDto, HttpStatus.OK);  
        }
    }

    
    /**
     * Thực hiện chức năng detail ...
     * @pathvariable id
     * @return ResponseEntity
     */
    @Override
    public ResponseEntity<?> delete(Long id) {
        Continent continent = repository.findById(id).get();
            if (continent == null) {
                return ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body("Record not found");
            } else {
                continent.setDeleted(true);
                repository.save(continent);
                return ResponseEntity
                        .status(HttpStatus.OK)
                        .body("Delete successfully");
        }
    }

    
    /**
     * Thực hiện chức năng get list ...
     * @param pageable
     * @return Paging<ContinentDto> <= 20 record
     */
    @Override
    public Paging<ContinentDto> getList(Pageable pageable) {
        JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);
        QContinent q = QContinent.continent;
        List<Continent> continents = queryFactory.selectFrom(q)
                .where(q.deleted.eq(false))
                .fetch();
        Page<Continent> pages = new PageImpl<Continent>(continents, pageable, 5);
        Page<ContinentDto> result = pages.map(mapper::toDto);
        return Paging.of(result);
    }
    
    
    /**
     * Thực hiện chức năng check code ...
     * @param code
     * @return Boolean
     */
    public Boolean checkCode(String code) {
        JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);
        QContinent q = QContinent.continent;
        List<Continent> continents = queryFactory.selectFrom(q)
                .where(q.deleted.eq(false)).fetch();
        List<String> codes = new ArrayList<>();
        for (Continent continent : continents) {
            codes.add(continent.getCode());
        }
        return codes.contains(code.toUpperCase());
    }
}
