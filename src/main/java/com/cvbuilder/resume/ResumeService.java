package com.cvbuilder.resume;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.cvbuilder.resume.dtos.ResumeDto;
import com.cvbuilder.user.UserModel;
import com.cvbuilder.user.UserService;

@Service
public class ResumeService {
  
  final UserService userService;
  final ResumeRepository resumeRepository;

  public ResumeService(ResumeRepository resumeRepository, UserService userService) {
    this.resumeRepository = resumeRepository;
    this.userService = userService;
  }

  public List<ResumeModel> findAll() {
    return resumeRepository.findAll();
  }

  public Optional<ResumeModel> findById(UUID id){
    return resumeRepository.findById(id);
  }

  @Transactional
  public ResumeDto create(ResumeDto resumeDto) {
    ResumeModel resumeModel = new ResumeModel();
    BeanUtils.copyProperties(resumeDto, resumeModel);

    // @TODO: Pensar em um jeito de não precisar retornar a model
    UserModel user = userService.findByIdModel(resumeDto.getUserId());
    resumeModel.setUserResume(user);
  
    ResumeModel savedResumeModel = resumeRepository.save(resumeModel);

    ResumeDto savedResumeDto = new ResumeDto();
    BeanUtils.copyProperties(savedResumeModel, savedResumeDto);
    return savedResumeDto;
  }

}