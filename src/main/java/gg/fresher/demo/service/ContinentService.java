package gg.fresher.demo.service;

import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import gg.fresher.demo.dtos.ContinentDto;
import gg.fresher.demo.service.model.ContinentModel;
import gg.fresher.demo.utils.Paging;
/**
 * @created Mar 13, 2023
 *
 */
public interface ContinentService {
    /**
     * @param model
     * @return list continent with size <= 20 record
     */
    ContinentDto create(ContinentModel model);

    /**
     * @param model, @PathVariable id
     * @return list continent with size <= 20 record
     */
    ResponseEntity<?> update(ContinentModel model, Long id);
    
    /**
     * @pathvariable  id
     * @return 1 record continent
     */
    ResponseEntity<?> detail(Long id);
    
    /**
     * @pathvariable id
     * @return 1 record continent
     */
    ResponseEntity<?> delete(Long id);
    
    /**
     * @param pageable
     * @return list continent with size <= 20 record
     */
    Paging<ContinentDto> getList(Pageable pageable);
}
