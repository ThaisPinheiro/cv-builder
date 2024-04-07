package com.cvbuilder.user;

import com.cvbuilder.user.dtos.UserDto;

import jakarta.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/userResume")
public class UserController {

    final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<?>createUser(@RequestBody @Valid UserDto userDto,
                                            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return handleBindingResultErrors(bindingResult);
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
    public ResponseEntity<Object> updateUserId(@PathVariable(value = "id") UUID id, 
    @RequestBody @Valid UserDto userDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return handleBindingResultErrors(bindingResult);
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

    private ResponseEntity<Object> handleBindingResultErrors(BindingResult bindingResult) {
        List<String> errors = bindingResult.getAllErrors().stream()
        .map(DefaultMessageSourceResolvable::getDefaultMessage)
        .collect(Collectors.toList());
        return ResponseEntity.badRequest().body(errors);
    }
}
