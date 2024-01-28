package com.cvbuilder.personalData.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "personal_data")
public class PersonalDataModel implements Serializable {
    
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false, length = 30)
    private String name;

    @Column(nullable = false, length = 50)
    private String surname;

    @Column(nullable = false, length = 12)
    private String telephoneNumber;

    @Column(nullable = false, length = 50)
    private String email;

    @Column(length = 200)
    private String socialAccount;

    @Column(length = 200)
    private String portfolio;

}