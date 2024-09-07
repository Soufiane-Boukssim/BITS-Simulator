package com.simulator.atm.services.atmm;


import com.simulator.atm.controllers.controlleratm.LogNDCController;
import com.simulator.atm.repositories.repoatm.*;
import com.simulator.config.GlobalVars;
import com.simulator.entities.*;
import com.simulator.globalModels.ResponseApiJson;
import com.simulator.repository.LogNDCAIDRepository;
import com.simulator.repository.UserActivityTrackingRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class LogNDCService {
    @Autowired
    LogNDCAIDRepository logNDCAIDRepository;
    @Autowired
    LogNDCConfigurationRepository logNDCConfigurationRepository;
    @Autowired
    LogNDCFitRepository logNDCFitRepository;
    @Autowired
    LogNDCKeyRepository logNDCKeyRepository;
    @Autowired
    LogNDCScreenRepository logNDCScreenRepository;
    @Autowired
    LogNDCStateRepository logNDCStateRepository;
    @Autowired
    LogNDCTimerRepository logNDCTimerRepository;
    private JSONObject joResp = new JSONObject();



    @Autowired
    UserActivityTrackingRepository userActivityTrackingRepository;

    @Autowired
    GlobalVars globalVars;

    private final Logger logger = LogManager.getLogger(LogNDCController.class);


    public ResponseApiJson<List<LogNDCConfiguration>> getNDCConfiguration(String bankCode) {
        String idRequest = "NDC_" + new Date().getTime() + (Math.random() * 9999);
        Optional<UserManagement> userM = globalVars.getUser(GlobalVars.getBearerTokenHeader());
        logger.info("---->userM:"+userM);
        try {
//            List<LogNDCConfiguration> data = logNDCConfigurationRepository.findAll();
            List<LogNDCConfiguration> data = logNDCConfigurationRepository.findById_BankCode(bankCode);
            UserActivityTracking userTrace = new UserActivityTracking(userM.get().getUserCode(), "Retrieve", "NDC configuration", "000.000.00.00", "Success", GlobalVars.SUCCESS, "Retrieve NDC configuration", "NDC configuration retrieved successfully", new Date());
            userActivityTrackingRepository.save(userTrace);
            return new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS, "Getting NDC configuration successful", data);

        } catch (Exception ex) {
            logger.error("Exception in getNDCConfiguration: " + ex.getMessage());
            UserActivityTracking userTrace = new UserActivityTracking(userM.get().getUserCode(), "Retrieve", "NDC configuration", "000.000.00.00", "Error", GlobalVars.EXCEPTIONSISSUE, "Retrieve NDC configuration", "An error occurred while retrieving NDC configuration", new Date());
            userActivityTrackingRepository.save(userTrace);
            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE, "Some issues in getNDCConfiguration, please check with your provider !");
        }
    }




    public ResponseApiJson<List<LogNDCScreen>> getNDCScreen(String bankCode) {
        String idRequest = "NDC_" + new Date().getTime() + (Math.random() * 9999);
        Optional<UserManagement> userM = globalVars.getUser(GlobalVars.getBearerTokenHeader());
        try {
            List<LogNDCScreen> data = logNDCScreenRepository.findById_BankCode(bankCode);

            UserActivityTracking userTrace = new UserActivityTracking(userM.get().getUserCode(), "Retrieve", "NDC screen", "000.000.00.00", "Success", GlobalVars.SUCCESS, "Retrieve NDC screen", "NDC screen retrieved successfully", new Date());
            userActivityTrackingRepository.save(userTrace);

            return new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS, "Getting NDC screen successful", data);

        } catch (Exception ex) {
            logger.error("Exception in getNDCScreen: " + ex.getMessage());
            UserActivityTracking userTrace = new UserActivityTracking(userM.get().getUserCode(), "Retrieve", "NDC screen", "000.000.00.00", "Error", GlobalVars.EXCEPTIONSISSUE, "Retrieve NDC screen", "Some issues in getNDCScreen, please check with your provider !", new Date());
            userActivityTrackingRepository.save(userTrace);
            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE, "Some issues in getNDCScreen, please check with your provider !");
        }
    }




    public ResponseApiJson<List<LogNDCState>> getNDCState(String bankCode) {
        String idRequest = "NDC_" + new Date().getTime() + (Math.random() * 9999);
        Optional<UserManagement> userM = globalVars.getUser(GlobalVars.getBearerTokenHeader());
        try {
            List<LogNDCState> data = logNDCStateRepository.findById_BankCode(bankCode);

            UserActivityTracking userTrace = new UserActivityTracking(userM.get().getUserCode(), "Retrieve", "NDC state", "000.000.00.00", "Success", GlobalVars.SUCCESS, "Retrieve NDC state", "NDC state retrieved successfully", new Date());
            userActivityTrackingRepository.save(userTrace);

            return new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS, "Getting NDC state successful", data);

        } catch (Exception ex) {
            logger.error("Exception in getNDCState: " + ex.getMessage());
            UserActivityTracking userTrace = new UserActivityTracking(userM.get().getUserCode(), "Retrieve", "NDC state", "000.000.00.00", "Error", GlobalVars.EXCEPTIONSISSUE, "Retrieve NDC state", "Some issues in getNDCState, please check with your provider !", new Date());
            userActivityTrackingRepository.save(userTrace);
            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE, "Some issues in getNDCState, please check with your provider !");
        }
    }





    public ResponseApiJson<List<LogNDCFit>> getNDCFIT(String bankCode) {
        String idRequest = "NDC_" + new Date().getTime() + (Math.random() * 9999);
        Optional<UserManagement> userM = globalVars.getUser(GlobalVars.getBearerTokenHeader());
        try {
            List<LogNDCFit> data = logNDCFitRepository.findById_BankCode(bankCode);

            UserActivityTracking userTrace = new UserActivityTracking(userM.get().getUserCode(), "Retrieve", "NDC FIT", "000.000.00.00", "Success", GlobalVars.SUCCESS, "Retrieve FIT state", "NDC FIT retrieved successfully", new Date());
            userActivityTrackingRepository.save(userTrace);

            return new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS, "Getting NDC FIT successful", data);

        } catch (Exception ex) {
            logger.error("Exception in getNDCFIT: " + ex.getMessage());
            UserActivityTracking userTrace = new UserActivityTracking(userM.get().getUserCode(), "Retrieve", "NDC FIT", "000.000.00.00", "Error", GlobalVars.EXCEPTIONSISSUE, "Retrieve FIT state", "Some issues in getNDCFIT, please check with your provider !", new Date());
            userActivityTrackingRepository.save(userTrace);
            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE, "Some issues in getNDCFIT, please check with your provider !");
        }
    }


    public ResponseApiJson<List<LogNDCAID>> getNDCAID(String bankCode) {
        String idRequest = "NDC_" + new Date().getTime() + (Math.random() * 9999);
        Optional<UserManagement> userM = globalVars.getUser(GlobalVars.getBearerTokenHeader());
        try {
            List<LogNDCAID> data = logNDCAIDRepository.findById_BankCode(bankCode);

            UserActivityTracking userTrace = new UserActivityTracking(userM.get().getUserCode(), "Retrieve", "NDC AID", "000.000.00.00", "Success", GlobalVars.SUCCESS, "Retrieve AID state", "NDC AID retrieved successfully", new Date());
            userActivityTrackingRepository.save(userTrace);

            return new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS, "Getting NDC AID successful", data);

        } catch (Exception ex) {
            logger.error("Exception in getNDCAID: " + ex.getMessage());
            UserActivityTracking userTrace = new UserActivityTracking(userM.get().getUserCode(), "Retrieve", "NDC AID", "000.000.00.00", "Error", GlobalVars.EXCEPTIONSISSUE, "Retrieve AID state", "Some issues in getNDCAID, please check with your provider !", new Date());
            userActivityTrackingRepository.save(userTrace);
            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE, "Some issues in getNDCAID, please check with your provider !");
        }
    }




}





















