package com.cvbuilder.resume;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

@Service
public class ResumeService {
  final ResumeRepository resumeRepository;

  public ResumeService(ResumeRepository resumeRepository) {
    this.resumeRepository = resumeRepository;
  }

  public ResumeModel save(ResumeModel resumeModel) {
    return resumeRepository.save(resumeModel);
  }

  public List<ResumeModel> findAll() {
    return resumeRepository.findAll();
  }

  public Optional<ResumeModel> findById(UUID id){
    return resumeRepository.findById(id);
  }

}
