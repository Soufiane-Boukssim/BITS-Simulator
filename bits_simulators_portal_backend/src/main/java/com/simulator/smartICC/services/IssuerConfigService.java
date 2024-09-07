package com.simulator.smartICC.services;

import com.simulator.atm.controllers.controlleratm.LogNDCController;
import com.simulator.config.GlobalVars;
import com.simulator.entities.UserActivityTracking;
import com.simulator.entities.UserManagement;
import com.simulator.globalModels.ResponseApiJson;
import com.simulator.repository.UserActivityTrackingRepository;
import com.simulator.smartICC.models.IssuerConfig;
import com.simulator.smartICC.repositories.IssuerConfigRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class IssuerConfigService {
    private final IssuerConfigRepository issuerConfigRepository;

    @Autowired
    public IssuerConfigService(IssuerConfigRepository issuerConfigRepository) {
        this.issuerConfigRepository = issuerConfigRepository;
    }
    @Autowired
    UserActivityTrackingRepository userActivityTrackingRepository;

    @Autowired
    GlobalVars globalVars;
    private final Logger logger = LogManager.getLogger(LogNDCController.class);



    public ResponseApiJson<IssuerConfig> insertIssuerConfig(IssuerConfig issuerConfig) {
        String idRequest = "INSERT_ISSUER_CONFIG_" + new Date().getTime() + (Math.random() * 9999);
        Optional<UserManagement> userM = globalVars.getUser(GlobalVars.getBearerTokenHeader());

        try {
            IssuerConfig insertedIssuerConfig = issuerConfigRepository.save(issuerConfig);
            UserActivityTracking userTrace = new UserActivityTracking(userM.get().getUserCode(), "Insert", "Issuer Config", "000.000.00.00", "Success", GlobalVars.SUCCESS, "Insert Issuer Config", "Issuer Config inserted successfully", new Date());
            userActivityTrackingRepository.save(userTrace);
            return new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS, "Inserting Issuer Config successful", insertedIssuerConfig);
        } catch (Exception ex) {
            logger.error("Exception in insertIssuerConfig: " + ex.getMessage());
            UserActivityTracking userTrace = new UserActivityTracking(userM.get().getUserCode(), "Insert", "Issuer Config", "000.000.00.00", "Error", GlobalVars.EXCEPTIONSISSUE, "Insert Issuer Config", "An error occurred while inserting Issuer Config", new Date());
            userActivityTrackingRepository.save(userTrace);
            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE, "Some issues in insertIssuerConfig, please check with your provider !");
        }
    }

    public ResponseApiJson<List<IssuerConfig>> getAllIssuerConfigs() {
        String idRequest = "GET_ALL_ISSUER_CONFIGS_" + new Date().getTime() + (Math.random() * 9999);
        Optional<UserManagement> userM = globalVars.getUser(GlobalVars.getBearerTokenHeader());

        try {
            List<IssuerConfig> issuerConfigs = issuerConfigRepository.findAll();
            UserActivityTracking userTrace = new UserActivityTracking(userM.get().getUserCode(), "Retrieve", "Issuer Configs", "000.000.00.00", "Success", GlobalVars.SUCCESS, "Retrieve Issuer Configs", "Issuer Configs retrieved successfully", new Date());
            userActivityTrackingRepository.save(userTrace);
            return new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS, "Getting Issuer Configs successful", issuerConfigs);
        } catch (Exception ex) {
            logger.error("Exception in getAllIssuerConfigs: " + ex.getMessage());
            UserActivityTracking userTrace = new UserActivityTracking(userM.get().getUserCode(), "Retrieve", "Issuer Configs", "000.000.00.00", "Error", GlobalVars.EXCEPTIONSISSUE, "Retrieve Issuer Configs", "An error occurred while retrieving Issuer Configs", new Date());
            userActivityTrackingRepository.save(userTrace);
            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE, "Some issues in getAllIssuerConfigs, please check with your provider !");
        }
    }


    public ResponseApiJson<IssuerConfig> getIssuerConfigsId(String code_profil) {
        String idRequest = "SEARCH_ISSUER_PROFILE_" + new Date().getTime() + (Math.random() * 9999);
        Optional<UserManagement> userM = globalVars.getUser(GlobalVars.getBearerTokenHeader());

        try {
            Optional<IssuerConfig> profileOptional = issuerConfigRepository.findById(code_profil);

            if (profileOptional.isPresent()) {
                IssuerConfig issuerConfig = profileOptional.get();
                UserActivityTracking userTrace = new UserActivityTracking(userM.get().getUserCode(), "Retrieve", "Issuer Config", "000.000.00.00", "Success", GlobalVars.SUCCESS, "Retrieve Issuer Config", "Issuer Config retrieved successfully", new Date());
                userActivityTrackingRepository.save(userTrace);
                return new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS, "Getting Issuer Config successful", issuerConfig);
            } else {
                return new ResponseApiJson<>(idRequest, GlobalVars.NOTEXIST, "Issuer Config not found", null);
            }
        } catch (Exception ex) {
            logger.error("Exception in getIssuerConfigsId: " + ex.getMessage());
            UserActivityTracking userTrace = new UserActivityTracking(userM.get().getUserCode(), "Retrieve", "Issuer Config", "000.000.00.00", "Error", GlobalVars.EXCEPTIONSISSUE, "Retrieve Issuer Config", "An error occurred while retrieving Issuer Config", new Date());
            userActivityTrackingRepository.save(userTrace);
            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE, "Some issues in getIssuerConfigsId, please check with your provider !");
        }
    }

    public ResponseApiJson<String> deleteIssuerConfig(String code_profil) {
        String idRequest = "DELETE_ISSUER_CONFIG_" + new Date().getTime() + (Math.random() * 9999);
        Optional<UserManagement> userM = globalVars.getUser(GlobalVars.getBearerTokenHeader());

        try {
            Optional<IssuerConfig> issuerConfigOptional = issuerConfigRepository.findById(code_profil);
            if (issuerConfigOptional.isPresent()) {
                issuerConfigRepository.delete(issuerConfigOptional.get());
                UserActivityTracking userTrace = new UserActivityTracking(userM.get().getUserCode(), "Delete", "Issuer Config", "000.000.00.00", "Success", GlobalVars.SUCCESS, "Delete Issuer Config", "Issuer Config deleted successfully", new Date());
                userActivityTrackingRepository.save(userTrace);
                return new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS, "Deleting Issuer Config successful", code_profil);
            } else {
                return new ResponseApiJson<>(idRequest, GlobalVars.NOTEXIST, "Issuer Config not found", null);
            }
        } catch (Exception ex) {
            logger.error("Exception in deleteIssuerConfig: " + ex.getMessage());
            UserActivityTracking userTrace = new UserActivityTracking(userM.get().getUserCode(), "Delete", "Issuer Config", "000.000.00.00", "Error", GlobalVars.EXCEPTIONSISSUE, "Delete Issuer Config", "An error occurred while deleting Issuer Config", new Date());
            userActivityTrackingRepository.save(userTrace);
            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE, "Some issues in deleteIssuerConfig, please check with your provider !");
        }
    }



}
