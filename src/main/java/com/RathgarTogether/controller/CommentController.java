package com.RathgarTogether.controller;

import com.RathgarTogether.dto.CommentDto;
import com.RathgarTogether.dto.HobbyGroupDto;
import com.RathgarTogether.entities.Comment;
import com.RathgarTogether.entities.HobbyGroup;
import com.RathgarTogether.service.comment.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/comment")
public class CommentController {


    @Autowired
    private CommentService commentService;

    @PostMapping
    public ResponseEntity<?> createComment(@RequestBody CommentDto commentDto) {

        Comment comment = commentService.addComment(commentDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(comment);
    }
}
