package com.java24.plantswap.service;

import com.java24.plantswap.models.plant.Plant;
import com.java24.plantswap.models.plant.PlantStatus;
import com.java24.plantswap.models.user.User;
import com.java24.plantswap.repositories.PlantRepository;
import com.java24.plantswap.repositories.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.ArrayList;
import java.util.List;

@Service
public class PlantService {

    //konstruktor injektion istället för autowired
    private final PlantRepository plantRepository;
    private final UserService userService;
    private final UserRepository userRepository;

    public PlantService(PlantRepository plantRepository, UserService userService, UserRepository userRepository) {
        this.plantRepository = plantRepository;
        this.userService = userService;
        this.userRepository = userRepository;
    }
    //metod för att skapa ny planta och binda den till user
    public Plant createPlant(Plant plant, String userId) {
        //Hittar usern på user id genom att kalla metoden getUserById från userService
        User user = userService.getUserById(userId);
        //Om usern har fler än 10 plantor ska inte tillåta skapa ny listing med planta och ska sckika en felmedelande
        if(user.getPlants() != null && user.getPlants().size() >= 10){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "You exceeded maximum number of posted plants.");
        }
        //tvingar alla plantor som skapas ha status AVAILABLE
        plant.setPlantStatus(PlantStatus.AVAILABLE);
        //sparar planta till plantRepository
        Plant createdPlant = plantRepository.save(plant);
        //om user har inga plantor, dvs finns ingen lista, så ska han få en
        if(user.getPlants() == null){
            user.setPlants(new ArrayList<>());
        }
        //adderar planta till users nya tömma listan
        user.getPlants().add(createdPlant);
        //uppdaterar user
        userService.updateUserById(userId, user);
        //returnerar skapade planta
        return createdPlant;
    }

    //metod att få alla plantor
    public List<Plant> getAllPlants() {
        return plantRepository.findAll();
    }

    //metod att få planta efter id, kastar fel ifall det inte finns en planta med det id
    public Plant getPlantById(String id) {
        return plantRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Plant not found with id: " + id));
    }

    //metod att uppdatera en planta efter id, kastar fel ifall det inte finns en planta med ett sånt id
    public Plant updatePlantById(String plantId, Plant plantDetails) {
        //hittar planta med metoden getPlantById
        Plant existingPlant = getPlantById(plantId);

        // Updatera atributer hos Plant
        if(plantDetails.getTrivialName() != null){
            existingPlant.setTrivialName(plantDetails.getTrivialName());
        }
        if(plantDetails.getScientificName() != null){
            existingPlant.setScientificName(plantDetails.getScientificName());
        }
        if(plantDetails.getAgeInMonths() != null){
            existingPlant.setAgeInMonths(plantDetails.getAgeInMonths());
        }
        if(plantDetails.getPlantType() != null){
            existingPlant.setPlantType(plantDetails.getPlantType());
        }
        if(plantDetails.getLightRequirement() != null){
            existingPlant.setLightRequirement(plantDetails.getLightRequirement());
        }
        if(plantDetails.getWaterRequirment() != null){
            existingPlant.setWaterRequirment(plantDetails.getWaterRequirment());
        }
        if(plantDetails.getCareDifficulty() != null){
            existingPlant.setCareDifficulty(plantDetails.getCareDifficulty());
        }
        if(plantDetails.getPriceInSEK() != null){
            existingPlant.setPriceInSEK(plantDetails.getPriceInSEK());
        }
        if(plantDetails.getExchangePreferences() != null){
            existingPlant.setExchangePreferences(plantDetails.getExchangePreferences());
        }
        if(plantDetails.getImageURL() != null){
            existingPlant.setImageURL(plantDetails.getImageURL());
        }
        if(plantDetails.getPlantStatus() != null) {
            existingPlant.setPlantStatus(plantDetails.getPlantStatus());
        }
        if(plantDetails.getDesiredTransactionType() != null) {
            existingPlant.setDesiredTransactionType(plantDetails.getDesiredTransactionType());
        }
        if(plantDetails.getSize() != null){
            existingPlant.setSize(plantDetails.getSize());
        }
        // Spara updaterade plantan
        return plantRepository.save(existingPlant);
    }

    //metod för att få bort en planta efter id, kastar fel ifall det inte finns en planta med det id
    public void deletePlantById(String plantId, String userId) {
        Plant plant = getPlantById(plantId);
        plantRepository.deleteById(plantId);
        User user = userService.getUserById(userId);
        user.getPlants().remove(plant);
        userRepository.save(user);
    }

    //metod som hämtar alla tillgälgiga plantor
    public List<Plant> getAvailablePlants() {
        //deklarerar en tömm lista som heter available plants
        List<Plant> availablePlants = new ArrayList<>();
        //deklarerar en lista som heter alla plantor och ger det värde av alla plantor, genom att kalla metoden getAllPlants
        List<Plant> allPlants = getAllPlants();
        //loopar genom alla plantor för att sortera bort alla som har status AVAILABLE och adderar de till listan availablePlant, returnerar den listan
        for(int i = 0; i < allPlants.size(); i++) {
            if(allPlants.get(i).getPlantStatus().equals(PlantStatus.AVAILABLE)) {
                availablePlants.add(allPlants.get(i));
            }
        }
        return availablePlants;
    }


}

