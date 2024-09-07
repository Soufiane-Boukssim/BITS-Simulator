package com.simulator.smartICC.services;

import com.simulator.atm.controllers.controlleratm.LogNDCController;
import com.simulator.config.GlobalVars;
import com.simulator.entities.UserActivityTracking;
import com.simulator.entities.UserManagement;
import com.simulator.globalModels.ResponseApiJson;
import com.simulator.repository.UserActivityTrackingRepository;
import com.simulator.smartICC.models.TransactionConfig;
import com.simulator.smartICC.repositories.TransactionConfigRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionConfigService {
    private final TransactionConfigRepository transactionConfigRepository;
    private final Logger logger = LogManager.getLogger(LogNDCController.class);
    @Autowired
    UserActivityTrackingRepository userActivityTrackingRepository;

    @Autowired
    GlobalVars globalVars;


    @Autowired
    public TransactionConfigService(TransactionConfigRepository transactionConfigRepository) {
        this.transactionConfigRepository = transactionConfigRepository;
    }


    public ResponseApiJson<TransactionConfig> insertTransactionConfig(TransactionConfig transactionConfig) {
        String idRequest = "INSERT_TRANSACTION_CONFIG_" + new Date().getTime() + (Math.random() * 9999);
        Optional<UserManagement> userM = globalVars.getUser(GlobalVars.getBearerTokenHeader());

        try {
            TransactionConfig insertedTransactionConfig = transactionConfigRepository.save(transactionConfig);
            UserActivityTracking userTrace = new UserActivityTracking(userM.get().getUserCode(), "Insert", "Transaction Config", "000.000.00.00", "Success", GlobalVars.SUCCESS, "Insert Transaction Config", "Transaction Config inserted successfully", new Date());
            userActivityTrackingRepository.save(userTrace);
            return new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS, "Inserting Transaction Config successful", insertedTransactionConfig);
        } catch (Exception ex) {
            logger.error("Exception in insertTransactionConfig: " + ex.getMessage());
            UserActivityTracking userTrace = new UserActivityTracking(userM.get().getUserCode(), "Insert", "Transaction Config", "000.000.00.00", "Error", GlobalVars.EXCEPTIONSISSUE, "Insert Transaction Config", "An error occurred while inserting Transaction Config", new Date());
            userActivityTrackingRepository.save(userTrace);
            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE, "Some issues in insertTransactionConfig, please check with your provider !");
        }
    }


    public ResponseApiJson<List<TransactionConfig>> getAllTransactionConfigs() {
        String idRequest = "GET_ALL_TRANSACTION_CONFIGS_" + new Date().getTime() + (Math.random() * 9999);
        Optional<UserManagement> userM = globalVars.getUser(GlobalVars.getBearerTokenHeader());

        try {
            List<TransactionConfig> transactionConfigs = transactionConfigRepository.findAll();
            UserActivityTracking userTrace = new UserActivityTracking(userM.get().getUserCode(), "Retrieve", "Transaction Configs", "000.000.00.00", "Success", GlobalVars.SUCCESS, "Retrieve Transaction Configs", "Transaction Configs retrieved successfully", new Date());
            userActivityTrackingRepository.save(userTrace);
            return new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS, "Getting Transaction Configs successful", transactionConfigs);
        } catch (Exception ex) {
            logger.error("Exception in getAllTransactionConfigs: " + ex.getMessage());
            UserActivityTracking userTrace = new UserActivityTracking(userM.get().getUserCode(), "Retrieve", "Transaction Configs", "000.000.00.00", "Error", GlobalVars.EXCEPTIONSISSUE, "Retrieve Transaction Configs", "An error occurred while retrieving Transaction Configs", new Date());
            userActivityTrackingRepository.save(userTrace);
            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE, "Some issues in getAllTransactionConfigs, please check with your provider !");
        }
    }


    public ResponseApiJson<TransactionConfig> getTransactionConfigById(String code_profil) {
        String idRequest = "SEARCH_PROFILE_" + new Date().getTime() + (Math.random() * 9999);

        try {
            Optional<TransactionConfig> profileOptional = transactionConfigRepository.findById(code_profil);

            if (profileOptional.isPresent()) {
                TransactionConfig transactionConfig = profileOptional.get();
                return new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS, "Terminal Config found successfully", transactionConfig);
            } else {
                return new ResponseApiJson<>(idRequest, GlobalVars.NOTEXIST, "Terminal Config not found", null);
            }
        } catch (Exception ex) {
            logger.error("Exception in getTransactionConfigById  : " + ex.getMessage());
            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE, "Some issues in searchProfil, please check with your provider !");
        }
    }


    // Méthodes du service (CRUD, etc.)
    public ResponseApiJson<String> deleteTransactionConfig(String code_profil) {
        String idRequest = "DELETE_TRANSACTION_CONFIG_" + new Date().getTime() + (Math.random() * 9999);
        Optional<UserManagement> userM = globalVars.getUser(GlobalVars.getBearerTokenHeader());

        try {
            transactionConfigRepository.deleteById(code_profil);
            UserActivityTracking userTrace = new UserActivityTracking(userM.get().getUserCode(), "Delete", "Transaction Config", "000.000.00.00", "Success", GlobalVars.SUCCESS, "Delete Transaction Config", "Transaction Config deleted successfully", new Date());
            userActivityTrackingRepository.save(userTrace);
            return new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS, "Deleting Transaction Config successful", code_profil);
        } catch (Exception ex) {
            logger.error("Exception in deleteTransactionConfig: " + ex.getMessage());
            UserActivityTracking userTrace = new UserActivityTracking(userM.get().getUserCode(), "Delete", "Transaction Config", "000.000.00.00", "Error", GlobalVars.EXCEPTIONSISSUE, "Delete Transaction Config", "An error occurred while deleting Transaction Config", new Date());
            userActivityTrackingRepository.save(userTrace);
            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE, "Some issues in deleteTransactionConfig, please check with your provider !");
        }
    }


}

