package com.java24.plantswap.models;

public class Plant {

    private String id;

    private String trivialName;

    private String scientificName;

    private int ageInMonths;

    private Size size;

    private Type type;

    private LightRequirment lightRequirement;

    private WaterRequirment waterRequirment;

    private CareDifficulty careDifficulty;

    private boolean isOnSale;

    private int priceInSEK;

    private boolean isOnExchange;

    private String exchangePreferences;

    private String imageURL;

    private Status status;











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
