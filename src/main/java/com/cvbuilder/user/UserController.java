package com.cvbuilder.user;

import com.cvbuilder.user.dtos.UserDto;
import com.cvbuilder.validators.ValidatorHandler;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {
    
  final UserService userService;

  final ValidatorHandler validatorHandler;

  public UserController(UserService userService, ValidatorHandler validatorHandler) {
    this.userService = userService;
    this.validatorHandler = validatorHandler;
  }

  @GetMapping
  public ResponseEntity<List<UserModel>> getUser() {
    return ResponseEntity.status(HttpStatus.OK).body(userService.findAll());
  }

  @GetMapping("/{id}")
  public ResponseEntity<Object> getUserId(@PathVariable(value = "id") UUID id) {
    UserDto userDataDto = userService.findById(id);
    if(userDataDto == null) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(userDataDto);
    }
    return ResponseEntity.status(HttpStatus.OK).body(userDataDto);
  }

  @PostMapping
  public ResponseEntity<?>createUser(@RequestBody @Valid UserDto userDto,BindingResult bindingResult) {
    ResponseEntity<Object> errorResponse = validatorHandler.handleBindingResultErrors(bindingResult);
    if (errorResponse != null) {
      return errorResponse;
    }
    
    UserDto savedUserDto = userService.create(userDto);
    return ResponseEntity.status(HttpStatus.CREATED).body(savedUserDto);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Object> updateUserId(@PathVariable(value = "id") UUID id, @RequestBody @Valid
                                                            UserDto userDto, BindingResult bindingResult) {
    ResponseEntity<Object> errorResponse = validatorHandler.handleBindingResultErrors(bindingResult);
    if (errorResponse != null) {
      return errorResponse;
    }

    UserDto savedUserDto = userService.update(userDto, id);
    return ResponseEntity.status(HttpStatus.CREATED).body(savedUserDto);
  }
}
