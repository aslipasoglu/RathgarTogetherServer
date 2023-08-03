package com.RathgarTogether.dto;

import com.RathgarTogether.enums.UserRole;
import lombok.Data;

@Data
public class UserDto {

    private Long id;

    private String email;

    private String password;

    private String name;
    private String surname;

    private String phone;

    private UserRole role;

}
