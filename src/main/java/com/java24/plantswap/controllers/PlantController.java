package com.java24.plantswap.controllers;

import com.java24.plantswap.models.plant.Plant;
import com.java24.plantswap.service.PlantService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//talar om att det är kontroller
@RestController
//mappar alla requests
@RequestMapping("/api/plants")
public class PlantController {

    private final PlantService plantService;

    public PlantController(PlantService plantService) {
        this.plantService = plantService;
    }


    @PostMapping
    public ResponseEntity<Plant> createPlant (@RequestBody Plant plant) {
        Plant savedPlant = plantService.createPlant(plant);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedPlant);
    }

    @GetMapping
    public ResponseEntity<List<Plant>> getAllPlants() {
        List<Plant>plants = plantService.getAllPlants();
        return ResponseEntity.ok(plants);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Plant> getPlantById(@PathVariable String id){
        Plant plant = plantService.getPlantById(id);
        return ResponseEntity.ok(plant);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Plant> updatePlant(@PathVariable String id, @RequestBody Plant plantDetails) {
        Plant updatedPlant = plantService.updatePlantById(id, plantDetails);
        return ResponseEntity.ok(updatedPlant);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Plant> deletePlant(@PathVariable String id) {
        plantService.deletePlantById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/available")
    public ResponseEntity<List<Plant>> getAvailablePlants(){
        List<Plant> availablePlants = plantService.getAvailablePlants();
        return ResponseEntity.ok(availablePlants);
    }



}
