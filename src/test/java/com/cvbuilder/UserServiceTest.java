package com.cvbuilder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.BeanUtils;
import org.springframework.boot.test.context.SpringBootTest;

import com.cvbuilder.user.UserModel;
import com.cvbuilder.user.UserRepository;
import com.cvbuilder.user.UserService;
import com.cvbuilder.user.dtos.UserDto;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    private UserDto userDto;

    private UserModel userModel;

    @BeforeEach
    public void setUp() { 
        userDto = new UserDto();
        userDto.setName("teste");
        userDto.setEmail("teste@gmail.com");

        userModel = new UserModel();
        BeanUtils.copyProperties(userDto, userModel); 
    }

    @Test
    void createUser() {
        when(userRepository.save(any(UserModel.class))).thenReturn(userModel);

        UserDto savedUserDto = userService.create(userDto);

        assertEquals(userDto.getName(), savedUserDto.getName());
        assertEquals(userDto.getEmail(), savedUserDto.getEmail());
    }

}
