package com.cvbuilder.user;

import com.cvbuilder.user.models.UserModel;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService { // só conversa com repository
    //    ponto de injeção da repository
    final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public UserModel save(UserModel userModel) {
        return userRepository.save(userModel);
    }

    public List<UserModel> findAll() {
        return userRepository.findAll();
    }

    public Optional<UserModel> findById(UUID id) {
        return userRepository.findById(id);
    }

}