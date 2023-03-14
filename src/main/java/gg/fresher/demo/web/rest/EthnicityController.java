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

import gg.fresher.demo.dtos.EthnicityDto;
import gg.fresher.demo.service.EthnicityService;
import gg.fresher.demo.service.model.EthnicityModel;
import gg.fresher.demo.utils.Paging;
import io.swagger.v3.oas.annotations.Operation;
/**
 * @created Mar 13, 2023
 *
 */
@RestController
@RequestMapping("/api/ethnicity")
public class EthnicityController {
    
    @Autowired
    private EthnicityService service;
    
    @Autowired
    private Validator validator;
    
    @Operation(summary = "Create ethnicity")
    @PostMapping("/create")
    public ResponseEntity<?> createEthnicity(@RequestBody @Valid EthnicityModel model, BindingResult bindingResult) {
        try {
            validator.validate(model, bindingResult);
            if(bindingResult.hasErrors()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Create ethnicity unsuccessfully");
            }
            service.create(model);
            return ResponseEntity.status(HttpStatus.CREATED).body("Create ethnicity successfully");
        } catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Bad request", e);
        }
        
    }

    @Operation(summary = "get list of ethnicity")
    @GetMapping("/list")
    public ResponseEntity<Paging<EthnicityDto>> getListEthnicity(@ParameterObject Pageable pageable) {
        try {
            return ResponseEntity.ok().body(service.getList(pageable));
        } catch (Exception e){
            throw new ResponseStatusException(
                    HttpStatus.NO_CONTENT, "No content", e);
        }
    }
    
    @Operation(summary = "update ethnicity")
    @PatchMapping("/update/{id}")
    public ResponseEntity<?> updateEthnicity(@RequestBody @Valid EthnicityModel model, BindingResult bindingResult, @PathVariable Long id) {
        try {
            validator.validate(model, bindingResult);
            if(bindingResult.hasErrors()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Update ethnicity unsuccessfully");
            }
            service.update(model, id);
            return ResponseEntity.status(HttpStatus.CREATED).body("Update ethnicity successfully");
        } catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Bad request", e);
        }
        
    }
    
    @Operation(summary = "delete ethnicity")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteEthnicity(@PathVariable Long id) {
        try {
            return service.delete(id);
        } catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Not found", e);
        }
    }
    
    @Operation(summary = "detail Ethnicity")
    @GetMapping("/detail/{id}")
    public ResponseEntity<?> detailEthnicity(@PathVariable Long id) {
        try {
            return service.detail(id);
        } catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Not found", e);
        }
    }
    
    @Operation(summary = "get list of Country")
    @GetMapping("/findCountry")
    public ResponseEntity<Paging<EthnicityDto>> getEthnicityOfCountry( @ParameterObject Pageable pageable, @RequestParam(required = false) Long countryId) {
        try {
            return ResponseEntity.ok().body(service.getListSearchEthnicityOfCountry(pageable, countryId));
        } catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.NO_CONTENT, "No content", e);
        }
        
    }
    
    @Operation(summary = "get list of continent")
    @GetMapping("/findContinent")
    public ResponseEntity<Paging<EthnicityDto>> getEthnicityOfContinent( @ParameterObject Pageable pageable, @RequestParam(required = false) Long continentId) {
        try {
            return ResponseEntity.ok().body(service.getListSearchEthnicityOfContinent(pageable, continentId));
        } catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.NO_CONTENT, "No content", e);
        }
    }
}
