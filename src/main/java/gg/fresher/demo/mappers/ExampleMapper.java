/**
 * 
 */
package gg.fresher.demo.mappers;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import gg.fresher.demo.dtos.ExampleDto;
import gg.fresher.demo.entities.Example;
import gg.fresher.demo.service.model.ExampleModel;

/**
 * @created Mar 2, 2023
 *
 */
@Mapper
public interface ExampleMapper {

    ExampleDto toDto(Example entity);

    @BeanMapping(ignoreByDefault = true)
    @Mapping(source = "name", target = "name")
    @Mapping(source = "code", target = "code")
    @Mapping(source = "description", target = "description")
    
    Example toEntity(ExampleModel model);
}
