package gg.fresher.demo.service;

import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import gg.fresher.demo.dtos.LanguagesDto;
import gg.fresher.demo.service.model.LanguagesModel;
import gg.fresher.demo.utils.Paging;
/**
 * @created Mar 13, 2023
 *
 */
public interface LanguagesService {
    /**
     * @param model
     * @return list Language with size <= 20 record
     */
    LanguagesDto create(LanguagesModel model);

    /**
     * @param model, @PathVariable id
     * @return list Language with size <= 20 record
     */
    ResponseEntity<?> update(LanguagesModel model, Long id);
    
    /**
     * @pathvariable  id
     * @return 1 record Language
     */
    ResponseEntity<?> detail(Long id);
    
    /**
     * @requestparam  id
     * @return list record Languages
     */
    Paging<LanguagesDto> getListSearchLanguagesOfCountry(Pageable pageable, Long countryId);
    
    /**
     * @pathvariable id
     * @return 1 record Language
     */
    ResponseEntity<?> delete(Long id);
    
    /**
     * @param pageable
     * @return list Language with size <= 20 record
     */
    Paging<LanguagesDto> getList(Pageable pageable);
}
