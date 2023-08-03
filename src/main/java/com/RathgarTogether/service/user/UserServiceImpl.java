package com.RathgarTogether.service.user;


import com.RathgarTogether.dto.UserDto;
import com.RathgarTogether.entities.User;
import com.RathgarTogether.enums.UserRole;
import com.RathgarTogether.repo.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;


    @Transactional
    public User createUser(UserDto userDto) {
            User user = new User();
            user.setName(userDto.getName());
            user.setSurname(userDto.getSurname());
            user.setPhone(userDto.getPhone());
            user.setEmail(userDto.getEmail());
            user.setRole(UserRole.USER);
            user.setPassword(new BCryptPasswordEncoder().encode(userDto.getPassword()));
            return userRepository.save(user);
    }

    public Boolean hasUserWithEmail(String email) {
        return userRepository.findFirstByEmail(email) != null;
    }

    public User login(User user) {
        Optional<User> dbUser = userRepository.findByEmail(user.getEmail());
        if(dbUser.isPresent() && new BCryptPasswordEncoder().matches(user.getPassword(),dbUser.get().getPassword())) {
            return dbUser.get();
        }
        return null;
    }


    public User getUser(Long userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        return optionalUser.orElse(null);
    }

    public User updateUser(Long userId, User user) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if(optionalUser.isPresent()){
            User savedUser = optionalUser.get();

            savedUser.setEmail(user.getEmail());
            return userRepository.save(savedUser);
        }
        return null;
    }

    public List<User> getAllUsers() {
        return userRepository.findAllByRole(UserRole.USER);
    }

    public void deleteUser(Long userId) {
        User user = userRepository.findById(userId).orElse(null);

        if (user != null) {
            userRepository.delete(user);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User with ID " + userId + " not found.");
        }
    }
}
