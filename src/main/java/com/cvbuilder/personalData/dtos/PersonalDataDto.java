package com.cvbuilder.personalData.dtos;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.validator.constraints.URL;

@Data
public class PersonalDataDto {
    @NotBlank(message = "O campo Nome é obrigatório")
    @Size(max = 30, message = "O campo Nome deve conter até 30 caracteres")
    private String name;

    @NotBlank(message = "O campo Sobrenome é obrigatório")
    @Size(max = 50, message = "O campo Sobrenome deve conter até 50 caracteres")
    private String surname;

    @NotBlank(message = "O campo Telefone é obrigatório")
    @Pattern(regexp = "\\d+", message = "O campo 'telefone' deve conter apenas dígitos")
    @Size(max = 12, message = "O campo Telefone deve conter até 12 digitos")
    private String telephoneNumber;

    @Email
    @NotBlank(message = "O campo E-mail é obrigatório")
    @Size(max = 50)
    private String email;

    @URL
    @Size(max = 200)
    private String socialAccount;

    @URL
    @Size(max = 200)
    private String portfolio;

}