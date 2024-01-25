package com.cvbuilder.dtos;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.validator.constraints.URL;

@Data
public class PersonalDataDto {
    @NotBlank
    @Size(max = 30)
    private String name;

    @NotBlank
    @Size(max = 50)
    private String surname;

    @NotBlank
    @Size(max = 12)
    private String telephoneNumber;

    @Email
    @NotBlank
    @Size(max = 50)
    private String email;

    @URL
    @Size(max = 200)
    private String socialAccount;

    @URL
    @Size(max = 200)
    private String portfolio;

}
