package gg.fresher.demo.mappers;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import gg.fresher.demo.dtos.CountryDto;
import gg.fresher.demo.entities.Country;
import gg.fresher.demo.service.model.CountryModel;
/**
 * @created Mar 13, 2023
 *
 */
@Mapper
public interface CountryMapper {
    
    CountryDto toDto(Country entity);

    @BeanMapping(ignoreByDefault = true)
    @Mapping(source = "name", target = "name")
    @Mapping(source = "code", target = "code")
    @Mapping(source = "description", target = "description")
    
    @Mapping(source = "continentId", target = "continentId")
    Country toEntity(CountryModel entity);
    
    @Mapping(source = "continentId", target = "continentId")
    Country toModel(CountryDto model);
    
    default Country fromId(Long id) {
        if (id == null) {
            return null;
        }
        Country quocGia = new Country();
        quocGia.setId(id);
        return quocGia;
    }
}
