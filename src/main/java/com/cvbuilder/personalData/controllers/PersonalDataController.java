package com.cvbuilder.personalData.controllers;

import com.cvbuilder.personalData.dtos.PersonalDataDto;
import com.cvbuilder.personalData.models.PersonalDataModel;
import com.cvbuilder.personalData.services.PersonalDataService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@RestController
//@CrossOrigin() -> Verificar caso de uso da aplicação
@RequestMapping("/personal-data")
public class PersonalDataController { // só conversa com service

    final PersonalDataService personalDataService;

    public PersonalDataController(PersonalDataService personalDataService) {
        this.personalDataService = personalDataService;
    }

    @PostMapping
    public ResponseEntity<?>createPersonalData(@RequestBody @Valid PersonalDataDto personalDataDto,
                                               BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage());
        }
        PersonalDataModel personalDataModel = new PersonalDataModel();
        BeanUtils.copyProperties(personalDataDto, personalDataModel);
        PersonalDataModel savePersonalData = personalDataService.save(personalDataModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(savePersonalData);
    }

    @GetMapping
    public ResponseEntity<List<PersonalDataModel>> getPersonalData() {
        return ResponseEntity.status(HttpStatus.OK).body(personalDataService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getPersonalDataId(@PathVariable(value = "id") UUID id) {
        Optional<PersonalDataModel> optionalPersonalDataModel = personalDataService.findById(id);
        if (!optionalPersonalDataModel.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Personal Data not found");
        }
        return ResponseEntity.status(HttpStatus.OK).body(optionalPersonalDataModel.get());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updatePersonalDataId(@PathVariable(value = "id") UUID id, @RequestBody @Valid
                                  PersonalDataDto personalDataDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage());
        }
        Optional<PersonalDataModel> optionalPersonalDataModel = personalDataService.findById(id);
        if (!optionalPersonalDataModel.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Personal Data not found");
        }
        PersonalDataModel personalDataModel = new PersonalDataModel();
        BeanUtils.copyProperties(personalDataDto, personalDataModel);
        personalDataModel.setId(optionalPersonalDataModel.get().getId());
        PersonalDataModel savePersonalData = personalDataService.save(personalDataModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(savePersonalData);
    }
}
