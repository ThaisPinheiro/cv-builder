package com.cvbuilder.user.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.validator.constraints.URL;

@Data
public class UserDto {
    
    @NotBlank(message = "The Name field is required.")
    @Size(max = 30, message = "The Name field must contain up to 30 characters.")
    private String name;

    @NotBlank(message = "The Sobrenome field is required.")
    @Size(max = 50, message = "The Surname field must contain up to 50 characters.")
    private String surname;

    @NotBlank(message = "The Telefone field is required.")
    @Pattern(regexp = "\\d+", message = "The Telefone field must contain digits only.")
    @Size(max = 12, message = "The Phone Number field must contain up to 12 digits.")
    private String telephoneNumber;

    @Email
    @NotBlank(message = "The E-mail field is required.")
    @Size(max = 50)
    private String email;

    @URL
    @Size(max = 200)
    private String socialAccount;

    @URL
    @Size(max = 200)
    private String portfolio;

    @Size(max = 2000, message = "Your description must contain up to 2000 characters.")
    private String aboutMe;

}