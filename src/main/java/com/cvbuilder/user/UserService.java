package com.cvbuilder.user;

import com.cvbuilder.user.dtos.UserDto;
import jakarta.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {
  final UserRepository userRepository;

  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public List<UserModel> findAll() {
    return userRepository.findAll();
  }

  public UserDto findById(UUID id) {
    Optional<UserModel> optionalUserModel = userRepository.findById(id);
    if (!optionalUserModel.isPresent()) {
      return null;
    }
    UserModel userModel = optionalUserModel.get();
    UserDto userDataDto = new UserDto();
    BeanUtils.copyProperties(userModel, userDataDto);
    return userDataDto;
  }

  // @TODO: Pensar em um jeito de não precisar retornar a model - provavelmente vamos precisar de um convert
  public UserModel findByIdModel(UUID id) {
    Optional<UserModel> optionalUserModel = userRepository.findById(id);
    if (!optionalUserModel.isPresent()) {
      return null;
    }
    
    return optionalUserModel.get();
  }

  @Transactional
  public UserDto create(UserDto userDto) {
    UserModel userModel = new UserModel();
    BeanUtils.copyProperties(userDto, userModel);
    UserModel savedUserModel = userRepository.save(userModel);
    
    UserDto savedUserDto = new UserDto();
    BeanUtils.copyProperties(savedUserModel, savedUserDto);
    return savedUserDto;
  }

  @Transactional
  public UserDto update(UserDto userDto, UUID id) {
    Optional<UserModel> optionalUserModel = userRepository.findById(id);

    UserModel userModel = new UserModel();
    BeanUtils.copyProperties(userDto, userModel);
    userModel.setId(optionalUserModel.get().getId());
    UserModel savedUserModel = userRepository.save(userModel);
    
    UserDto savedUserDto = new UserDto();
    BeanUtils.copyProperties(savedUserModel, savedUserDto);
    return savedUserDto;
  }
  
  @Transactional
  public UserModel save(UserModel userModel) {
    return userRepository.save(userModel);
  }
}