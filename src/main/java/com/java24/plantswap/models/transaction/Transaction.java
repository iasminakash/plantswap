package com.java24.plantswap.models.transaction;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "transactions")
public class Transaction {

    private String id;

    @NotNull(message = "PlantId can not be null")
    @NotEmpty(message = "PlantId can not be empty")
    private String ownerPlantId;

    private String recipientPlantId;

    @NotNull(message = "RecipientId can not be null")
    @NotEmpty(message = "RecipientId can not be empty")
    private String recipientId;

    @NotNull(message = "OwnerId can not be null")
    @NotEmpty(message = "OwnerId can not be empty")
    private String ownerId;

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

    public String getOwnerPlantId() {
        return ownerPlantId;
    }

    public void setOwnerPlantId(String ownerPlantId) {
        this.ownerPlantId = ownerPlantId;
    }

    public String getRecipientPlantId() {
        return recipientPlantId;
    }

    public void setRecipientPlantId(String recipientPlantId) {
        this.recipientPlantId = recipientPlantId;
    }

    public String getRecipientId() {
        return recipientId;
    }

    public void setRecipientId(String recipientId) {
        this.recipientId = recipientId;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }
}


