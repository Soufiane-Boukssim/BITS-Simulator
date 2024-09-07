package com.simulator.smartICC.services;

import com.simulator.atm.controllers.controlleratm.LogNDCController;
import com.simulator.config.GlobalVars;
import com.simulator.entities.UserActivityTracking;
import com.simulator.entities.UserManagement;
import com.simulator.globalModels.ResponseApiJson;
import com.simulator.repository.UserActivityTrackingRepository;
import com.simulator.smartICC.models.EMVProfile;
import com.simulator.smartICC.repositories.EMVProfileRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class EMVProfileService {

    @Autowired
    private EMVProfileRepository profileRepository;

    private final Logger logger = LogManager.getLogger(LogNDCController.class);
    @Autowired
    UserActivityTrackingRepository userActivityTrackingRepository;

    @Autowired
    GlobalVars globalVars;

//    public EMVProfile insertProfile(EMVProfile profile) {
//        return profileRepository.save(profile);
//    }

    public ResponseApiJson<EMVProfile> insertProfile(EMVProfile profile) {
        String idRequest = "INSERT_PROFILE_" + new Date().getTime() + (Math.random() * 9999);
        Optional<UserManagement> userM = globalVars.getUser(GlobalVars.getBearerTokenHeader());
        logger.info("---->userM:" + userM);
        try {
            EMVProfile insertedProfile = profileRepository.save(profile);
            UserActivityTracking userTrace = new UserActivityTracking(userM.get().getUserCode(), "Insert", "EMV profile", "000.000.00.00", "Success", GlobalVars.SUCCESS, "Insert EMV profile", "EMV profile inserted successfully", new Date());
            userActivityTrackingRepository.save(userTrace);
            return new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS, "Inserting EMV profile successful", insertedProfile);
        } catch (Exception ex) {
            logger.error("Exception in insertProfile: " + ex.getMessage());
            UserActivityTracking userTrace = new UserActivityTracking(userM.get().getUserCode(), "Insert", "EMV profile", "000.000.00.00", "Error", GlobalVars.EXCEPTIONSISSUE, "Insert EMV profile", "An error occurred while inserting EMV profile", new Date());
            userActivityTrackingRepository.save(userTrace);
            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE, "Some issues in insertProfile, please check with your provider !");
        }
    }




    public ResponseApiJson<List<EMVProfile>> getAllProfiles() {
        String idRequest = "PROFILE_" + new Date().getTime() + (Math.random() * 9999);
        Optional<UserManagement> userM = globalVars.getUser(GlobalVars.getBearerTokenHeader());
        logger.info("---->userM:" + userM);
        try {
            List<EMVProfile> profiles = profileRepository.findAll();
            UserActivityTracking userTrace = new UserActivityTracking(userM.get().getUserCode(), "Retrieve", "EMV profiles", "000.000.00.00", "Success", GlobalVars.SUCCESS, "Retrieve EMV profiles", "EMV profiles retrieved successfully", new Date());
            userActivityTrackingRepository.save(userTrace);
            return new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS, "Getting EMV profiles successful", profiles);
        } catch (Exception ex) {
            logger.error("Exception in getAllProfiles: " + ex.getMessage());
            UserActivityTracking userTrace = new UserActivityTracking(userM.get().getUserCode(), "Retrieve", "EMV profiles", "000.000.00.00", "Error", GlobalVars.EXCEPTIONSISSUE, "Retrieve EMV profiles", "An error occurred while retrieving EMV profiles", new Date());
            userActivityTrackingRepository.save(userTrace);
            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE, "Some issues in getAllProfiles, please check with your provider !");
        }
    }



    public ResponseApiJson<EMVProfile> searchProfil(String code_profil) {
        String idRequest = "SEARCH_PROFILE_" + new Date().getTime() + (Math.random() * 9999);
        Optional<UserManagement> userM = globalVars.getUser(GlobalVars.getBearerTokenHeader());

        try {
            // Recherche du profil par son code
            Optional<EMVProfile> profileOptional = profileRepository.findByCodeProfile(code_profil);

            if (profileOptional.isPresent()) {
                EMVProfile foundProfile = profileOptional.get();
//                UserActivityTracking userTrace = new UserActivityTracking(userM.get().getUserCode(), "Search", "EMV profile", "000.000.00.00", "Success", GlobalVars.SUCCESS, "Search EMV profile", "EMV profile found successfully", new Date());
//                userActivityTrackingRepository.save(userTrace);
                return new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS, "Search EMV profile successful", foundProfile);
            } else {
//                UserActivityTracking userTrace = new UserActivityTracking(userM.get().getUserCode(), "Search", "EMV profile", "000.000.00.00", "Error", GlobalVars.NOTEXIST, "Search EMV profile", "EMV profile not found", new Date());
//                userActivityTrackingRepository.save(userTrace);
                return new ResponseApiJson<>(idRequest, GlobalVars.NOTEXIST, "EMV profile not found", null);
            }
        } catch (Exception ex) {
            logger.error("Exception in searchProfil: " + ex.getMessage());
            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE, "Some issues in searchProfil, please check with your provider !");
        }
    }




//    public void deleteProfile(String id) {
//        profileRepository.deleteById(id);
//    }


public ResponseApiJson<String> deleteProfile(String code_profil) {
    String idRequest = "DELETE_PROFILE_" + new Date().getTime() + (Math.random() * 9999);
    Optional<UserManagement> userM = globalVars.getUser(GlobalVars.getBearerTokenHeader());

    try {
        profileRepository.deleteById(code_profil);
        UserActivityTracking userTrace = new UserActivityTracking(userM.get().getUserCode(), "Delete", "EMV profile", "000.000.00.00", "Success", GlobalVars.SUCCESS, "Delete EMV profile", "EMV profile deleted successfully", new Date());
        userActivityTrackingRepository.save(userTrace);
        return new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS, "Deleting EMV profile successful", code_profil);
    } catch (Exception ex) {
        logger.error("Exception in deleteProfile: " + ex.getMessage());
        UserActivityTracking userTrace = new UserActivityTracking(userM.get().getUserCode(), "Delete", "EMV profile", "000.000.00.00", "Error", GlobalVars.EXCEPTIONSISSUE, "Delete EMV profile", "An error occurred while deleting EMV profile", new Date());
        userActivityTrackingRepository.save(userTrace);
        return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE, "Some issues in deleteProfile, please check with your provider !");
    }
}



    public ResponseApiJson<String> changeActiveProfile(String code_profil) {
        Optional<UserManagement> userM = globalVars.getUser(GlobalVars.getBearerTokenHeader());
        String idRequest = "TERMINAL_" + new Date().getTime() + (Math.random() * 9999);
        try {
            Optional<EMVProfile> profileOptional = profileRepository.findByCodeProfile(code_profil);
            if (profileOptional.isPresent()) {
                // Désactiver les autres profils
                Optional<EMVProfile> profilesToDeactivateOptional = profileRepository.findByActiveProfile("Y");
                logger.info("--->profilesToDeactivateOptional"+profilesToDeactivateOptional);
                if (profilesToDeactivateOptional.isPresent()) {
                    EMVProfile profileToDeactivate = profilesToDeactivateOptional.get();
                    logger.info("profileToDeactivate--->"+profileToDeactivate);
                    if (!profileToDeactivate.getCodeProfile().equals(code_profil)) {
                        profileToDeactivate.setActiveProfile("N");
                        profileRepository.save(profileToDeactivate);
                    }
                }

                EMVProfile profileToUpdate = profileOptional.get();
                profileToUpdate.setActiveProfile("Y");
                profileRepository.save(profileToUpdate);


                if (profilesToDeactivateOptional.isPresent()) {
                    EMVProfile profileToDeactivate = profilesToDeactivateOptional.get();
                    logger.info("profileToDeactivate--->"+profileToDeactivate);
                    if (!profileToDeactivate.getCodeProfile().equals(code_profil)) {
                        profileToDeactivate.setActiveProfile("N");
                        profileRepository.save(profileToDeactivate);
                    }
                }
                // Enregistrer l'activité de l'utilisateur
                UserActivityTracking userTrace = new UserActivityTracking(userM.get().getUserCode(), "Change", "EMV profile", "000.000.00.00", "Success", GlobalVars.SUCCESS, "Change active EMV profile", "EMV profile changed to active successfully", new Date());
                userActivityTrackingRepository.save(userTrace);

                // Retourner une réponse de succès
                return new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS, "EMV profile changed to active successfully", code_profil);
            } else {
                // Si le profil n'est pas trouvé, retourner une erreur
                return new ResponseApiJson<>(idRequest, GlobalVars.NOTEXIST, "EMV profile not found", code_profil);
            }
        } catch (Exception ex) {
            // En cas d'erreur, enregistrer l'activité de l'utilisateur et retourner un message d'erreur
            logger.error("Exception in change Active: " + ex.getMessage());
            UserActivityTracking userTrace = new UserActivityTracking(userM.get().getUserCode(), "Change", "EMV profile", "000.000.00.00", "Error", GlobalVars.EXCEPTIONSISSUE, "Change active EMV profile", "An error occurred while changing active EMV profile", new Date());
            userActivityTrackingRepository.save(userTrace);
            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE, "An error occurred while changing active EMV profile, please check with your provider !");
        }
    }



}
