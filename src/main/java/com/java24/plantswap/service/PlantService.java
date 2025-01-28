package com.java24.plantswap.service;

import com.java24.plantswap.models.Plant;
import com.java24.plantswap.models.PlantStatus;
import com.java24.plantswap.repositories.PlantRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class PlantService {

    private final PlantRepository plantRepository;

    public PlantService(PlantRepository plantRepository) {
        this.plantRepository = plantRepository;
    }

    public Plant createPlant(Plant plant) {
        return plantRepository.save(plant);
    }

    public List<Plant> getAllPlants() {
        return plantRepository.findAll();
    }

    public Plant getPlantById(String id) {
        return plantRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Plant not found with id: " + id));
    }

    public Plant updatePlantById(String id, Plant plantDetails) {
        Plant existingPlant = plantRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Plant not found"));

        // Update the plant's attributes
        existingPlant.setTrivialName(plantDetails.getTrivialName());
        existingPlant.setScientificName(plantDetails.getScientificName());
        existingPlant.setAgeInMonths(plantDetails.getAgeInMonths());
        existingPlant.setPlantType(plantDetails.getPlantType());
        existingPlant.setLightRequirement(plantDetails.getLightRequirement());
        existingPlant.setWaterRequirment(plantDetails.getWaterRequirment());
        existingPlant.setCareDifficulty(plantDetails.getCareDifficulty());
        existingPlant.setPriceInSEK(plantDetails.getPriceInSEK());
        existingPlant.setExchangePreferences(plantDetails.getExchangePreferences());
        existingPlant.setImageURL(plantDetails.getImageURL());
        existingPlant.setPlantStatus(plantDetails.getPlantStatus());

        // Save the updated plant
        return plantRepository.save(existingPlant);
    }

    public void deletePlant(String id) {
        if (!plantRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Plant not found");
        }
        plantRepository.deleteById(id);
    }

    public List<Plant> getAvailablePlants() {
        return plantRepository.findAllByPlantStatus(PlantStatus.AVAILABLE);
    }
}

