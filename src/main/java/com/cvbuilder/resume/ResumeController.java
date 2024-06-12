package com.cvbuilder.resume;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.cvbuilder.resume.dtos.ResumeDto;
import com.cvbuilder.validators.ValidatorHandler;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/resume")
public class ResumeController {
  
  final ResumeService resumeService;
  final ValidatorHandler validatorHandler;

  public ResumeController(ResumeService resumeService, ValidatorHandler validatorHandler) {
    this.resumeService = resumeService;
    this.validatorHandler = validatorHandler;
  }

  @PostMapping
  public ResponseEntity<?>createResume(@RequestBody @Valid ResumeDto resumeDto, BindingResult bindingResult) {

    ResponseEntity<Object> errorResponse = validatorHandler.handleBindingResultErrors(bindingResult);
    if (errorResponse != null) {
      return errorResponse;
    }

    if (resumeDto.getUserId() == null) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("The UserId field is required.");
    }

    ResumeDto savedResumeDto = resumeService.create(resumeDto);
    return ResponseEntity.status(HttpStatus.CREATED).body(savedResumeDto.getDescription());
  }

}