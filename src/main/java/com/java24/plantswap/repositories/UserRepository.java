package com.java24.plantswap.repositories;

import com.java24.plantswap.models.user.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {


}
