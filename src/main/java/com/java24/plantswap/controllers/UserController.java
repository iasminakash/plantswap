package com.java24.plantswap.controllers;

import com.java24.plantswap.models.User;
import com.java24.plantswap.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//talar om att det är kontroller
@RestController
//mappar alla requests
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;

    }

    @PostMapping
    public ResponseEntity<User> registerNewUser(@RequestBody User user) {
        User newUser = userService.registerNewUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
    }
}
