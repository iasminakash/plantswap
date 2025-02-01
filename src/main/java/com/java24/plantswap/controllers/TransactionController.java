package com.java24.plantswap.controllers;

import com.java24.plantswap.models.transaction.Transaction;
import com.java24.plantswap.service.TransactionService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


//talar om att det är kontroller
@RestController
//mappar alla requests
@RequestMapping("/api/transactions")

public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping("/purchase")
    public ResponseEntity<Transaction> createPurchase(@Valid @RequestBody Transaction newTransaction){
        Transaction createdTransaction = transactionService.createPurchase(newTransaction);
        return ResponseEntity.ok(createdTransaction);
    }

    @PostMapping("/swaprequest")
    public ResponseEntity<Transaction> requestSwap(@Valid @RequestBody Transaction newTransaction){
        Transaction swapRequest = transactionService.requestSwap(newTransaction);
        return ResponseEntity.ok(swapRequest);
    }

}
