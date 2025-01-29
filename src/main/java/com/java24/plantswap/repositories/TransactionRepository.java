package com.java24.plantswap.repositories;

import com.java24.plantswap.models.transaction.Transaction;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TransactionRepository extends MongoRepository<Transaction, String> {
}
