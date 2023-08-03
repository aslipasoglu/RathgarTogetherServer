package com.RathgarTogether.dto;

import com.RathgarTogether.entities.User;
import lombok.Data;

@Data
public class HobbyGroupDto {

    private Long id;

    private String name;
    private String speciality;

    private Long owner;
}
