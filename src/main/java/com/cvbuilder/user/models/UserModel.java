package com.cvbuilder.user.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import com.cvbuilder.resume.ResumeModel;
import java.time.Instant;

@Entity
@Getter
@Setter
@Table(name = "user_resume")
public class UserModel implements Serializable {
    
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false, length = 30)
    private String name;

    @Column(nullable = false, length = 50)
    private String surname;

    @Column(nullable = false, length = 12)
    private String telephoneNumber;

    @Column(nullable = false, length = 50)
    private String email;

    @Column(length = 200)
    private String socialAccount;

    @Column(length = 200)
    private String portfolio;

    @Column(length = 2000)
    private String aboutMe;

    @CreationTimestamp
    private Instant createdAt;

    @UpdateTimestamp
    private Instant updatedAt;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "userResume")
    private List<ResumeModel> resumes;

}