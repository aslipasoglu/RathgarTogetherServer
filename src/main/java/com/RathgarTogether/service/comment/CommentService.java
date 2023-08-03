package com.RathgarTogether.service.comment;

import com.RathgarTogether.dto.CommentDto;
import com.RathgarTogether.entities.Comment;

public interface CommentService {


    Comment addComment(CommentDto commentDto);
}
