package com.java24.plantswap.controllers;

import com.java24.plantswap.service.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//talar om att det är kontroller
@RestController
//mappar alla requests
@RequestMapping("/api/plants")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
}
