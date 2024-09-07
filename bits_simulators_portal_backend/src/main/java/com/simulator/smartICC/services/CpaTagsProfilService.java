package com.simulator.smartICC.services;

import com.simulator.config.GlobalVars;
import com.simulator.globalModels.ResponseApiJson;
import com.simulator.repository.UserActivityTrackingRepository;
import com.simulator.smartICC.models.CpaTagsProfil;
import com.simulator.smartICC.repositories.CpaTagsProfilRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CpaTagsProfilService {

    @Autowired
    private CpaTagsProfilRepository cpaTagsProfilRepository;


    @Autowired
    UserActivityTrackingRepository userActivityTrackingRepository;

    @Autowired
    GlobalVars globalVars;



    public ResponseApiJson<List<CpaTagsProfil>> getTagCpaByCodeProfile(String codeProfile) {
        String idRequest = "GET_TAG_DEFINITION_" + System.currentTimeMillis();
//        logger.info("---->codeProfile:"+codeProfile);
        try {
            List<CpaTagsProfil> tagDefinitionOptional = cpaTagsProfilRepository.findByProfileCode(codeProfile);
//            logger.info("---->tagDefinitionOptional.isPresent:"+tagDefinitionOptional);
            if (tagDefinitionOptional.size()>0) {

                return new ResponseApiJson<List<CpaTagsProfil>>(idRequest, GlobalVars.SUCCESS, "Tag Cpa found successfully", tagDefinitionOptional);
            } else {
                return new ResponseApiJson<>(idRequest, GlobalVars.NOTEXIST, "Tag Cpa not found for code profile: " + codeProfile, null);
            }
        } catch (Exception ex) {
//            logger.error("Exception in getTagDefinitionByCodeProfile: " + ex.getMessage());
            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE, "Some issues occurred while retrieving tag cpa, please check with your provider !");
        }
    }



    public ResponseApiJson<List<CpaTagsProfil>> saveTagsCpaProfile(List<CpaTagsProfil> listTag) {
        String idRequest = "INSERT_TAG_DEFINITION_" + System.currentTimeMillis();
//        Optional<UserManagement> userM = globalVars.getUser(GlobalVars.getBearerTokenHeader());

        try {
            List<CpaTagsProfil> insertedTagsDefinition = cpaTagsProfilRepository.saveAll(listTag);
//            UserActivityTracking userTrace = new UserActivityTracking(userM.get().getUserCode(), "Insert", "Tag Definition", "000.000.00.00", "Success", GlobalVars.SUCCESS, "Insert Tag Definition", "Tag Definition inserted successfully", new Date());
//            userActivityTrackingRepository.save(userTrace);
            return new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS, "Inserting Tag Definition successful", insertedTagsDefinition);
        } catch (Exception ex) {
//            logger.error("Exception in saveTagDefinition: " + ex.getMessage());
//            UserActivityTracking userTrace = new UserActivityTracking(userM.get().getUserCode(), "Insert", "Tag Definition", "000.000.00.00", "Error", GlobalVars.EXCEPTIONSISSUE, "Insert Tag Definition", "An error occurred while inserting Tag Definition", new Date());
//            userActivityTrackingRepository.save(userTrace);
            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE, "Some issues occurred while inserting Tag Definition, please check with your provider!");
        }
    }


    public ResponseApiJson<String> deleteTagsCpaProfile(String code_profil) {
        String idRequest = "DELETE_CPA_PROFILE_" + new Date().getTime() + (Math.random() * 9999);
//        Optional<UserManagement> userM = globalVars.getUser(GlobalVars.getBearerTokenHeader());

        try {
            cpaTagsProfilRepository.deleteByProfileCode(code_profil);
//            UserActivityTracking userTrace = new UserActivityTracking(userM.get().getUserCode(), "Delete", "EMV profile", "000.000.00.00", "Success", GlobalVars.SUCCESS, "Delete CPA profile", "CPA profile deleted successfully", new Date());
//            userActivityTrackingRepository.save(userTrace);
            return new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS, "Deleting CPA profile successful", code_profil);
        } catch (Exception ex) {
//            logger.error("Exception in deleteProfile: " + ex.getMessage());
//            UserActivityTracking userTrace = new UserActivityTracking(userM.get().getUserCode(), "Delete", "CPA profile", "000.000.00.00", "Error", GlobalVars.EXCEPTIONSISSUE, "Delete CPA profile", "An error occurred while deleting CPA profile", new Date());
//            userActivityTrackingRepository.save(userTrace);
            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE, "Some issues in deleteProfile, please check with your provider !");
        }
    }



}
