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

import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

import gg.fresher.demo.dtos.EthnicityDto;
import gg.fresher.demo.entities.Ethnicity;
import gg.fresher.demo.entities.QEthnicity;
import gg.fresher.demo.mappers.EthnicityMapper;
import gg.fresher.demo.repositories.EthnicityJpaRepository;
import gg.fresher.demo.service.EthnicityService;
import gg.fresher.demo.service.model.EthnicityModel;
import gg.fresher.demo.utils.Paging;
import lombok.RequiredArgsConstructor;
/**
 * @created Mar 13, 2023
 *
 */
@Service
@RequiredArgsConstructor
public class EthnicityServiceImpl implements EthnicityService{
    
    @PersistenceContext
    EntityManager entityManager;
    
    private final EthnicityJpaRepository repository;
    
    private final EthnicityMapper mapper;
    
    
    /**
     * Thực hiện chức năng create ...
     * @requestbody EthnicityModel
     * @return EthnicityDto
     */
    @Override
    public EthnicityDto create(EthnicityModel model) {
        model.setCode(model.getCode().toUpperCase());
        Ethnicity newEntity = repository.save(mapper.toEntity(model));
        return mapper.toDto(newEntity);
    }

    
    /**
     * Thực hiện chức năng update ...
     * @requestbody EthnicityModel, 
     * @pathvariable id,
     * @return ResponseEntity
     */
    @Override
    public ResponseEntity<?> update(EthnicityModel model, Long id) {
        EthnicityDto ethnicityDto = mapper.toDto(repository.findById(id).get());
        if (ethnicityDto == null) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Record not found");
        } else {
            ethnicityDto.setName(model.getName());
            ethnicityDto.setCode(model.getCode().toUpperCase());
            ethnicityDto.setDescription(model.getDescription());
            ethnicityDto.setCountryId(model.getCountryId());
            ethnicityDto.setContinentId(model.getContinentId());
            repository.save(mapper.toModel(ethnicityDto));
            return new ResponseEntity<>(ethnicityDto, HttpStatus.OK);  
        }
    }

    
    /**
     * Thực hiện chức năng detail ...
     * @pathvariable id
     * @return ResponseEntity
     */
    @Override
    public ResponseEntity<?> detail(Long id) {
        EthnicityDto ethnicityDto = mapper.toDto(repository.findById(id).get());
        if (ethnicityDto == null) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Record not found");
        } else {
            return new ResponseEntity<>(ethnicityDto, HttpStatus.OK);  
        }
    }

    
    /**
     * Thực hiện chức năng delete ...
     * @pathvariable id
     * @return ResponseEntity
     */
    @Override
    public ResponseEntity<?> delete(Long id) {
        EthnicityDto ethnicityDto = mapper.toDto(repository.findById(id).get());
        if (ethnicityDto == null) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Record not found");
        } else {
            repository.deleteById(ethnicityDto.getId());
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body("Delete successfully");
        }
    }

    
    /**
     * Thực hiện chức năng get list ...
     * @param pageable
     * @return Paging<EthnicityDto> <= 20 record
     */
    @Override
    public Paging<EthnicityDto> getList(Pageable pageable) {
        JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);
        QEthnicity q = QEthnicity.ethnicity;
        List<Ethnicity> languages = queryFactory.selectFrom(q)
                .where(q.deleted.eq(false))
                .fetch();
        Page<Ethnicity> pages = new PageImpl<Ethnicity>(languages, pageable, 5);
        Page<EthnicityDto> result = pages.map(mapper::toDto);
        return Paging.of(result);
    }

    
    /**
     * Thực hiện chức năng search ...
     * @param pageable, @PathVariable countryId
     * @return Paging<EthnicityDto> <= 20 record
     */
    @Override
    public Paging<EthnicityDto> getListSearchEthnicityOfCountry(Pageable pageable, Long countryId) {
        JPAQuery<Ethnicity> query = new JPAQuery<Ethnicity>(entityManager);
        QEthnicity q = QEthnicity.ethnicity;
        query.from(q).where(q.deleted.isFalse());
        if(countryId != null) {
            query.where(q.countryId.eq(countryId));
        }
        Page<Ethnicity> pages = new PageImpl<Ethnicity>(query.fetch(), pageable, 5);
        Page<EthnicityDto> result = pages.map(mapper::toDto);
        return Paging.of(result);
    }

    
    /**
     * Thực hiện chức năng search ...
     * @param pageable, @PathVariable continentId
     * @return Paging<EthnicityDto> <= 20 record
     */
    @Override
    public Paging<EthnicityDto> getListSearchEthnicityOfContinent(Pageable pageable, Long continentId) {
        JPAQuery<Ethnicity> query = new JPAQuery<Ethnicity>(entityManager);
        QEthnicity q = QEthnicity.ethnicity;
        query.from(q).where(q.deleted.isFalse());
        if(continentId != null) {
            query.where(q.continentId.eq(continentId));
        }
        Page<Ethnicity> pages = new PageImpl<Ethnicity>(query.fetch(), pageable, 5);
        Page<EthnicityDto> result = pages.map(mapper::toDto);
        return Paging.of(result);
    }

    
    /**
     * Thực hiện chức năng check code ...
     * @param code
     * @return Boolean
     */
    public Boolean checkCode(String code) {
        JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);
        QEthnicity q = QEthnicity.ethnicity;
        List<Ethnicity> ethnicitys = queryFactory.selectFrom(q)
                .where(q.deleted.eq(false)).fetch();
        List<String> codes = new ArrayList<>();
        for (Ethnicity ethnicity : ethnicitys) {
            codes.add(ethnicity.getCode());
        }
        return codes.contains(code.toUpperCase());
    }
}
