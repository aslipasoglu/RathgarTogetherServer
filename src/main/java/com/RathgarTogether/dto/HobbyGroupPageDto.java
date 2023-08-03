package com.RathgarTogether.dto;

import com.RathgarTogether.entities.Comment;
import com.RathgarTogether.entities.Event;
import com.RathgarTogether.entities.HobbyGroup;
import lombok.Data;

import java.util.List;

@Data
public class HobbyGroupPageDto {

    private HobbyGroup hobbyGroup;

    private List<Comment> commentList;
    private List<Event> eventList;
}
