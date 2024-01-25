package com.cvbuilder.repositories;

import com.cvbuilder.models.PersonalDataModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

//acesso aos métodos para transações com o bd.
public interface PersonalDataRepository extends JpaRepository<PersonalDataModel, UUID> {
}
