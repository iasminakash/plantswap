package com.java24.plantswap.controllers;

import com.java24.plantswap.models.transaction.Transaction;
import com.java24.plantswap.service.TransactionService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


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

    @GetMapping
    public ResponseEntity<List<Transaction>> getAllTransactions(){
        List <Transaction> transactions = transactionService.getAllTransactions();
        return ResponseEntity.ok(transactions);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Transaction> getTransactionById (@Valid @PathVariable String id) {
        Transaction transaction = transactionService.getTransactionById(id);
        return ResponseEntity.ok(transaction);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Transaction> updateTransactionById (@Valid @PathVariable String id, @RequestBody Transaction newTransaction){
        Transaction transaction = transactionService.updateTransactionById(id, newTransaction);
        return ResponseEntity.ok(transaction);
    }

    @PutMapping("/swapapproval")
    public ResponseEntity<Transaction> approveTransaction (@Valid @RequestBody Transaction transaction){
        Transaction swapRequestApproval = transactionService.acceptSwapRequestApproval(transaction);
        return  ResponseEntity.ok(transaction);
    }
}
