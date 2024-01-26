package com.cvbuilder.personalData.services;

import com.cvbuilder.personalData.repositories.PersonalDataRepository;
import org.springframework.stereotype.Service;

@Service
public class PersonalDataService {
    //    ponto de injeção da repository
    final PersonalDataRepository personalDataRepository;

    public PersonalDataService(PersonalDataRepository personalDataRepository) {
        this.personalDataRepository = personalDataRepository;
    }
}
