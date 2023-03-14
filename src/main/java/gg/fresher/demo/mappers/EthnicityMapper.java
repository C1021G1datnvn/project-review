package gg.fresher.demo.mappers;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import gg.fresher.demo.dtos.EthnicityDto;
import gg.fresher.demo.entities.Ethnicity;
import gg.fresher.demo.service.model.EthnicityModel;
/**
 * @created Mar 13, 2023
 *
 */
@Mapper
public interface EthnicityMapper {
    EthnicityDto toDto(Ethnicity entity);

    @BeanMapping(ignoreByDefault = true)
    @Mapping(source = "name", target = "name")
    @Mapping(source = "code", target = "code")
    @Mapping(source = "description", target = "description")
    
    @Mapping(source = "continentId", target = "continentId")
    @Mapping(source = "countryId", target = "countryId")
    Ethnicity toEntity(EthnicityModel entity);
    
    @Mapping(source = "continentId", target = "continentId")
    @Mapping(source = "countryId", target = "countryId")
    Ethnicity toModel(EthnicityDto model);
    
    default Ethnicity fromId(Long id) {
        if (id == null) {
            return null;
        }
        Ethnicity chauLuc = new Ethnicity();
        chauLuc.setId(id);
        return chauLuc;
    }

}
