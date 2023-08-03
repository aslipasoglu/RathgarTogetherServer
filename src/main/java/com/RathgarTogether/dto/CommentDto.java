package com.RathgarTogether.dto;

import lombok.Data;

import java.util.Date;

@Data
public class CommentDto {


    private  Long id;

    private String body;

    private Date createdDate;

    private Long hobbyGroupId;

    private Long userId;
}
