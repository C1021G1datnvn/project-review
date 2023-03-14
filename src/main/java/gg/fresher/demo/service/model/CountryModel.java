package gg.fresher.demo.service.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * @created Mar 13, 2023
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CountryModel implements Validator{
    @NotBlank
    @Size(min = 5, max = 255)
    private String name;

    @Size(max = 100)
    @Pattern(regexp = "^[A-Z]{2}(-[0-9]{3})?$")
    private String code;

    @Size(max = 500)
    private String description;
    
    private Long continentId;

    @Override
    public boolean supports(Class<?> clazz) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void validate(Object obj, Errors e) {
        ValidationUtils.rejectIfEmpty(e, "code", "code.empty");
        CountryModel p = (CountryModel) obj;
        if (p.getCode().isEmpty()) {
            e.rejectValue("code", "Code cannot be empty");
        } else if (p.getCode().length() > 100) {
            e.rejectValue("code", "Code size must be less than 100 characters");
        }
    }
}
