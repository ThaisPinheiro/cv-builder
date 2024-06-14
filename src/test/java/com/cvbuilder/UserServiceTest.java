package com.cvbuilder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.BeanUtils;

import com.cvbuilder.user.UserModel;
import com.cvbuilder.user.UserRepository;
import com.cvbuilder.user.UserService;
import com.cvbuilder.user.dtos.UserDto;

@ExtendWith(MockitoExtension.class)
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
        userDto.setName("Luana");
        userDto.setSurname("Machado");
        userDto.setTelephoneNumber("1799999999");
        userDto.setEmail("luana@gmail.com");
        userDto.setSocialAccount("https://linkedin.com/in/luana");
        userDto.setPortfolio("https://github.com/luanavma");
        userDto.setAboutMe("This is not a real description.");

        userModel = new UserModel();
        BeanUtils.copyProperties(userDto, userModel);
    }

    @Test
    void createUser() {
        when(userRepository.save(any(UserModel.class))).thenReturn(userModel);

        UserDto savedUserDto = userService.create(userDto);

        assertEquals(userDto.getName(), savedUserDto.getName());
        assertEquals(userDto.getSurname(), savedUserDto.getSurname());
        assertEquals(userDto.getTelephoneNumber(), savedUserDto.getTelephoneNumber());
        assertEquals(userDto.getEmail(), savedUserDto.getEmail());
        assertEquals(userDto.getSocialAccount(), savedUserDto.getSocialAccount());
        assertEquals(userDto.getPortfolio(), savedUserDto.getPortfolio());
        assertEquals(userDto.getAboutMe(), savedUserDto.getAboutMe());

        verify(userRepository, times(1)).save(any(UserModel.class));
     }

    }