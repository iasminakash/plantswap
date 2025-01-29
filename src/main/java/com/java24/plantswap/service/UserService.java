package com.java24.plantswap.service;

import com.java24.plantswap.models.plant.Plant;
import com.java24.plantswap.models.user.User;
import com.java24.plantswap.repositories.PlantRepository;
import com.java24.plantswap.repositories.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;


    public UserService(UserRepository userRepository, PlantRepository plantRepository) {
        this.userRepository = userRepository;
    }

    public User registerNewUser(User user) {
        return userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(String id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));

    }

    public User updateUserById(String id, User userDetails) {
        User existingUser = userRepository.findById(id).
                orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

        //updatera attributer hos User
        existingUser.setFirstName(userDetails.getFirstName());
        existingUser.setLastName(userDetails.getLastName());
        existingUser.setEmail(userDetails.getEmail());
        existingUser.setPassword(userDetails.getPassword());
        existingUser.setPlants(userDetails.getPlants());
        existingUser.setTransactions(userDetails.getTransactions());

        //spara updaterade User
        return userRepository.save(existingUser);
    }

    public void deleteUserById(String id) {
        if (!userRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }
        userRepository.deleteById(id);
    }

    public List<Plant> getAllPlantsOfUser(String userId){
        return getUserById(userId).getPlants();
    }



}
