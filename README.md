# plantswap
INSTALLATIONSINSTRUKTIONER

För att installera denna REST API behövs följande:

-JDK 
-IntelliJ IDEA (CE eller UE)
-MongoDB Compass
-Maven



# FUNKTIONALITET
Detta är ett enkelt REST API för en växtutbytesapplikation där användare kan köpa eller byta ut växter.
-user management
-listing management
-transaction management


 ## Steg 1
Klona repositoriet med följande kommando i terminalen:
git clone https://github.com/iasminakash/plantswap.git
Öppna projektet i IntelliJ

 ## Steg 2
Skaffa ny anslutning i MongoDB Compass
Använd följande anslutningsport i MongoDB Compass
mongodb://localhost:27017

 ## Steg 3
I filen src/main/resources/application.properties, sätt din databasanslutning så här:
spring.data.mongodb.uri=mongodb://localhost:27017/<plantswap>

 ## Steg 4
Öppna terminalen och skriv in följande kommandon
mvn clean install
mvn spring-boot:run



#BESKRIVNING AV API METODER
Nedan följer en sammanfattning av de olika API-metoderna och deras funktioner. För exempel på responses finns följande publicering i Postman. Besök det genom att följa länken nedan.
https://documenter.getpostman.com/view/41102705/2sAYX6ng2e

# PLANT MANAGEMENT:
POST create plant: 
Skapar en ny växt med information om namn, typ, storlek, ljuskrav, och mycket mer.

GET get all plants: 
Hämtar alla växter från systemet.

GET get single plant by id: 
Hämtar information om en specifik växt med ett unikt ID.

GET get all available plants: 
Hämtar alla växter som är tillgängliga för utbyte eller köp.

PUT update plant: 
Uppdaterar information om en växt (som pris eller status) med ett specifikt ID.

DELETE delete plant by id: 
Tar bort en växt med ett specifikt ID.

# USER MANAGEMENT
POST create user: 
Skapar en ny användare med namn, email, lösenord och tom lista på växter och transaktioner.

GET get all users: 
Hämtar alla användare i systemet.

GET get single user by id: 
Hämtar information om en specifik användare med deras ID.

PUT update user: 
Uppdaterar användarens information som namn eller email.

DELETE delete user by id: 
Tar bort en användare från systemet med deras ID.

GET get all plants of a user: 
Hämtar alla växter som tillhör en specifik användare.

# TRANSACTION MANAGEMENT
POST create purchase: 
Skapar en köptransaktion där en växt säljs eller byts mellan användare.

POST create purchase: 
Skapar en växtbyte-förfrågan där användare kan be om att köpa en växt.

POST create swaprequest: 
Skapar en växtbyte-förfrågan där användare kan be om att byta växter med varandra.

PUT accept swaprequest: 
Accepterar förfrågan om utbyte om en växt.

PUT decline swaprequest: 
Neka förfrågan om utbyte av en växt.

GET get all transactions: 
Hämtar alla transaktioner (köp eller byten) mellan användare.

GET get single transaction by id: 
Hämtar detaljer om en specifik transaktion.

PUT update transaction by id: 
Uppdaterar en transaktion (t.ex. godkänna eller neka ett byte) baserat på ID.

DELETE delete transaction by id: 
Tar bort en transaction från systemet. 

# Affärsregler
En användare kan inte ha mer än 10 aktiva annonser samtidigt.
Växter markerade för byte kan endast bytas mot andra växter, inte säljas.
Vid byte måste båda parter godkänna bytet innan det genomförs.
Prissatta växter måste ha ett fast pris mellan 50 och 1000 kr.

# Lista över kända begränsningar
Problem: Systemet tillåter att en användare byter planta med sig själv eller köper en planta från sig själv, vilket inte är logiskt.
Förslag på förbättring: Ha bättre kontroll och med hjälp av förändring i bussines logiken se till att ownerId och recipientId i kollektionen "transactions" inte kan vara samma värde.

Problem: Systemet tillåter att en användare lägga pris även om plantan ska bytas ut. Dessutom kan en användare lägga "exchange preferences", info om preferenser för byte ifall plantan ska vara till salu.
Förslag på förbättring: Ha bättre kontroll och med hjälp av förändring i bussines logiken se till att visa fälten blockedas från ifyllning i momenten när användaren bestämmer om plantan är för utbyte eller till salu.
