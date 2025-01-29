package com.java24.plantswap.controllers;

import com.java24.plantswap.models.plant.Plant;
import com.java24.plantswap.models.user.User;
import com.java24.plantswap.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers(){
        List <User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getByUserId(@PathVariable String id) {
        User user = userService.getUserById(id);
        return ResponseEntity.ok(user);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUserById(@PathVariable String id, @RequestBody User userDetails){
        User updatedUser = userService.updateUserById(id, userDetails);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<User> deleteUserById(@PathVariable String id){
        userService.deleteUserById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/plants")
    public ResponseEntity<List<Plant>> getAllPlantsOfUser(@PathVariable String id) {
        List<Plant> plants = userService.getAllPlantsOfUser(id);
        return ResponseEntity.ok(plants);
    }











}
