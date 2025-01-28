package com.java24.plantswap.models;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "plants")
public class Plant {

    @Id
    private String id;

    private String trivialName;

    private String scientificName;

    private int ageInMonths;

    private PlantSize plantSize;

    private PlantType plantType;

    private PlantLightRequirment lightRequirement;

    private PlantWaterRequirment plantWaterRequirment;

    private PlantCareDifficulty plantCareDifficulty;


    private int priceInSEK;

    private String exchangePreferences;

    private String imageURL;

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
        return lightRequirement;
    }

    public void setLightRequirement(PlantLightRequirment lightRequirement) {
        this.lightRequirement = lightRequirement;
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





    /*Name (both scientific name and common/trivial name)
Age/Size
Plant Type (e.g., cutting, mature plant, seed, etc.)
Light Requirements
Water Requirements
Difficulty Level of Care (1–5)
Exchange Preferences or Fixed Price
Images (URLs to pictures)
Status (available, reserved, exchanged/sold)

    Varje växt måste innehålla följande information:
    Namn (både vetenskapligt namn och trivialnamn)
    Ålder/storlek
    Växttyp (stickling, fullvuxen planta, frö etc.)
    Ljusbehov
            Vattenbehov
    Svårighetsgrad för skötsel (1-5)
    Bytesönskemål eller fast pris
    Bilder (URL:er till bilder)
    Status (tillgänglig, reserverad, bytt/såld)
    */

}
