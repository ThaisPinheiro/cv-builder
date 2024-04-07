package com.cvbuilder.usersLogin.enums;

public enum UserRolesEnum {
    ADMIN("admin"),
    USER("user");

    private String role;

    UserRolesEnum(String role) {
        this.role = role;
    }

    public String getRole(){
        return role;
    }
}
