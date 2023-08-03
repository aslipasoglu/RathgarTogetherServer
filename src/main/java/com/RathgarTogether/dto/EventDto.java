package com.RathgarTogether.dto;

import lombok.Data;

import java.util.Date;

@Data
public class EventDto {

    private Long id;

    private String title;
    private String body;

    private Date date;

    private Long hobbyGroupId;
}
