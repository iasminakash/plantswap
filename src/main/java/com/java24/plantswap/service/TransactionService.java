package com.java24.plantswap.service;

import com.java24.plantswap.models.plant.DesiredTransactionType;
import com.java24.plantswap.models.plant.Plant;
import com.java24.plantswap.models.plant.PlantStatus;
import com.java24.plantswap.models.transaction.Transaction;
import com.java24.plantswap.models.user.User;
import com.java24.plantswap.repositories.PlantRepository;
import com.java24.plantswap.repositories.TransactionRepository;
import com.java24.plantswap.repositories.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class TransactionService {

    //konstruktor injektion istället för autowired
    private final TransactionRepository transactionRepository;
    private final PlantRepository plantRepository;
    private final UserRepository userRepository;
    private final PlantService plantService;
    private final UserService userService;


    public TransactionService(TransactionRepository transactionRepository, PlantRepository plantRepository, UserRepository userRepository, PlantService plantService, UserService userService) {
        this.transactionRepository = transactionRepository;
        this.plantRepository = plantRepository;
        this.userRepository = userRepository;
        this.plantService = plantService;
        this.userService = userService;
    }

    //metod for att skaffa ett köp
    public Transaction createPurchase(Transaction newTransaction) {
        //hittar plantan, med hjälp av id, använder metoden getPlantById från plantService samt getter
        Plant ownerPlant = plantService.getPlantById(newTransaction.getOwnerPlantId());
        //hittar owner och recipient med hjälp av id, använder metoden getUserById från userService
        User owner = userService.getUserById(newTransaction.getOwnerId());
        User recipient = userService.getUserById(newTransaction.getRecipientId());
        //Nu när jag hittat allt, "tittar in i plantan" och tar alla scenario beroende på DesiredTransaction type och PlantStatus,
        //Dvs kollar om plantan är till salu och inte sold eller redan såld/byttat
        //Om det är till swap och inte till salu, kastar felmedelande
        if (ownerPlant.getDesiredTransactionType().equals(DesiredTransactionType.SWAP)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "These plant is only available for swap.");
        }
        //Om det är såld eller utbytt kastar felmedelande
        if (ownerPlant.getPlantStatus().equals(PlantStatus.SOLD_OR_SWAPPED)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The flower is already sold or swapped.");
        }
        //om de två "if" skippades och så byter jag status på plantan och gör det från "AVAILABLE" till SOLD_OR_SWAPPED
        ownerPlant.setPlantStatus(PlantStatus.SOLD_OR_SWAPPED);
        //sparar planta
        plantRepository.save(ownerPlant);
        //sätter datum för transactionen
        newTransaction.setTransasctionDate(new Date());
        //både owner och recipient BOOLEAN om agreement sätts till TRUE
        newTransaction.setOwnerAgreement(true);
        newTransaction.setRecipientAgreement(true);
        //sparar transactionen till repositoriet
        transactionRepository.save(newTransaction);
        //om owner inte har någon array med transactioner ger den en ny lista
        if (owner.getTransactions() == null) {
            owner.setTransactions(new ArrayList<>());
        }
        //adderar listan till arrayen
        owner.getTransactions().add(newTransaction);
        //sparar det till userRepository
        userRepository.save(owner);
        //om recipient inte har någon array med transactioner ger den en ny lista
        if (recipient.getTransactions() == null) {
            recipient.setTransactions(new ArrayList<>());
        }
        //adderar listan till arrayen
        recipient.getTransactions().add(newTransaction);
        //sparar det till userRepository
        userRepository.save(recipient);
        //returnerar transactionen
        return newTransaction;
    }

    //metod för att initiera ett swap (resulterar inte i en swap, utan det är som en requent från recipient till owner,
    //ändrar status av planten till RESERVED, swap kommer inte hända fram tills owner accepterade med
    public Transaction requestSwap(Transaction newTransaction) {
        //hittar plantan till recipient och plantan till owner, med hjälp av id, använder metoden getPlantById från plantService samt getter
        Plant ownerPlant = plantService.getPlantById(newTransaction.getOwnerPlantId());
        Plant recipientPlant = plantService.getPlantById(newTransaction.getRecipientPlantId());
        //hittar owner och recipient med hjälp av id, använder metoden getUserById från userService
        User owner = userService.getUserById(newTransaction.getOwnerId());
        User recipient = userService.getUserById(newTransaction.getRecipientId());
        //Nu när jag hittat allt, "tittar in i p=ownerPlantan" och tar alla scenario beroende på DesiredTransaction type och PlantStatus
        //Dvs kollar om plantan är till salu och inte sold eller redan såld/byttat
        //Om det är till swap och inte till salu, kastar felmedelande
        if (ownerPlant.getDesiredTransactionType().equals(DesiredTransactionType.SALE)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "These plant is only available for sale.");
        }
        //Om det är såld eller utbytt kastar felmedelande
        if (ownerPlant.getPlantStatus().equals(PlantStatus.SOLD_OR_SWAPPED)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The flower is already sold or swapped.");
        }
        //om de två "if" skippades och så byter jag status på owner plantan och recipient plantan och gör det från "AVAILABLE" till "RESERVED"
        ownerPlant.setPlantStatus(PlantStatus.RESERVED);
        recipientPlant.setPlantStatus(PlantStatus.RESERVED);
        //sparar plantorna
        plantRepository.save(ownerPlant);
        plantRepository.save(recipientPlant);
        //ställer om datum
        newTransaction.setTransasctionDate(new Date());
        //recipient BOOLEAN om agreement sätts till TRUE, ownerAgreement är forfarande FALSE
        newTransaction.setOwnerAgreement(false);
        newTransaction.setRecipientAgreement(true);
        //sparar transactionen till repositoriet
        transactionRepository.save(newTransaction);
        //om owner inte har någon array med transactioner ger den en ny lista
        if (owner.getTransactions() == null) {
            owner.setTransactions(new ArrayList<>());
        }
        //adderar listan till arrayen
        owner.getTransactions().add(newTransaction);
        //sparar det till userRepository
        userRepository.save(owner);
        //om recipient inte har någon array med transactioner ger den en ny lista
        if (recipient.getTransactions() == null) {
            recipient.setTransactions(new ArrayList<>());
        }
        //adderar listan till arrayen
        recipient.getTransactions().add(newTransaction);
        //sparar det till userRepository
        userRepository.save(recipient);
        //returnerar transactionen
        return newTransaction;
    }

    //Metod för att accepterade en request
    public Transaction acceptSwapRequestApproval(String transactionId) {
        Transaction transaction = getTransactionById(transactionId);
        if (transaction.isOwnerAgreement()) {
            throw new RuntimeException("The transaction is already approved by owner.");
        }
        //hittar plantan till recipient och plantan till owner, med hjälp av id, använder metoden getPlantById från plantService samt getter
        Plant ownerPlant = plantService.getPlantById(transaction.getOwnerPlantId());
        Plant recipientPlant = plantService.getPlantById(transaction.getRecipientPlantId());
        //hittar owner och recipient med hjälp av id, använder metoden getUserById från userService
        User owner = userService.getUserById(transaction.getOwnerId());
        User recipient = userService.getUserById(transaction.getRecipientId());
        //Status ska ändras från "RESERVED" till "SOLD_OR_SWAPPED"
        ownerPlant.setPlantStatus(PlantStatus.SOLD_OR_SWAPPED);
        recipientPlant.setPlantStatus(PlantStatus.SOLD_OR_SWAPPED);
        //Updaterar plantorna
        plantService.updatePlantById(ownerPlant.getId(), ownerPlant);
        plantService.updatePlantById(recipientPlant.getId(), recipientPlant);
        //Nu när jag hittat allt, ska ownerAgreement approval sättas till TRUE
        transaction.setOwnerAgreement(true);
        //Updaterar transaction
        return updateTransactionById(transactionId, transaction);
    }

    public void declineSwapRequest(String transactionId) {
        Transaction transaction = getTransactionById(transactionId);
        if (transaction.isOwnerAgreement()) {
            throw new RuntimeException("The transaction is already approved by owner.");
        }
        //hittar plantan till recipient och plantan till owner, med hjälp av id, använder metoden getPlantById från plantService samt getter
        Plant ownerPlant = plantService.getPlantById(transaction.getOwnerPlantId());
        Plant recipientPlant = plantService.getPlantById(transaction.getRecipientPlantId());
        //hittar owner och recipient med hjälp av id, använder metoden getUserById från userService
        User owner = userService.getUserById(transaction.getOwnerId());
        User recipient = userService.getUserById(transaction.getRecipientId());
        //Status ska ändras från "RESERVED" till "AVAILABLE"
        ownerPlant.setPlantStatus(PlantStatus.AVAILABLE);
        recipientPlant.setPlantStatus(PlantStatus.AVAILABLE);
        //Updaterar planta
        plantService.updatePlantById(ownerPlant.getId(), ownerPlant);
        plantService.updatePlantById(recipientPlant.getId(), recipientPlant);
        //tar bort transaktionen från transaction repositoriet och från owner med hjälp av metoden deleteTransaction
        deleteTransaction(transaction.getId(), owner.getId());
    }


    //metod för att hämta alla transaktioner
    public List<Transaction> getAllTransactions(){
        return transactionRepository.findAll();
    }

    //metod för att hämta en transaktion efter id, kastar fel om inte hittar transactionen i repositoriet
    public Transaction getTransactionById(String transactionId){
        return transactionRepository.findById(transactionId)
                .orElseThrow(() -> new RuntimeException("Transaction not found with id: " + transactionId));
    }

    //metod för att hämta alla transaktioner hos en user
    public List<Transaction> getAllTransactionForByOwnerId(String userId){
        User user = userService.getUserById(userId);
        return transactionRepository.findAllByOwnerId(userId);

    }

    //metod för att updatera en transaction
    public Transaction updateTransactionById(String transactionId, Transaction newTransaction) {
        //hittar transaction efter id med metoden getTransactionById
        Transaction existingTransaction = getTransactionById(transactionId);
        //uppdaterar alla attributes
        //ser till att nya transaktion attributer är inte null, utan det skrivs de över och de gamla attributer försinner
        if (newTransaction.getOwnerPlantId() != null) {
            existingTransaction.setOwnerPlantId(newTransaction.getOwnerPlantId());
        }
        if (newTransaction.getOwnerId() != null) {
            existingTransaction.setOwnerId(newTransaction.getOwnerId());
        }
        if (newTransaction.getRecipientId() != null) {
            existingTransaction.setRecipientId(newTransaction.getRecipientId());
        }
        if (newTransaction.isOwnerAgreement()) {
            existingTransaction.setOwnerAgreement(true);
        } else {
            existingTransaction.setOwnerAgreement(false);
        }
        if (newTransaction.isRecipientAgreement()) {
            existingTransaction.setRecipientAgreement(true);
        } else {
            existingTransaction.setRecipientAgreement(false);
        }
        if (newTransaction.getTransasctionDate() != null){
            existingTransaction.setTransasctionDate(newTransaction.getTransasctionDate());
        }
            //sparade uppdatedade transactionen till transaction repository
            return transactionRepository.save(existingTransaction);
    }

    public void deleteTransaction(String transactionId, String ownerId){
        Transaction transaction = getTransactionById(transactionId);
        transactionRepository.delete(transaction);
        User owner = userService.getUserById(ownerId);
        owner.getTransactions().remove(transaction);
        userRepository.save(owner);
        User recipient = userService.getUserById(transaction.getRecipientId());
        recipient.getTransactions().remove(transaction);
        userRepository.save(recipient);
    }
}
