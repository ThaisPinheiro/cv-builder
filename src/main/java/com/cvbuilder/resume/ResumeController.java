package com.cvbuilder.resume;

import java.util.Objects;

import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.cvbuilder.resume.dtos.ResumeDto;
import com.cvbuilder.user.UserRepository;
import com.cvbuilder.user.models.UserModel;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/resume")
public class ResumeController {
  final ResumeService resumeService;
  final UserRepository userRepository;

  public ResumeController(ResumeService resumeService, UserRepository userRepository) {
    this.resumeService = resumeService;
    this.userRepository = userRepository;
  }

  @PostMapping
  public ResponseEntity<?>createResume(@RequestBody @Valid ResumeDto resumeDto,
  BindingResult bindingResult) {

    if (bindingResult.hasErrors()) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage());
    }

    if (resumeDto.getUserId() == null) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("O campo userId é obrigatório");
    }

    ResumeModel resumeModel = new ResumeModel();
    BeanUtils.copyProperties(resumeDto, resumeModel);

    UserModel user = userRepository.findById(resumeDto.getUserId())
    .orElseThrow();
    resumeModel.setUserResume(user);
  
    ResumeModel savedResumeModel = resumeService.save(resumeModel);

    ResumeDto savedResumeDto = new ResumeDto();
    BeanUtils.copyProperties(savedResumeModel, savedResumeDto);
    return ResponseEntity.status(HttpStatus.CREATED).body(savedResumeDto);
  }
  
}