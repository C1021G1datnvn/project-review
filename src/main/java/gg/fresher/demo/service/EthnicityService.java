package gg.fresher.demo.service;

import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import gg.fresher.demo.dtos.CountryDto;
import gg.fresher.demo.dtos.EthnicityDto;
import gg.fresher.demo.service.model.EthnicityModel;
import gg.fresher.demo.utils.Paging;
/**
 * @created Mar 13, 2023
 *
 */
public interface EthnicityService {
    /**
     * @param model
     * @return list Ethnicity with size <= 20 record
     */
    EthnicityDto create(EthnicityModel model);

    /**
     * @param model, @PathVariable id
     * @return list Ethnicity with size <= 20 record
     */
    ResponseEntity<?> update(EthnicityModel model, Long id);
    
    /**
     * @pathvariable  id
     * @return 1 record Ethnicity
     */
    ResponseEntity<?> detail(Long id);
    
    /**
     * @pathvariable id
     * @return 1 record Ethnicity
     */
    ResponseEntity<?> delete(Long id);
    
    /**
     * @requestparam  id
     * @return list record Ethnicity
     */
    Paging<EthnicityDto> getListSearchEthnicityOfCountry(Pageable pageable, Long countryId);
    
    /**
     * @requestparam  id
     * @return list record Ethnicity
     */
    Paging<EthnicityDto> getListSearchEthnicityOfContinent(Pageable pageable, Long continentId);
    
    /**
     * @param pageable
     * @return list Ethnicity with size <= 20 record
     */
    Paging<EthnicityDto> getList(Pageable pageable);
}
