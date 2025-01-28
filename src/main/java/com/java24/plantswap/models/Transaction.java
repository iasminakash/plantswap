package com.java24.plantswap.models;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "transactions")
public class Transaction {

    private String id;

    @DBRef
    private User owner;

    @DBRef
    private User recipient;

    @DBRef
    private Plant plant;

    private TransactionType transactionType;

    private Date transasctionDate;

    //tomm konstruktor
    public Transaction() {
    }

    //getters och setter
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public User getRecipient() {
        return recipient;
    }

    public void setRecipient(User recipient) {
        this.recipient = recipient;
    }


    public Plant getPlant() {
        return plant;
    }

    public void setPlant(Plant plant) {
        this.plant = plant;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public Date getTransasctionDate() {
        return transasctionDate;
    }

    public void setTransasctionDate(Date transasctionDate) {
        this.transasctionDate = transasctionDate;
    }

}
