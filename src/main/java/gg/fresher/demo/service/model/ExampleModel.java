/**
 * 
 */
package gg.fresher.demo.service.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @created Mar 2, 2023
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExampleModel {

    @NotBlank
    @Size(min = 5, max = 255)
    private String name;

    @NotBlank
    @Size(max = 100)
    private String code;

    @Size(max = 500)
    private String description;
}
