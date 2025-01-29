package com.java24.plantswap.repositories;

import com.java24.plantswap.models.plant.Plant;
import com.java24.plantswap.models.plant.PlantStatus;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlantRepository extends MongoRepository <Plant, String> {

    List<Plant> findAllByPlantStatus(PlantStatus plantStatus);

}
