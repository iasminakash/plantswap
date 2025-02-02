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
    public ResponseEntity<Transaction> getTransactionById ( @PathVariable String id) {
        Transaction transaction = transactionService.getTransactionById(id);
        return ResponseEntity.ok(transaction);
    }

    @GetMapping("getForOwner/{ownerId}")
    public ResponseEntity<List<Transaction>> getAllTransactionForUser (@PathVariable String ownerId){
        List <Transaction> userTransactions = transactionService.getAllTransactionForByOwnerId(ownerId);
        return ResponseEntity.ok(userTransactions);

    }

    @PutMapping("/{id}")
    public ResponseEntity<Transaction> updateTransactionById ( @PathVariable String id, @Valid @RequestBody Transaction newTransaction){
        Transaction transaction = transactionService.updateTransactionById(id, newTransaction);
        return ResponseEntity.ok(transaction);
    }

    @GetMapping("/swapapproval/{transactionId}")
    public ResponseEntity<Transaction> approveTransaction (@PathVariable String transactionId){
        Transaction swapRequestApproval = transactionService.acceptSwapRequestApproval(transactionId);
        return  ResponseEntity.accepted().build();
    }

    @GetMapping("/swapdecline/{transactionId}")
    public ResponseEntity<Transaction> declineTransaction (@PathVariable String transactionId){
        transactionService.declineSwapRequest(transactionId);
        return ResponseEntity.accepted().build();
    }


    @DeleteMapping("/{transactionId}/{ownerId}")
    public ResponseEntity<Transaction> deleteTransaction (@PathVariable String transactionId, @PathVariable String ownerId){
        transactionService.deleteTransaction(transactionId, ownerId);
        return ResponseEntity.noContent().build();
    }

}
