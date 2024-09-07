package com.simulator.iso.services;

import com.simulator.config.GlobalVars;
import com.simulator.entities.FieldsDefinition;
import com.simulator.entities.FieldsDefinitionId;
import com.simulator.entities.UserActivityTracking;
import com.simulator.entities.UserManagement;
import com.simulator.globalModels.ResponseApiJson;
import com.simulator.repository.FieldsRepository;
import com.simulator.repository.UserActivityTrackingRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@Service
public  class FieldsService {
    private final Logger logger = LogManager.getLogger(FieldsService.class);
    @Autowired
    FieldsRepository fieldsRepository;

    @Autowired
    UserActivityTrackingRepository userActivityTrackingRepository;


    @Autowired
    GlobalVars globalVars;


  /*  public FieldsDefinition getFieldsDefinition(FieldsDefinitionId id) { return fieldsRepository.findById(id).orElse(null);
    }*/

  /*  public List<FieldsDefinition> getFilteredFieldsList(String bankCode, Character fieldProtocole) {
        return fieldsRepository.findByBankCodeAndFieldsProtocole(bankCode, fieldProtocole);
    }*/

  /*  public ResponseApiJson<List<FieldsDefinition>> getAllFieldsDefinitions() {
        String idRequest = "MS_" + new Date().getTime() + (Math.random() * 9999);
        try {
            List<FieldsDefinition> fieldsDefinitions = fieldsRepository.findAll();
            return new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS, "Get all Fields succesfully  !", fieldsDefinitions);

        } catch (Exception e) {
            logger.info("Exception of this::getAllFieldsDefinitions " + e.getMessage());
            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE, "some issues in getAllFieldsDefinitions please check with your provider !");

        }


    }*/

    public ResponseApiJson<List<FieldsDefinition>> getAllFieldsDefinitions(FieldsDefinitionId id) {
        String idRequest = "MS_" + new Date().getTime() + (Math.random() * 9999);
        Optional<UserManagement> userM = globalVars.getUser(GlobalVars.getBearerTokenHeader());
        try {
            List<FieldsDefinition> fieldsDefinitions = fieldsRepository.findByBankCodeAndFieldsProtocole(id.getBankCode(),id.getFieldProtocole());

            // Track user activity
            UserActivityTracking userTrace =new UserActivityTracking(userM.get().getUserCode(), "Retrieve", "fielssuccesfully", "000.000.00.00", "Success", GlobalVars.SUCCESS, "Retrieve fielssuccesfully", "fielssuccesfully retrieved successfully", new Date());
            userActivityTrackingRepository.save(userTrace);

                return new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS, "Get all fielssuccesfully  !", fieldsDefinitions);

        } catch (Exception e) {
            logger.info("Exception of this::getAllFieldsDefinitions " + e.getMessage());

            // Track user activity for exception
            UserActivityTracking userTrace = new UserActivityTracking(userM.get().getUserCode(), "Retrieve", "fielssuccesfully", "000.000.00.00", "Error", GlobalVars.EXCEPTIONSISSUE, "Retrieve fielssuccesfully", "some issues in getAllFieldsDefinitions please check with your provider !", new Date());
            userActivityTrackingRepository.save(userTrace);


            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE, "some issues in getAllFieldsDefinitions please check with your provider !");

        }

    }



    public ResponseApiJson<List<FieldsDefinition>> getOneFieldsDefinition(FieldsDefinitionId id) {
        String idRequest = "MSS_" + new Date().getTime() + (Math.random() * 9999);
        Optional<UserManagement> userM = globalVars.getUser(GlobalVars.getBearerTokenHeader());
        try {
            Optional<FieldsDefinition> fieldsDefinition = fieldsRepository.findById(id);
            List<FieldsDefinition> fieldsDefinitions = new ArrayList<>();
            fieldsDefinition.ifPresent(fieldsDefinitions::add);
            // Track user activity
            UserActivityTracking userTrace =new UserActivityTracking(userM.get().getUserCode(), "Retrieve", "fielssuccesfully", "000.000.00.00", "Success", GlobalVars.SUCCESS, "Retrieve fielssuccesfully", "fielssuccesfully retrieved successfully", new Date());
            userActivityTrackingRepository.save(userTrace);


            return new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS , "Get one fieldsDefinition succesfully  !" , fieldsDefinitions);
        } catch (Exception e)   {
            logger.info("Exception of  getOneFieldsDefinition " + e.getMessage());

            // Track user activity for exception
            UserActivityTracking userTrace = new UserActivityTracking(userM.get().getUserCode(), "Retrieve", "fielssuccesfully", "000.000.00.00", "Error", GlobalVars.EXCEPTIONSISSUE, "Retrieve fielssuccesfully", "some issues in getOneFieldsDefinition please check with your provider !", new Date());
            userActivityTrackingRepository.save(userTrace);

            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE , " some issues in getOneFieldsDefinition please check with your provider !");

        }
    }

    public ResponseApiJson<?> addFieldsDefinition(FieldsDefinition fieldsDefinition){
        String idRequest = "AM_"  + new  Date().getTime() + (Math.random() * 9999);
        logger.info(fieldsDefinition.toString());
        Optional<UserManagement> userM = globalVars.getUser(GlobalVars.getBearerTokenHeader());
        try {
            Optional<FieldsDefinition> fieldsDefinitiontoCheck = fieldsRepository.findById(fieldsDefinition.getId());
            if ( fieldsDefinitiontoCheck.isEmpty()) {
                fieldsRepository.save(fieldsDefinition);

                // Track user activity
                UserActivityTracking userTrace =new UserActivityTracking(userM.get().getUserCode(), "Insert", "fieldsDefinition", "000.000.00.00", "Success", GlobalVars.SUCCESS, "Insert fieldsDefinition", "fieldsDefinition inserted successfully", new Date());
                userActivityTrackingRepository.save(userTrace);


                return new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS, "Insertde fieldsDefinition sucessfully  !");
            }else {

                // Track user activity
                UserActivityTracking userTrace =new UserActivityTracking(userM.get().getUserCode(), "Insert", "fieldsDefinition", "000.000.00.00", "Failed", GlobalVars.ALREADYEXIST, "Insert fieldsDefinition", "this fieldsDefinition already exist !", new Date());
                userActivityTrackingRepository.save(userTrace);
                return  new ResponseApiJson<>(idRequest , GlobalVars.ALREADYEXIST , "this fieldsDefinition already exist !");

            }
        } catch (Exception e ){
            logger.info("Exception of getOneFieldsDefinition "  + e.getMessage());

            // Track user activity for exception
            UserActivityTracking userTrace = new UserActivityTracking(userM.get().getUserCode(), "Insert", "fieldsDefinition", "000.000.00.00", "Error", GlobalVars.EXCEPTIONSISSUE, "Insert fieldsDefinition", " Some issues in addFieldsDefinition please check with your provider !", new Date());
            userActivityTrackingRepository.save(userTrace);

            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE , "Some issues in addFieldsDefinition please check with your provider !");
        }
    }

    public  ResponseApiJson<?> updateFieldsDefinition(FieldsDefinition fieldsDefinition) throws IllegalAccessException {
        String  idRequest = "UMS_"  + new Date().getTime() + (Math.random() * 9999);
        Optional<UserManagement> userM = globalVars.getUser(GlobalVars.getBearerTokenHeader());
        try{
            Optional<FieldsDefinition> fieldsDefinitionto = fieldsRepository.findById(fieldsDefinition.getId());
            GlobalVars globalVars = new GlobalVars();
            if (fieldsDefinitionto.isPresent()){
                FieldsDefinition fieldsDefinition1 = (FieldsDefinition)  globalVars.construct(FieldsDefinition.class, fieldsDefinition , fieldsDefinitionto.get());
                logger.info("FieldsDefinitionto " + fieldsDefinition1.toString());
                fieldsRepository.save(fieldsDefinition1);

                // Track user activity
                UserActivityTracking userTrace =new UserActivityTracking(userM.get().getUserCode(), "Update", "fieldsDefinition", "000.000.00.00", "Successfully", GlobalVars.SUCCESS, "Update fieldsDefinition", "fieldsDefinition updated successfully", new Date());
                userActivityTrackingRepository.save(userTrace);
                return new ResponseApiJson<>(idRequest , GlobalVars.SUCCESS , "Update  fieldsDefinition  sucessfuly  !");

            } else {
                // Track user activity
                UserActivityTracking userTrace =new UserActivityTracking(userM.get().getUserCode(), "Update", "fieldsDefinition", "000.000.00.00", "Failed", GlobalVars.ALREADYEXIST, "Update fieldsDefinition", "this fieldsDefinition  does not exit !", new Date());
                userActivityTrackingRepository.save(userTrace);
                return new ResponseApiJson<>(idRequest , GlobalVars.NOTEXIST , "this fieldsDefinition  does not exit   !");

            }
        } catch (Exception e) {
            logger.info("Exception of getOneFieldsDefinition "  + e.getMessage());

            // Track user activity for exception
            UserActivityTracking userTrace = new UserActivityTracking(userM.get().getUserCode(), "Update", "fieldsDefinition", "000.000.00.00", "Error", GlobalVars.EXCEPTIONSISSUE, "Update fieldsDefinition", " Some issues in updateFieldsService please check with your provider !", new Date());
            userActivityTrackingRepository.save(userTrace);

            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE , "Some issues in updateFieldsService please check with your provider !");


        }
    }

    public  ResponseApiJson<?> deleteFieldsDefinition(FieldsDefinitionId id){
        String  idRequest = "DM_" + new Date().getTime() + (Math.random() * 9999);
        Optional<UserManagement> userM = globalVars.getUser(GlobalVars.getBearerTokenHeader());
        try {
            Optional<FieldsDefinition> fieldsDefinition = fieldsRepository.findById(id);
            fieldsDefinition.ifPresent(fieldsRepository ::delete);

            // Track user activity
            UserActivityTracking userTrace =new UserActivityTracking(userM.get().getUserCode(), "Delete", "fieldsDefinition", "000.000.00.00", "Successfully", GlobalVars.SUCCESS, "Delete fieldsDefinition", "fieldsDefinition deleted successfully", new Date());
            userActivityTrackingRepository.save(userTrace);

            return  new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS , "Delete mtiDef succesfully !");
        }catch (Exception e){
            logger.info("Delete mtiDef Exception: " +e.getMessage());

            // Track user activity for exception
            UserActivityTracking userTrace = new UserActivityTracking(userM.get().getUserCode(), "Delete", "fieldsDefinition", "000.000.00.00", "Error", GlobalVars.EXCEPTIONSISSUE, "Delete fieldsDefinition", " Some issues in deleteMtiDef please check with your provider !", new Date());
            userActivityTrackingRepository.save(userTrace);


            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE ,"deleteMtiDef terminated with issue");
        }
    }
}




