package com.cvbuilder.personalData.controllers;

import com.cvbuilder.personalData.services.PersonalDataService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@CrossOrigin() -> Verificar caso de uso da aplicação
@RequestMapping("/personal-data")
public class PersonalDataController {
    //    ponto de injeção da service
    final PersonalDataService personalDataService;

    public PersonalDataController(PersonalDataService personalDataService) {
        this.personalDataService = personalDataService;
    }

}
