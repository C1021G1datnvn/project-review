/**
 * 
 */
package gg.fresher.demo.web.rest;

import javax.validation.Valid;

import org.springdoc.api.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import gg.fresher.demo.dtos.ExampleDto;
import gg.fresher.demo.service.ExampleService;
import gg.fresher.demo.service.model.ExampleModel;
import gg.fresher.demo.utils.Paging;
import io.swagger.v3.oas.annotations.Operation;

/**
 * @created Mar 2, 2023
 *
 */
@RestController
@RequestMapping("/api/example")
public class ExampleController {

    @Autowired
    private ExampleService service;

    @Operation(summary = "Create example")
    @PostMapping
    public ResponseEntity<ExampleDto> createExample(@RequestBody @Valid ExampleModel model) {
        return ResponseEntity.ok().body(service.create(model));
    }

    @Operation(summary = "get list of examples")
    @GetMapping
    public ResponseEntity<Paging<ExampleDto>> getListExample(@ParameterObject Pageable pageable) {
        return ResponseEntity.ok().body(service.getList(pageable));
    }
}
