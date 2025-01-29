package com.java24.plantswap.models.transaction;

import com.java24.plantswap.models.plant.Plant;
import com.java24.plantswap.models.user.User;
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

    private boolean ownerAgreement;

    private boolean recipientAgreement;

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

    public Date getTransasctionDate() {
        return transasctionDate;
    }

    public void setTransasctionDate(Date transasctionDate) {
        this.transasctionDate = transasctionDate;
    }

    public boolean isOwnerAgreement() {
        return ownerAgreement;
    }

    public void setOwnerAgreement(boolean ownerAgreement) {
        this.ownerAgreement = ownerAgreement;
    }

    public boolean isRecipientAgreement() {
        return recipientAgreement;
    }

    public void setRecipientAgreement(boolean recipientAgreement) {
        this.recipientAgreement = recipientAgreement;
    }
}


