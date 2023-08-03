package com.RathgarTogether.service.user;

import com.RathgarTogether.dto.UserDto;
import com.RathgarTogether.entities.User;

import java.util.List;

public interface UserService {

    User createUser(UserDto userDto);

    Boolean hasUserWithEmail(String email);

    User login(User user);

    User getUser(Long userId);

    User updateUser(Long userId, User user);

    List<User> getAllUsers();

    void deleteUser(Long userId);
}
