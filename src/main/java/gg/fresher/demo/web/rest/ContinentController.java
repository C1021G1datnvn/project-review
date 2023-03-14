package gg.fresher.demo.web.rest;

import javax.validation.Valid;

import org.springdoc.api.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import gg.fresher.demo.dtos.ContinentDto;
import gg.fresher.demo.service.ContinentService;
import gg.fresher.demo.service.model.ContinentModel;
import gg.fresher.demo.utils.Paging;
import io.swagger.v3.oas.annotations.Operation;
/**
 * @created Mar 13, 2023
 *
 */
@RestController
@RequestMapping("/api/continent")
public class ContinentController {
    @Autowired
    private ContinentService service;
    
    @Autowired
    private Validator validator;
    
    @Operation(summary = "Create Continent")
    @PostMapping("/create")
    public ResponseEntity<?> createContinent(@RequestBody @Valid ContinentModel model, BindingResult bindingResult) {
        try {
            validator.validate(model, bindingResult);
            if(bindingResult.hasErrors()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Create continent unsuccessfully");
            }
            service.create(model);
            return ResponseEntity.status(HttpStatus.CREATED).body("Create continent successfully");
        } catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Bad request", e);
        }
        
    }

    @Operation(summary = "get list of Continent")
    @GetMapping("/list")
    public ResponseEntity<Paging<ContinentDto>> getListContinent(@ParameterObject Pageable pageable) {
        try {
            return ResponseEntity.ok().body(service.getList(pageable));
        } catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.NO_CONTENT, "No content", e);
        }
        
    }
    
    @Operation(summary = "update Continent")
    @PatchMapping("/update/{id}")
    public ResponseEntity<?> updateContinent(@RequestBody @Valid ContinentModel model, BindingResult bindingResult, @PathVariable Long id) {
        try {
            validator.validate(model, bindingResult);
            if(bindingResult.hasErrors()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Update continent unsuccessfully");
            }
            service.update(model, id);
            return ResponseEntity.status(HttpStatus.CREATED).body("Update continent successfully");
        } catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Bad request", e);
        }
        
    }
    
    @Operation(summary = "delete Continent")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteContinent(@PathVariable Long id) {
        try {
            return service.delete(id);
        } catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Not found", e);
        }
    }
    
    @Operation(summary = "detail Continent")
    @GetMapping("/detail/{id}")
    public ResponseEntity<?> detailContinent(@PathVariable Long id) {
        try {
            return service.detail(id);
        } catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Not found", e);
        }
    }
}
