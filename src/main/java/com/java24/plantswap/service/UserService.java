package com.java24.plantswap.service;

import com.java24.plantswap.models.User;
import com.java24.plantswap.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User registerNewUser (User user){
        return userRepository.save(user);
    }


}
