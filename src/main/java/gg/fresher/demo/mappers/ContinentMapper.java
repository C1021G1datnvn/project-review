package gg.fresher.demo.mappers;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import gg.fresher.demo.dtos.ContinentDto;
import gg.fresher.demo.entities.Continent;
import gg.fresher.demo.service.model.ContinentModel;

@Mapper(uses = LanguagesMapper.class)
public interface ContinentMapper {
    

    ContinentDto toDto(Continent entity);

    @BeanMapping(ignoreByDefault = true)
    @Mapping(source = "name", target = "name")
    @Mapping(source = "code", target = "code")
    @Mapping(source = "description", target = "description")
    
    Continent toEntity(ContinentModel entity);
    
    Continent toModel(ContinentDto model);
    
    default Continent fromId(Long id) {
        if (id == null) {
            return null;
        }
        Continent danToc = new Continent();
        danToc.setId(id);
        return danToc;
    }
}
