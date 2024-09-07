package com.simulator.smartICC.services;

import com.simulator.atm.controllers.controlleratm.LogNDCController;
import com.simulator.config.GlobalVars;
import com.simulator.entities.UserActivityTracking;
import com.simulator.entities.UserManagement;
import com.simulator.globalModels.ResponseApiJson;
import com.simulator.repository.UserActivityTrackingRepository;
import com.simulator.smartICC.models.GetTagsDefinition;
import com.simulator.smartICC.repositories.GetTagsDefinitionRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class GetTagsDefinitionService {
    @Autowired
    private GetTagsDefinitionRepository getTagsDefinitionRepository;

    private final Logger logger = LogManager.getLogger(LogNDCController.class);
    @Autowired
    UserActivityTrackingRepository userActivityTrackingRepository;

    @Autowired
    GlobalVars globalVars;



    public ResponseApiJson<List<GetTagsDefinition>> getTagDefinitionByCodeProfile(String codeProfile) {
        String idRequest = "GET_TAG_DEFINITION_" + System.currentTimeMillis();
          logger.info("---->codeProfile:"+codeProfile);
        try {
            List<GetTagsDefinition> tagDefinitionOptional = getTagsDefinitionRepository.findByCodeProfile(codeProfile);
            logger.info("---->tagDefinitionOptional.isPresent:"+tagDefinitionOptional);
            if (tagDefinitionOptional.size()>0) {

                return new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS, "Tag Definition found successfully", tagDefinitionOptional);
            } else {
                return new ResponseApiJson<>(idRequest, GlobalVars.NOTEXIST, "Tag Definition not found for code profile: " + codeProfile, null);
            }
        } catch (Exception ex) {
            logger.error("Exception in getTagDefinitionByCodeProfile: " + ex.getMessage());
            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE, "Some issues occurred while retrieving tag definition, please check with your provider !");
        }
    }




    public ResponseApiJson<List<GetTagsDefinition>> saveTagDefinition(List<GetTagsDefinition> listTag) {
        String idRequest = "INSERT_TAG_DEFINITION_" + System.currentTimeMillis();
        Optional<UserManagement> userM = globalVars.getUser(GlobalVars.getBearerTokenHeader());

        try {
            List<GetTagsDefinition> insertedTagsDefinition = getTagsDefinitionRepository.saveAll(listTag);
            UserActivityTracking userTrace = new UserActivityTracking(userM.get().getUserCode(), "Insert", "Tag Definition", "000.000.00.00", "Success", GlobalVars.SUCCESS, "Insert Tag Definition", "Tag Definition inserted successfully", new Date());
            userActivityTrackingRepository.save(userTrace);
            return new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS, "Inserting Tag Definition successful", insertedTagsDefinition);
        } catch (Exception ex) {
            logger.error("Exception in saveTagDefinition: " + ex.getMessage());
            UserActivityTracking userTrace = new UserActivityTracking(userM.get().getUserCode(), "Insert", "Tag Definition", "000.000.00.00", "Error", GlobalVars.EXCEPTIONSISSUE, "Insert Tag Definition", "An error occurred while inserting Tag Definition", new Date());
            userActivityTrackingRepository.save(userTrace);
            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE, "Some issues occurred while inserting Tag Definition, please check with your provider!");
        }
    }






    public ResponseApiJson<List<GetTagsDefinition>> deleteTagDefinition(List<GetTagsDefinition> tagDefinitions) {
        String idRequest = "DELETE_TAG_DEFINITION_" + System.currentTimeMillis();
        Optional<UserManagement> userM = globalVars.getUser(GlobalVars.getBearerTokenHeader());

        try {
            List<String> tagIds = tagDefinitions.stream()
                    .map(GetTagsDefinition::getTagId)
                    .collect(Collectors.toList());
            getTagsDefinitionRepository.deleteAllById(tagIds);

            UserActivityTracking userTrace = new UserActivityTracking(userM.get().getUserCode(), "Delete", "Tag Definition", "000.000.00.00", "Success", GlobalVars.SUCCESS, "Delete Tag Definition", "Tag Definitions deleted successfully", new Date());
            userActivityTrackingRepository.save(userTrace);

            return new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS, "Deleting Tag Definitions successful", null);
        } catch (Exception ex) {
            logger.error("Exception in deleteTagDefinition: " + ex.getMessage());

            UserActivityTracking userTrace = new UserActivityTracking(userM.get().getUserCode(), "Delete", "Tag Definition", "000.000.00.00", "Error", GlobalVars.EXCEPTIONSISSUE, "Delete Tag Definition", "An error occurred while deleting Tag Definitions", new Date());
            userActivityTrackingRepository.save(userTrace);

            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE, "Some issues in delete Tag Definitions, please check with your provider!");
        }
    }





}
