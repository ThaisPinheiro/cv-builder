package com.cvbuilder.usersLogin.dtos;

import com.cvbuilder.usersLogin.enums.UserRolesEnum;

public record LoginRegisterDto(String login, String password, UserRolesEnum role) {
    
}
