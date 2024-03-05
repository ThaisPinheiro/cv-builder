package com.cvbuilder.resume;

import org.springframework.stereotype.Repository;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface ResumeRepository extends JpaRepository<ResumeModel, UUID>{
  
}
