package com.RathgarTogether.controller;

import com.RathgarTogether.dto.UserDto;
import com.RathgarTogether.entities.User;
import com.RathgarTogether.service.user.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;


@RestController
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping({"/api/user"})
    public ResponseEntity<?> signupUser(@RequestBody(required = true) @Valid UserDto user) {

        if (userService.hasUserWithEmail(user.getEmail()))
            return new ResponseEntity<>("User already exists", HttpStatus.NOT_ACCEPTABLE);

        User createdUser = userService.createUser(user);
        if (createdUser == null)
            return new ResponseEntity<>("User not created, come again later", HttpStatus.NOT_ACCEPTABLE);

        return new ResponseEntity<>(createdUser, HttpStatus.OK);
    }

    @PostMapping({"/login"})
    public ResponseEntity<?> login(@RequestBody User user) {
        User dbUser = userService.login(user);
        if (dbUser == null)
            return new ResponseEntity<>("Wrong Conditionals", HttpStatus.NOT_ACCEPTABLE);

        return new ResponseEntity<>(dbUser, HttpStatus.OK);
    }

    @GetMapping("/api/user/{id}")
    public ResponseEntity<?> getUser(@PathVariable Long id){
        return new ResponseEntity<>(userService.getUser(id), HttpStatus.OK);
    }

    @PutMapping("/api/user/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody User user){
        return new ResponseEntity<>(userService.updateUser(id,user), HttpStatus.OK);
    }

    @GetMapping("/api/user/all")
    public ResponseEntity<?> getAllUsers(){
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    @DeleteMapping("/api/user/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
        return ResponseEntity.ok("User with ID " + userId + " has been deleted successfully.");
    }
}
