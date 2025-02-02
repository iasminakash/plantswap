package com.java24.plantswap.repositories;

import com.java24.plantswap.models.transaction.Transaction;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface TransactionRepository extends MongoRepository<Transaction, String> {

    List<Transaction> findAllByOwnerId(String userId);
}
