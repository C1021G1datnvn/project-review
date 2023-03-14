package gg.fresher.demo.service;

import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import gg.fresher.demo.dtos.CountryDto;
import gg.fresher.demo.service.model.CountryModel;
import gg.fresher.demo.utils.Paging;
/**
 * @created Mar 13, 2023
 *
 */
public interface CountryService {
    /**
     * @param model
     * @return list Country with size <= 20 record
     */
    CountryDto create(CountryModel model);

    /**
     * @param model, @PathVariable id
     * @return list Country with size <= 20 record
     */
    ResponseEntity<?> update(CountryModel model, Long id);
    
    /**
     * @pathvariable  id
     * @return 1 record Country
     */
    ResponseEntity<?> detail(Long id);
    
    /**
     * @requestparam  id
     * @return list record continent
     */
    Paging<CountryDto> getListSearchCountryOfContinent(Pageable pageable, Long continentId);
    
    /**
     * @pathvariable id
     * @return 1 record Country
     */
    ResponseEntity<?> delete(Long id);
    
    /**
     * @param pageable
     * @return list Country with size <= 20 record
     */
    Paging<CountryDto> getList(Pageable pageable);
}
