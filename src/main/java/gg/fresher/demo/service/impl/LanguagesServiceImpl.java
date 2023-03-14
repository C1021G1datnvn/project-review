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

import gg.fresher.demo.dtos.LanguagesDto;
import gg.fresher.demo.entities.Languages;
import gg.fresher.demo.entities.QLanguages;
import gg.fresher.demo.mappers.LanguagesMapper;
import gg.fresher.demo.repositories.LanguagesJpaRepository;
import gg.fresher.demo.service.LanguagesService;
import gg.fresher.demo.service.model.LanguagesModel;
import gg.fresher.demo.utils.Paging;
import lombok.RequiredArgsConstructor;
/**
 * @created Mar 13, 2023
 *
 */
@Service
@RequiredArgsConstructor
public class LanguagesServiceImpl implements LanguagesService{
    
    @PersistenceContext
    EntityManager entityManager;
    
    private final LanguagesJpaRepository repository;
    
    private final LanguagesMapper mapper;
    
    
    /**
     * Thực hiện chức năng create ...
     * @requestbody LanguagesModel
     * @return LanguagesDto
     */
    @Override
    public LanguagesDto create(LanguagesModel model) {            
            model.setCode(model.getCode().toUpperCase());
            Languages newEntity = repository.save(mapper.toEntity(model));
            return mapper.toDto(newEntity);
    }
    
    
    /**
     * Thực hiện chức năng update ...
     * @requestbody LanguagesModel, 
     * @pathvariable id,
     * @return ResponseEntity
     */
    @Override
    public ResponseEntity<?> update(LanguagesModel model, Long id) {
        LanguagesDto languagesDto = mapper.toDto(repository.findById(id).get());
        if (languagesDto == null) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Record not found");
        } else {
            languagesDto.setName(model.getName());
            languagesDto.setCode(model.getCode().toUpperCase());
            languagesDto.setDescription(model.getDescription());
            languagesDto.setCountryId(model.getCountryId());
            repository.save(mapper.toModel(languagesDto));
            return new ResponseEntity<>(languagesDto, HttpStatus.OK);  
        }
    }
    
    
    /**
     * Thực hiện chức năng detail ...
     * @pathvariable id
     * @return ResponseEntity
     */
    @Override
    public ResponseEntity<?> detail(Long id) {
        LanguagesDto languagesDto = mapper.toDto(repository.findById(id).get());
        if (languagesDto == null) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Record not found");
        } else {
            return new ResponseEntity<>(languagesDto, HttpStatus.OK);  
        }
    }
    
    
    /**
     * Thực hiện chức năng delete ...
     * @pathvariable id
     * @return ResponseEntity
     */
    @Override
    public ResponseEntity<?> delete(Long id) {
        Languages languages = repository.findById(id).get();
        if (languages == null) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Record not found");
        } else {
            languages.setDeleted(true);
            repository.save(languages);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body("Delete successfully");
        }
    }
    
    
    /**
     * Thực hiện chức năng get list ...
     * @param pageable
     * @return Paging<LanguagesDto> <= 20 record
     */
    @Override
    public Paging<LanguagesDto> getList(Pageable pageable) {
        JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);
        QLanguages q = QLanguages.languages;
        List<Languages> languages = queryFactory.selectFrom(q)
                .where(q.deleted.eq(false))
                .fetch();
        Page<Languages> pages = new PageImpl<Languages>(languages, pageable, 5);
        Page<LanguagesDto> result = pages.map(mapper::toDto);
        return Paging.of(result);
    }

    /**
     * Thực hiện chức năng search ...
     * @param pageable, @PathVariable countryId
     * @return Paging<LanguagesDto> <= 20 record
     */
    @Override
    public Paging<LanguagesDto> getListSearchLanguagesOfCountry(Pageable pageable, Long countryId) {
        JPAQuery<Languages> query = new JPAQuery<Languages>(entityManager);
        QLanguages q = QLanguages.languages;
        query.from(q).where(q.deleted.isFalse());
        if(countryId != null) {
            query.where(q.countryId.eq(countryId));
        }
        Page<Languages> pages = new PageImpl<Languages>(query.fetch(), pageable, 5);
        Page<LanguagesDto> result = pages.map(mapper::toDto);
        return Paging.of(result);
    }
    
    /**
     * Thực hiện chức năng check code ...
     * @param code
     * @return Boolean
     */
    public Boolean checkCode(String code) {
        JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);
        QLanguages q = QLanguages.languages;
        List<Languages> languages = queryFactory.selectFrom(q)
                .where(q.deleted.eq(false)).fetch();
        List<String> codes = new ArrayList<>();
        for (Languages language : languages) {
            codes.add(language.getCode());
        }
        return codes.contains(code.toUpperCase());
    }
}
