package com.java24.plantswap.service;

import com.java24.plantswap.models.plant.Plant;
import com.java24.plantswap.models.user.User;
import com.java24.plantswap.repositories.PlantRepository;
import com.java24.plantswap.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    //konstruktor injektion istället för autowired
    private final UserRepository userRepository;


    public UserService(UserRepository userRepository, PlantRepository plantRepository) {
        this.userRepository = userRepository;
    }
    //metod for att registreta ny user, tar user objektet som parameter
    public User registerNewUser(User user) {
        return userRepository.save(user);
    }

    //metod för att hämta alla users
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    //metod för att hämta user efter id, tar id som parameter och kastar fel, ifall det finns inget user med ett sånt id i repositoriet
    public User getUserById(String id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));

    }
    //metod för att uppdatera user, tar emot user id, user objekt
    public User updateUserById(String userId, User userDetails) {
        //hämtar user efter id med hjälp av metoden getUserById
        User existingUser = getUserById(userId);
        //updaterar attributer hos User
        existingUser.setFirstName(userDetails.getFirstName());
        existingUser.setLastName(userDetails.getLastName());
        existingUser.setEmail(userDetails.getEmail());
        existingUser.setPassword(userDetails.getPassword());
        //ser till att nya user array med plants och transactions är inte null, utan det skrivs de över och de gamla plants och transactions försinner
        if(userDetails.getPlants() != null){
            existingUser.setPlants(userDetails.getPlants());
        }
        if(userDetails.getTransactions() != null){
            existingUser.setTransactions(userDetails.getTransactions());
        }

        //spara updaterade User
        return userRepository.save(existingUser);
    }
    //metod för att få bort user efter id, tar emot userId som parameter, kastar fel ifall det inte finns user men samma user id
    public void deleteUserById(String userId) {
        //hämtar user efter id med hjälp av metoden getUserById
        User existingUser = getUserById(userId);
        userRepository.deleteById(userId);
    }
    //metod för att hämta alla plantor av usern, tar emot id
    public List<Plant> getAllPlantsOfUser(String userId){
        //hämtar user efter id med hjälp av metoden getUserById
        User existingUser = getUserById(userId);
        return getUserById(userId).getPlants();
    }

}
