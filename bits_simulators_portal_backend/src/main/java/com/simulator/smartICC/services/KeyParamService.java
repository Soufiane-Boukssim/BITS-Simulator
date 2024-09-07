package com.simulator.smartICC.services;

import com.simulator.atm.controllers.controlleratm.LogNDCController;
import com.simulator.config.GlobalVars;
import com.simulator.entities.UserActivityTracking;
import com.simulator.entities.UserManagement;
import com.simulator.globalModels.ResponseApiJson;
import com.simulator.repository.UserActivityTrackingRepository;
import com.simulator.smartICC.models.KeyParam;
import com.simulator.smartICC.repositories.KeyParamRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class KeyParamService {
    @Autowired
    private KeyParamRepository keyParamRepository;

    private final Logger logger = LogManager.getLogger(LogNDCController.class);
    @Autowired
    UserActivityTrackingRepository userActivityTrackingRepository;

    @Autowired
    GlobalVars globalVars;


    public ResponseApiJson<KeyParam> keyParamCPAProfilById(String code_profil) {
        String idRequest = "KEY_PROFILE_CPA" + new Date().getTime() + (Math.random() * 9999);

        try {
            Optional<KeyParam> profileOptional = keyParamRepository.findById(code_profil);

            if (profileOptional.isPresent()) {
                KeyParam keyParam = profileOptional.get();
                return new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS, "key profile  found successfully", keyParam);
            } else {
                return new ResponseApiJson<>(idRequest, GlobalVars.NOTEXIST, "key profile  not found", null);
            }
        } catch (Exception ex) {
            logger.error("Exception in searchProfil: " + ex.getMessage());
            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE, "Some issues in get key profile , please check with your provider !");
        }
    }


    public ResponseApiJson<KeyParam> insertKeyParamCPAProfile(KeyParam profile) {
        String idRequest = "INSERT_Key_CPA_PROFILE_" + new Date().getTime() + (Math.random() * 9999);
        Optional<UserManagement> userM = globalVars.getUser(GlobalVars.getBearerTokenHeader());
        logger.info("---->userM:" + userM);
        try {
            KeyParam insertedProfile = keyParamRepository.save(profile);
            UserActivityTracking userTrace = new UserActivityTracking(userM.get().getUserCode(), "Insert", "key param cpa profile", "000.000.00.00", "Success", GlobalVars.SUCCESS, "key param cpa  profile", "key param cpa profile inserted successfully", new Date());
            userActivityTrackingRepository.save(userTrace);
            return new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS, "Inserting key param profile successful", insertedProfile);
        } catch (Exception ex) {
            logger.error("Exception in insertProfile: " + ex.getMessage());
            UserActivityTracking userTrace = new UserActivityTracking(userM.get().getUserCode(), "Insert", "key param cpa profile", "000.000.00.00", "Error", GlobalVars.EXCEPTIONSISSUE, "key param cpa  profile", "An error occurred while inserting key param cpa profile", new Date());
            userActivityTrackingRepository.save(userTrace);
            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE, "Some issues in insertProfile, please check with your provider !");
        }
    }




    public ResponseApiJson<String> deleteKeyParamCpa(String code_profil) {
        String idRequest = "DELETE_CPA_KEY_PROFILE_" + new Date().getTime() + (Math.random() * 9999);
        Optional<UserManagement> userM = globalVars.getUser(GlobalVars.getBearerTokenHeader());

        try {
            keyParamRepository.deleteById(code_profil);
            UserActivityTracking userTrace = new UserActivityTracking(userM.get().getUserCode(), "Delete", "EMV profile", "000.000.00.00", "Success", GlobalVars.SUCCESS, "Delete CPA profile", "CPA KEY profile deleted successfully", new Date());
            userActivityTrackingRepository.save(userTrace);
            return new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS, "Deleting KEY CPA profile successful", code_profil);
        } catch (Exception ex) {
            logger.error("Exception in delete KEY CPAProfile: " + ex.getMessage());
            UserActivityTracking userTrace = new UserActivityTracking(userM.get().getUserCode(), "Delete", "CPA profile", "000.000.00.00", "Error", GlobalVars.EXCEPTIONSISSUE, "Delete CPA  KEY profile", "An error occurred while deleting CPA KEY profile", new Date());
            userActivityTrackingRepository.save(userTrace);
            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE, "Some issues in deleteProfile, please check with your provider !");
        }
    }


}
