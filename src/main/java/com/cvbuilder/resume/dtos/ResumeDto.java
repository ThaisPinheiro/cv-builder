
package com.cvbuilder.resume.dtos;

import java.util.UUID;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ResumeDto {
  
  @NotNull(message = "The UserId field is required.")
  private UUID userId;

  @NotBlank(message = "The Description field is required.")
  @Size(max = 2000, message = "The Description field must contain up to 30 characters.")
  private String description;

}