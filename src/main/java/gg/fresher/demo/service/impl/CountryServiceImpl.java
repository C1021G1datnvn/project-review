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

import gg.fresher.demo.dtos.CountryDto;
import gg.fresher.demo.entities.Country;
import gg.fresher.demo.entities.QCountry;
import gg.fresher.demo.mappers.CountryMapper;
import gg.fresher.demo.repositories.CountryJpaRepository;
import gg.fresher.demo.service.CountryService;
import gg.fresher.demo.service.model.CountryModel;
import gg.fresher.demo.utils.Paging;
import lombok.RequiredArgsConstructor;
/**
 * @created Mar 13, 2023
 *
 */
@Service
@RequiredArgsConstructor
public class CountryServiceImpl implements CountryService{
    @PersistenceContext
    EntityManager entityManager;
    
    private final CountryJpaRepository repository;
    
    private final CountryMapper mapper;

    
    /**
     * Thực hiện chức năng create ...
     * @requestbody CountryModel
     * @return CountryDto
     */
    @Override
    public CountryDto create(CountryModel model) {
        model.setCode(model.getCode().toUpperCase());
        Country newEntity = repository.save(mapper.toEntity(model));
        return mapper.toDto(newEntity);
    }

    
    /**
     * Thực hiện chức năng update ...
     * @requestbody CountryModel, 
     * @pathvariable id,
     * @return ResponseEntity
     */
    @Override
    public ResponseEntity<?> update(CountryModel model, Long id) {
        CountryDto countryDto = mapper.toDto(repository.findById(id).get());
        if (countryDto == null) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Record not found");
        } else {
            countryDto.setName(model.getName());
            countryDto.setCode(model.getCode().toUpperCase());
            countryDto.setDescription(model.getDescription());
            countryDto.setContinentId(model.getContinentId());
            repository.save(mapper.toModel(countryDto));
            return new ResponseEntity<>(countryDto, HttpStatus.OK);  
        }
    }

    
    /**
     * Thực hiện chức năng detail ...
     * @pathvariable id
     * @return ResponseEntity
     */
    @Override
    public ResponseEntity<?> detail(Long id) {
        CountryDto countryDto = mapper.toDto(repository.findById(id).get());
        if (countryDto == null) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Record not found");
        } else {
            return new ResponseEntity<>(countryDto, HttpStatus.OK);  
        }
    }
    
    /**
     * Thực hiện chức năng detail ...
     * @pathvariable id
     * @return ResponseEntity
     */
    @Override
    public ResponseEntity<?> delete(Long id) {
        Country country = repository.findById(id).get();
        if (country == null) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Record not found");
        } else {
            country.setDeleted(true);
            repository.save(country);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body("Delete successfully");
        }
    }

    
    /**
     * Thực hiện chức năng get list ...
     * @param pageable
     * @return Paging<CountryDto> <= 20 record
     */
    @Override
    public Paging<CountryDto> getList(Pageable pageable) {
        JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);
        QCountry q = QCountry.country;
        List<Country> countrys = queryFactory.selectFrom(q)
                .where(q.deleted.eq(false))
                .fetch();
        Page<Country> pages = new PageImpl<Country>(countrys, pageable, 5);
        Page<CountryDto> result = pages.map(mapper::toDto);
        return Paging.of(result);
    }

    /**
     * Thực hiện chức năng search ...
     * @param pageable, @PathVariable continentId
     * @return Paging<CountryDto> <= 20 record
     */
    @Override
    public Paging<CountryDto> getListSearchCountryOfContinent(Pageable pageable, Long continentId) {
        JPAQuery<Country> query = new JPAQuery<Country>(entityManager);
        QCountry q = QCountry.country;
        query.from(q).where(q.deleted.isFalse());
        if(continentId != null) {
            query.where(q.continentId.eq(continentId));
        }
        Page<Country> pages = new PageImpl<Country>(query.fetch(), pageable, 5);
        Page<CountryDto> result = pages.map(mapper::toDto);
        return Paging.of(result);
    }
    
    /**
     * Thực hiện chức năng check code ...
     * @param code
     * @return Boolean
     */
    public Boolean checkCode(String code) {
        JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);
        QCountry q = QCountry.country;
        List<Country> countrys = queryFactory.selectFrom(q)
                .where(q.deleted.eq(false)).fetch();
        List<String> codes = new ArrayList<>();
        for (Country country : countrys) {
            codes.add(country.getCode());
        }
        return codes.contains(code.toUpperCase());
    }
    
}
