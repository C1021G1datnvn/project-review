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

import gg.fresher.demo.dtos.LanguagesDto;
import gg.fresher.demo.service.LanguagesService;
import gg.fresher.demo.service.model.LanguagesModel;
import gg.fresher.demo.utils.Paging;
import io.swagger.v3.oas.annotations.Operation;
/**
 * @created Mar 13, 2023
 *
 */
@RestController
@RequestMapping("/api/languages")
public class LanguagesController {
    @Autowired
    private LanguagesService service;
    
    @Autowired
    private Validator validator;
    
    
    @Operation(summary = "Create Languages")
    @PostMapping("/create")
    public ResponseEntity<?> createLanguages(@RequestBody @Valid LanguagesModel model, BindingResult bindingResult) {
        try{
            validator.validate(model, bindingResult);
            if(bindingResult.hasErrors()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Create language unsuccessfully");
            }
            service.create(model);
            return ResponseEntity.status(HttpStatus.CREATED).body("Create language successfully");
        } catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Bad request", e);
        }
    }

    @Operation(summary = "get list of languages")
    @GetMapping("/list")
    public ResponseEntity<Paging<LanguagesDto>> getListLanguages(@ParameterObject Pageable pageable) {
        try {
            return ResponseEntity.ok().body(service.getList(pageable));
        } catch (Exception e){
            throw new ResponseStatusException(
                    HttpStatus.NO_CONTENT, "No content", e);
        }
        
        
    }
    
    @Operation(summary = "update Languages")
    @PatchMapping("/update/{id}")
    public ResponseEntity<?> updateLanguages(@RequestBody @Valid LanguagesModel model, BindingResult bindingResult, @PathVariable Long id) {
        try {
            validator.validate(model, bindingResult);
            if(bindingResult.hasErrors()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Update language unsuccessfully");
            }
            service.update(model, id);
            return ResponseEntity.status(HttpStatus.CREATED).body("Update language successfully");
        } catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Bad request", e);
        }
        
    }
    
    @Operation(summary = "delete Languages")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteLanguages(@PathVariable Long id) {
        try {
            return service.delete(id);
        } catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Not found", e);
        }
    }
    
    @Operation(summary = "detail Languages")
    @GetMapping("/detail/{id}")
    public ResponseEntity<?> detailLanguages(@PathVariable Long id) {
        try {
            return service.detail(id);
        } catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Not found", e);
        }
    }
    
    @Operation(summary = "get list of Country")
    @GetMapping("/find")
    public ResponseEntity<Paging<LanguagesDto>> getLanguages( @ParameterObject Pageable pageable, @RequestParam(required = false) Long countryId) {
        try {
            return ResponseEntity.ok().body(service.getListSearchLanguagesOfCountry(pageable, countryId));
        } catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.NO_CONTENT, "No content", e);
        }
        
    }
}
