package com.cvbuilder.personalData.services;

import com.cvbuilder.personalData.models.PersonalDataModel;
import com.cvbuilder.personalData.repositories.PersonalDataRepository;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
public class PersonalDataService { // só conversa com repository
    //    ponto de injeção da repository
    final PersonalDataRepository personalDataRepository;

    public PersonalDataService(PersonalDataRepository personalDataRepository) {
        this.personalDataRepository = personalDataRepository;
    }

    @Transactional
    public PersonalDataModel save(PersonalDataModel personalDataModel) {
        return personalDataRepository.save(personalDataModel);
    }

    public List<PersonalDataModel> findAll() {
        return personalDataRepository.findAll();
    }

    public Optional<PersonalDataModel> findById(UUID id) {
        return personalDataRepository.findById(id);
    }

}