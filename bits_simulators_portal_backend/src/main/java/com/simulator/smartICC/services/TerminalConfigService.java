package com.simulator.smartICC.services;

import com.simulator.atm.controllers.controlleratm.LogNDCController;
import com.simulator.config.GlobalVars;
import com.simulator.entities.UserActivityTracking;
import com.simulator.entities.UserManagement;
import com.simulator.globalModels.ResponseApiJson;
import com.simulator.repository.UserActivityTrackingRepository;
import com.simulator.smartICC.models.TerminalConfig;
import com.simulator.smartICC.repositories.TerminalConfigRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TerminalConfigService {
    private final TerminalConfigRepository terminalConfigRepository;


    private final Logger logger = LogManager.getLogger(LogNDCController.class);
    @Autowired
    UserActivityTrackingRepository userActivityTrackingRepository;

    @Autowired
    GlobalVars globalVars;


    @Autowired
    public TerminalConfigService(TerminalConfigRepository terminalConfigRepository) {
        this.terminalConfigRepository = terminalConfigRepository;
    }

    // TerminalConfigService.java




    public ResponseApiJson<TerminalConfig> insertTerminalConfig(TerminalConfig terminalConfig) {
        String idRequest = "INSERT_TERMINAL_CONFIG_" + new Date().getTime() + (Math.random() * 9999);
        Optional<UserManagement> userM = globalVars.getUser(GlobalVars.getBearerTokenHeader());

        try {
            TerminalConfig insertedTerminalConfig = terminalConfigRepository.save(terminalConfig);
            UserActivityTracking userTrace = new UserActivityTracking(userM.get().getUserCode(), "Insert", "Terminal Config", "000.000.00.00", "Success", GlobalVars.SUCCESS, "Insert Terminal Config", "Terminal Config inserted successfully", new Date());
            userActivityTrackingRepository.save(userTrace);
            return new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS, "Inserting Terminal Config successful", insertedTerminalConfig);
        } catch (Exception ex) {
            logger.error("Exception in insertTerminalConfig: " + ex.getMessage());
            UserActivityTracking userTrace = new UserActivityTracking(userM.get().getUserCode(), "Insert", "Terminal Config", "000.000.00.00", "Error", GlobalVars.EXCEPTIONSISSUE, "Insert Terminal Config", "An error occurred while inserting Terminal Config", new Date());
            userActivityTrackingRepository.save(userTrace);
            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE, "Some issues in insertTerminalConfig, please check with your provider !");
        }
    }

    public ResponseApiJson<List<TerminalConfig>> getAllTerminalConfigs() {
        String idRequest = "PROFILE_" + new Date().getTime() + (Math.random() * 9999);
        Optional<UserManagement> userM = globalVars.getUser(GlobalVars.getBearerTokenHeader());
        logger.info("---->userM:" + userM);
        try {
            List<TerminalConfig> profiles = terminalConfigRepository.findAll();
            UserActivityTracking userTrace = new UserActivityTracking(userM.get().getUserCode(), "Retrieve", "Terminal Config ", "000.000.00.00", "Success", GlobalVars.SUCCESS, "Retrieve Terminal Config", "Terminal Config retrieved successfully", new Date());
            userActivityTrackingRepository.save(userTrace);
            return new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS, "Getting EMV profiles successful", profiles);
        } catch (Exception ex) {
            logger.error("Exception in getAllProfiles: " + ex.getMessage());
            UserActivityTracking userTrace = new UserActivityTracking(userM.get().getUserCode(), "Retrieve", "Terminal Config", "000.000.00.00", "Error", GlobalVars.EXCEPTIONSISSUE, "Retrieve Terminal Config", "An error occurred while retrieving Terminal Config", new Date());
            userActivityTrackingRepository.save(userTrace);
            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE, "Some issues in getAllTerminalConfig, please check with your provider !");
        }
    }


    public ResponseApiJson<TerminalConfig> getTermenalById(String code_profil) {
        String idRequest = "SEARCH_PROFILE_" + new Date().getTime() + (Math.random() * 9999);

        try {
            Optional<TerminalConfig> profileOptional = terminalConfigRepository.findById(code_profil);

            if (profileOptional.isPresent()) {
                TerminalConfig terminalConfig = profileOptional.get();
                return new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS, "Terminal Config found successfully", terminalConfig);
            } else {
                return new ResponseApiJson<>(idRequest, GlobalVars.NOTEXIST, "Terminal Config not found", null);
            }
        } catch (Exception ex) {
            logger.error("Exception in searchProfil: " + ex.getMessage());
            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE, "Some issues in searchProfil, please check with your provider !");
        }
    }

    public ResponseApiJson<String> deleteTerminalConfig(String code_profil) {
        String idRequest = "DELETE_PROFILE_" + new Date().getTime() + (Math.random() * 9999);
        Optional<UserManagement> userM = globalVars.getUser(GlobalVars.getBearerTokenHeader());

        try {
            terminalConfigRepository.deleteById(code_profil);
            UserActivityTracking userTrace = new UserActivityTracking(userM.get().getUserCode(), "Delete", "Terminal Config", "000.000.00.00", "Success", GlobalVars.SUCCESS, "Delete Terminal Config", "Terminal Config deleted successfully", new Date());
            userActivityTrackingRepository.save(userTrace);
            return new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS, "Deleting Terminal Config successful", code_profil);
        } catch (Exception ex) {
            logger.error("Exception in deleteTerminalConfig: " + ex.getMessage());
            UserActivityTracking userTrace = new UserActivityTracking(userM.get().getUserCode(), "Delete", "Terminal Config", "000.000.00.00", "Error", GlobalVars.EXCEPTIONSISSUE, "Delete Terminal Config", "An error occurred while deleting Terminal Config", new Date());
            userActivityTrackingRepository.save(userTrace);
            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE, "Some issues in deleteTerminalConfig, please check with your provider !");
        }
    }





}

