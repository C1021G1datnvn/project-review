/**
 * 
 */
package gg.fresher.demo.service;

import org.springframework.data.domain.Pageable;

import gg.fresher.demo.dtos.ExampleDto;
import gg.fresher.demo.service.model.ExampleModel;
import gg.fresher.demo.utils.Paging;

/**
 * @created Mar 2, 2023
 *
 */
public interface ExampleService {

    /**
     * @param model
     * @return
     */
    ExampleDto create(ExampleModel model);

    /**
     * @param pageable
     * @return
     */
    Paging<ExampleDto> getList(Pageable pageable);

}
