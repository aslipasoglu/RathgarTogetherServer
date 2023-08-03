package com.RathgarTogether.service.comment;

import com.RathgarTogether.dto.CommentDto;
import com.RathgarTogether.entities.Comment;
import com.RathgarTogether.entities.HobbyGroup;
import com.RathgarTogether.entities.User;
import com.RathgarTogether.repo.CommentRepository;
import com.RathgarTogether.repo.HobbyGroupRepository;
import com.RathgarTogether.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService{

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private HobbyGroupRepository hobbyGroupRepository;

    public Comment addComment(CommentDto commentDto) {
        User user = null;
        HobbyGroup hobbyGroup = null;
        Optional<User> userOptional = userRepository.findById(commentDto.getUserId());
        Optional<HobbyGroup> optionalHobbyGroup = hobbyGroupRepository.findById(commentDto.getHobbyGroupId());
        if(userOptional.isPresent() && optionalHobbyGroup.isPresent()){
            user = userOptional.get();
            hobbyGroup = optionalHobbyGroup.get();

            Comment comment = new Comment();
            comment.setBody(commentDto.getBody());
            comment.setCreatedDate(new Date());
            comment.setUser(user);
            comment.setHobbyGroup(hobbyGroup);

            return commentRepository.save(comment);
        }
        return null;
    }

}
