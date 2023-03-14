package gg.fresher.demo.dtos;

import lombok.Data;
/**
 * @created Mar 13, 2023
 *
 */
@Data
public class LanguagesDto {
    private Long id;

    private String name;

    private String code;
    
    private String description;
    
    private Long countryId;
}
