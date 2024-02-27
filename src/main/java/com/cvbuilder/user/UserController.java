package com.cvbuilder.user;

import com.cvbuilder.user.dtos.UserDto;
import com.cvbuilder.user.models.UserModel;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@RestController
//@CrossOrigin() -> Verificar caso de uso da aplicação
@RequestMapping("/userResume")
public class UserController { // só conversa com service

    final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<?>createUser(@RequestBody @Valid UserDto userDto,
                                               BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage());
        }
        UserModel userModel = new UserModel();
        BeanUtils.copyProperties(userDto, userModel);
        UserModel savedUserModel = userService.save(userModel);
        
        UserDto savedUserDto = new UserDto();
        BeanUtils.copyProperties(savedUserModel, savedUserDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUserDto);
    }

    //Comentado por enquanto para verificar a necessidade da listagem retornando dto
    // @GetMapping
    // public ResponseEntity<List<PersonalDataDto>> getUser() {
    //    List<PersonalDataModel> personalDataModelList = personalDataService.findAll();

    //    List<PersonalDataDto> personalDataDtoList = new ArrayList<>();

    //    for (PersonalDataModel personalDataModel : personalDataModelList) {
    //     PersonalDataDto personalDataDto = new PersonalDataDto();
    //     BeanUtils.copyProperties(personalDataModel, personalDataDto);
    //     personalDataDtoList.add(personalDataDto);
    //    }
    //     return ResponseEntity.status(HttpStatus.OK).body(personalDataDtoList);
    // }

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
        if (bindingResult.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage());
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
