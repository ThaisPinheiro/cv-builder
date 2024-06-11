
package com.cvbuilder.resume.dtos;

import java.util.UUID;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ResumeDto {
    @NotNull(message = "O campo user_id é obrigatório")
    private UUID userId;

    @NotBlank(message = "O campo descrição é obrigatório")
    @Size(max = 2000, message = "O campo descrição deve conter até 30 caracteres")
    private String description;

}