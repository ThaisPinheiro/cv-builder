package com.cvbuilder.resume;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import java.io.Serializable;
import java.util.UUID;
import com.cvbuilder.user.models.UserModel;

@Entity
@Getter
@Setter
@Table(name = "resume")
public class ResumeModel implements Serializable {
  
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private UUID id;

  @Column(nullable = false, length = 30)
  private String description;

  @ManyToOne
  @JoinColumn(name = "user_id", nullable = false)
  private UserModel user;

}
