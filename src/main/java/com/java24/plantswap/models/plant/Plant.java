package com.java24.plantswap.models.plant;


import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "plants")
public class Plant {

    @Id
    private String id;

    @NotNull(message = "Plant name can not be null")
    @NotEmpty(message = "Plant name can not be empty")
    private String trivialName;

    private String scientificName;

    private DesiredTransactionType desiredTransactionType;

    private int ageInMonths;

    private PlantSize plantSize;

    @NotNull(message = "Plant type can not be null")
    @NotEmpty(message = "Plant type can not be null")
    private PlantType plantType;

    private PlantLightRequirment plantLightRequirment;

    private PlantWaterRequirment plantWaterRequirment;

    private PlantCareDifficulty plantCareDifficulty;

    @Min(value = 50, message = "Plant price should not be less then 50 SEK")
    @Max(value = 1000, message = "Plant price should not exceed 1000 SEK")
    private int priceInSEK;

    private String exchangePreferences;

    @NotNull(message = "Plant image can not be null")
    @NotEmpty(message = "Plant image can not be empty")
    private String imageURL;

    @NotNull(message = "Plant status can not be null")
    @NotEmpty(message = "Plant status can not be empty")
    private PlantStatus plantStatus;



    //tom konstruktor
    public Plant() {
    }

    //getters och setter

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTrivialName() {
        return trivialName;
    }

    public void setTrivialName(String trivialName) {
        this.trivialName = trivialName;
    }

    public String getScientificName() {
        return scientificName;
    }

    public void setScientificName(String scientificName) {
        this.scientificName = scientificName;
    }

    public int getAgeInMonths() {
        return ageInMonths;
    }

    public void setAgeInMonths(int ageInMonths) {
        this.ageInMonths = ageInMonths;
    }

    public PlantSize getSize() {
        return plantSize;
    }

    public void setSize(PlantSize plantSize) {
        this.plantSize = plantSize;
    }

    public PlantType getPlantType() {
        return plantType;
    }

    public void setPlantType(PlantType plantType) {
        this.plantType = plantType;
    }

    public PlantLightRequirment getLightRequirement() {
        return plantLightRequirment;
    }

    public void setLightRequirement(PlantLightRequirment lightRequirement) {
        this.plantLightRequirment = lightRequirement;
    }

    public PlantWaterRequirment getWaterRequirment() {
        return plantWaterRequirment;
    }

    public void setWaterRequirment(PlantWaterRequirment plantWaterRequirment) {
        this.plantWaterRequirment = plantWaterRequirment;
    }

    public PlantCareDifficulty getCareDifficulty() {
        return plantCareDifficulty;
    }

    public void setCareDifficulty(PlantCareDifficulty plantCareDifficulty) {
        this.plantCareDifficulty = plantCareDifficulty;
    }

    public int getPriceInSEK() {
        return priceInSEK;
    }

    public void setPriceInSEK(int priceInSEK) {
        this.priceInSEK = priceInSEK;
    }


    public String getExchangePreferences() {
        return exchangePreferences;
    }

    public void setExchangePreferences(String exchangePreferences) {
        this.exchangePreferences = exchangePreferences;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public PlantStatus getPlantStatus() {
        return plantStatus;
    }

    public void setPlantStatus(PlantStatus plantStatus) {
        this.plantStatus = plantStatus;
    }

    public DesiredTransactionType getDesiredTransactionType() {
        return desiredTransactionType;
    }

    public void setDesiredTransactionType(DesiredTransactionType desiredTransactionType) {
        this.desiredTransactionType = desiredTransactionType;
    }

    public PlantSize getPlantSize() {
        return plantSize;
    }

    public void setPlantSize(PlantSize plantSize) {
        this.plantSize = plantSize;
    }

    public PlantWaterRequirment getPlantWaterRequirment() {
        return plantWaterRequirment;
    }

    public void setPlantWaterRequirment(PlantWaterRequirment plantWaterRequirment) {
        this.plantWaterRequirment = plantWaterRequirment;
    }

    public PlantCareDifficulty getPlantCareDifficulty() {
        return plantCareDifficulty;
    }

    public void setPlantCareDifficulty(PlantCareDifficulty plantCareDifficulty) {
        this.plantCareDifficulty = plantCareDifficulty;
    }
}
