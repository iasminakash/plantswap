package com.java24.plantswap.repositories;

import com.java24.plantswap.models.Plant;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlantRepository extends MongoRepository <Plant, String> {
}
