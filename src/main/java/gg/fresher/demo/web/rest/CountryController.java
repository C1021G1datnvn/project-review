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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import gg.fresher.demo.dtos.CountryDto;
import gg.fresher.demo.service.CountryService;
import gg.fresher.demo.service.model.CountryModel;
import gg.fresher.demo.utils.Paging;
import io.swagger.v3.oas.annotations.Operation;
/**
 * @created Mar 13, 2023
 *
 */
@RestController
@RequestMapping("/api/country")
public class CountryController {
    
    @Autowired
    private CountryService service;
    
    @Autowired
    private Validator validator;
    
    @Operation(summary = "Create Country")
    @PostMapping("/create")
    public ResponseEntity<?> createCountry(@RequestBody @Valid CountryModel model, BindingResult bindingResult) {
        try {
            validator.validate(model, bindingResult);
            if(bindingResult.hasErrors()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Create country unsuccessfully");
            }
            service.create(model);
            return ResponseEntity.status(HttpStatus.CREATED).body("Create country successfully");
        } catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Bad request", e);
        }
        
    }

    @Operation(summary = "get list of Country")
    @GetMapping("/list")
    public ResponseEntity<Paging<CountryDto>> getListCountry(@ParameterObject Pageable pageable) {
        try {
            return ResponseEntity.ok().body(service.getList(pageable));
        } catch (Exception e){
            throw new ResponseStatusException(
                    HttpStatus.NO_CONTENT, "No content", e);
        }
    }
    
    @Operation(summary = "update Country")
    @PatchMapping("/update/{id}")
    public ResponseEntity<?> updateCountry(@RequestBody @Valid CountryModel model, BindingResult bindingResult, @PathVariable Long id) {
        try {
            validator.validate(model, bindingResult);
            if(bindingResult.hasErrors()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Update country unsuccessfully");
            }
            service.update(model, id);
            return ResponseEntity.status(HttpStatus.CREATED).body("Update country successfully");
        } catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Bad request", e);
        }
        
    }
    
    @Operation(summary = "delete Country")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteCountry(@PathVariable Long id) {
        try {
            return service.delete(id);
        } catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Not found", e);
        }
    }
    
    @Operation(summary = "detail Country")
    @GetMapping("/detail/{id}")
    public ResponseEntity<?> detailCountry(@PathVariable Long id) {
        try {
            return service.detail(id);
        } catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Not found", e);
        }
    }
    
    @Operation(summary = "get list of Country")
    @GetMapping("/find")
    public ResponseEntity<Paging<CountryDto>> getCountry( @ParameterObject Pageable pageable, @RequestParam(required = false) Long continentId) {
        try {
            return ResponseEntity.ok().body(service.getListSearchCountryOfContinent(pageable, continentId));
        } catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.NO_CONTENT, "No content", e);
        }
    }
}
