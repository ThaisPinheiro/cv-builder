package com.cvbuilder.user;

import com.cvbuilder.user.dtos.UserDto;
import com.cvbuilder.validators.ValidatorHandler;

import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
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

    @PostMapping
    public ResponseEntity<?>createUser(@RequestBody @Valid UserDto userDto,
                                               BindingResult bindingResult) {
        ResponseEntity<Object> errorResponse = validatorHandler.handleBindingResultErrors(bindingResult);
        if (errorResponse != null) {
            return errorResponse;
        }

        UserModel userModel = new UserModel();
        BeanUtils.copyProperties(userDto, userModel);
        UserModel savedUserModel = userService.save(userModel);
        
        UserDto savedUserDto = new UserDto();
        BeanUtils.copyProperties(savedUserModel, savedUserDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUserDto);
    }

    @GetMapping
    public ResponseEntity<List<UserModel>> getUser() {
        return ResponseEntity.status(HttpStatus.OK).body(userService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getUserId(@PathVariable(value = "id") UUID id) {
        Optional<UserModel> optionalUserModel = userService.findById(id);
        if (!optionalUserModel.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }

        UserModel userModel = optionalUserModel.get();
        UserDto userDataDto = new UserDto();
        BeanUtils.copyProperties(userModel, userDataDto);
        return ResponseEntity.status(HttpStatus.OK).body(userDataDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateUserId(@PathVariable(value = "id") UUID id, @RequestBody @Valid
    UserDto userDto, BindingResult bindingResult) {
        ResponseEntity<Object> errorResponse = validatorHandler.handleBindingResultErrors(bindingResult);
        if (errorResponse != null) {
            return errorResponse;
        }

        Optional<UserModel> optionalUserModel = userService.findById(id);
        if (!optionalUserModel.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }

        UserModel userModel = new UserModel();
        BeanUtils.copyProperties(userDto, userModel);
        userModel.setId(optionalUserModel.get().getId());
        UserModel savedUserModel = userService.save(userModel);
        
        UserDto savedUserDto = new UserDto();
        BeanUtils.copyProperties(savedUserModel, savedUserDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedUserDto);
    }
}
