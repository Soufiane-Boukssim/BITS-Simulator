package com.simulator.smartICC.services;

import com.simulator.atm.controllers.controlleratm.LogNDCController;
import com.simulator.config.GlobalVars;
import com.simulator.entities.UserActivityTracking;
import com.simulator.entities.UserManagement;
import com.simulator.globalModels.ResponseApiJson;
import com.simulator.repository.UserActivityTrackingRepository;
import com.simulator.smartICC.models.CPAProfil;
import com.simulator.smartICC.repositories.CPAProfilRepository;
import com.simulator.smartICC.repositories.CpaTagsProfilRepository;
import com.simulator.smartICC.repositories.KeyParamRepository;
import com.simulator.smartICC.repositories.TestCaseRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CPAProfilService {

    @Autowired
    private CPAProfilRepository cPAProfilRepository;
    @Autowired
    private TestCaseRepository testCaseRepository;

    @Autowired
    private KeyParamRepository keyParamRepository;

    @Autowired
    private CpaTagsProfilRepository cpaTagsProfilRepository;

    private final Logger logger = LogManager.getLogger(LogNDCController.class);
    @Autowired
    UserActivityTrackingRepository userActivityTrackingRepository;

    @Autowired
    GlobalVars globalVars;




    public ResponseApiJson<List<CPAProfil>> getAllCPAProfiles() {
        String idRequest = "CPA_PROFILE_" + new Date().getTime() + (Math.random() * 9999);
        Optional<UserManagement> userM = globalVars.getUser(GlobalVars.getBearerTokenHeader());
        logger.info("---->userM:" + userM);
        try {
            List<CPAProfil> profiles = cPAProfilRepository.findAll();
            UserActivityTracking userTrace = new UserActivityTracking(userM.get().getUserCode(), "Retrieve", "CPA profiles", "000.000.00.00", "Success", GlobalVars.SUCCESS, "Retrieve CPA profiles", "CPA profiles retrieved successfully", new Date());
            userActivityTrackingRepository.save(userTrace);
            return new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS, "Getting CPA profiles successful", profiles);
        } catch (Exception ex) {
            logger.error("Exception in getAllCPAProfiles: " + ex.getMessage());
            UserActivityTracking userTrace = new UserActivityTracking(userM.get().getUserCode(), "Retrieve", "CPA profiles", "000.000.00.00", "Error", GlobalVars.EXCEPTIONSISSUE, "Retrieve CPA profiles", "An error occurred while retrieving CPA profiles", new Date());
            userActivityTrackingRepository.save(userTrace);
            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE, "Some issues in getAllCPAProfiles, please check with your provider !");
        }
    }

    public ResponseApiJson<CPAProfil> getCPAProfilById(String code_profil) {
        String idRequest = "SEARCH_PROFILE_CPA" + new Date().getTime() + (Math.random() * 9999);

        try {
            Optional<CPAProfil> profileOptional = cPAProfilRepository.findById(code_profil);

            if (profileOptional.isPresent()) {
                CPAProfil cpaProfil = profileOptional.get();
                return new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS, "Cpa profile  found successfully", cpaProfil);
            } else {
                return new ResponseApiJson<>(idRequest, GlobalVars.NOTEXIST, "Cpa profile  not found", null);
            }
        } catch (Exception ex) {
            logger.error("Exception in searchProfil: " + ex.getMessage());
            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE, "Some issues in get Cpa profile , please check with your provider !");
        }
    }





    public ResponseApiJson<CPAProfil> insertCPAProfile(CPAProfil profile) {
        String idRequest = "INSERT_CPA_PROFILE_" + new Date().getTime() + (Math.random() * 9999);
        Optional<UserManagement> userM = globalVars.getUser(GlobalVars.getBearerTokenHeader());
        logger.info("---->userM:" + userM);
        try {
            CPAProfil insertedProfile = cPAProfilRepository.save(profile);
            UserActivityTracking userTrace = new UserActivityTracking(userM.get().getUserCode(), "Insert", "Cpa profile", "000.000.00.00", "Success", GlobalVars.SUCCESS, "CPA  profile", "CPA profile inserted successfully", new Date());
            userActivityTrackingRepository.save(userTrace);
            return new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS, "Inserting EMV profile successful", insertedProfile);
        } catch (Exception ex) {
            logger.error("Exception in insertProfile: " + ex.getMessage());
            UserActivityTracking userTrace = new UserActivityTracking(userM.get().getUserCode(), "Insert", "CPA profile", "000.000.00.00", "Error", GlobalVars.EXCEPTIONSISSUE, "CPA  profile", "An error occurred while inserting CPA profile", new Date());
            userActivityTrackingRepository.save(userTrace);
            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE, "Some issues in insertProfile, please check with your provider !");
        }
    }

    @Async
    public ResponseApiJson<String> deleteProfile(String code_profil) {
        String idRequest = "DELETE_CPA_PROFILE_" + new Date().getTime() + (Math.random() * 9999);
        Optional<UserManagement> userM = globalVars.getUser(GlobalVars.getBearerTokenHeader());

        try {
            cPAProfilRepository.deleteById(code_profil);
            testCaseRepository.deleteByProfileCode(code_profil);
            cpaTagsProfilRepository.deleteByProfileCode(code_profil);
            keyParamRepository.deleteById(code_profil);



            UserActivityTracking userTrace = new UserActivityTracking(userM.get().getUserCode(), "Delete", "EMV profile", "000.000.00.00", "Success", GlobalVars.SUCCESS, "Delete CPA profile", "CPA profile deleted successfully", new Date());
            userActivityTrackingRepository.save(userTrace);
            return new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS, "Deleting CPA profile successful", code_profil);
        } catch (Exception ex) {
            logger.error("Exception in deleteProfile: " + ex.getMessage());
            UserActivityTracking userTrace = new UserActivityTracking(userM.get().getUserCode(), "Delete", "CPA profile", "000.000.00.00", "Error", GlobalVars.EXCEPTIONSISSUE, "Delete CPA profile", "An error occurred while deleting CPA profile", new Date());
            userActivityTrackingRepository.save(userTrace);
            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE, "Some issues in deleteProfile, please check with your provider !");
        }
    }




}
