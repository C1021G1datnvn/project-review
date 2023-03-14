package gg.fresher.demo.mappers;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import gg.fresher.demo.dtos.LanguagesDto;
import gg.fresher.demo.entities.Languages;
import gg.fresher.demo.service.model.LanguagesModel;
/**
 * @created Mar 13, 2023
 *
 */
@Mapper
public interface LanguagesMapper {
    
    LanguagesDto toDto(Languages entity);

    @BeanMapping(ignoreByDefault = true)
    @Mapping(source = "name", target = "name")
    @Mapping(source = "code", target = "code")
    @Mapping(source = "description", target = "description")
    
    @Mapping(source = "countryId", target = "countryId")
    Languages toEntity(LanguagesModel entity);
    
    @Mapping(source = "countryId", target = "countryId")
    Languages toModel(LanguagesDto model);
    
    default Languages fromId(Long id) {
        if (id == null) {
            return null;
        }
        Languages ngonNgu = new Languages();
        ngonNgu.setId(id);
        return ngonNgu;
    }
}
