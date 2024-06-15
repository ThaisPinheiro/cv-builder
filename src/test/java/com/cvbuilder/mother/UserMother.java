package com.cvbuilder.mother;

import org.springframework.beans.BeanUtils;

import com.cvbuilder.user.UserModel;
import com.cvbuilder.user.dtos.UserDto;

public class UserMother {

    public static UserDto userDtoMother() {

        UserDto userDto = new UserDto();
        userDto.setName("Luana");
        userDto.setSurname("Machado");
        userDto.setTelephoneNumber("1799999999");
        userDto.setEmail("luana@gmail.com");
        userDto.setSocialAccount("https://linkedin.com/in/luana");
        userDto.setPortfolio("https://github.com/luanavma");
        userDto.setAboutMe("This is not a real description.");

        return userDto;
    }

    public static UserModel userModelMother() {
        UserDto userDto = userDtoMother();
        UserModel userModel = new UserModel();
        BeanUtils.copyProperties(userDto, userModel);
        return userModel;
    } 
    
}
