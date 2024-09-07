package com.simulator.iso.services;

import com.simulator.config.GlobalVars;
import com.simulator.entities.ResponseDef;
import com.simulator.entities.ResponseDefId;
import com.simulator.entities.UserActivityTracking;
import com.simulator.entities.UserManagement;
import com.simulator.globalModels.ResponseApiJson;
import com.simulator.repository.ResponseRepository;
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
public  class ResponseService {
    private final Logger logger = LogManager.getLogger(ResponseService.class);
    @Autowired
    ResponseRepository responseRepository;

    @Autowired
    UserActivityTrackingRepository userActivityTrackingRepository;

    @Autowired
    GlobalVars globalVars;

  /*  public PosResponseDef getResponseDef(ResponseDefId id) { return responseRepository.findById(id).orElse(null);
    }
*/

   /* public List<PosResponseDef> getFilteredResponseList(String bankCode, Character respProtocol) {
        return responseRepository.findByBankCodeAndRespProtocol(bankCode, respProtocol);
    }
*/


   /* public ResponseApiJson<List<PosResponseDef>> getAllResponseDefs() {
        String idRequest = "MS_" + new Date().getTime() + (Math.random() * 9999);

        try {
            List<PosResponseDef> responseDefs = responseRepository.findAll();
            return new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS, "Get all response succesfully  !", responseDefs);

        } catch (Exception e) {
            logger.info("Exception of this::getAllResponseDefs " + e.getMessage());
            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE, "some issues in getAllResponseDefs please check with your provider !");

        }


    }*/


    public ResponseApiJson<List<ResponseDef>> getAllResponseDefs(ResponseDefId responseDefId) {
        String idRequest = "MS_" + new Date().getTime() + (Math.random() * 9999);
        Optional<UserManagement> userM = globalVars.getUser(GlobalVars.getBearerTokenHeader());
        try {
            List<ResponseDef> responseDefs = responseRepository.findById_BankCodeAndId_RespProtocol(responseDefId.getBankCode(), responseDefId.getRespProtocol());


            // Track user activity
            UserActivityTracking userTrace =new UserActivityTracking(userM.get().getUserCode(), "Retrieve", "ResponseDefs", "000.000.00.00", "Success", GlobalVars.SUCCESS, "Retrieve ResponseDefs", "ResponseDefs retrieved successfully", new Date());
            userActivityTrackingRepository.save(userTrace);

            return new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS, "Get all getAllResponseDefs  !", responseDefs);

        } catch (Exception e) {
            logger.info("Exception of this::getAllAccountDefs " + e.getMessage());
            // Track user activity for exception
            UserActivityTracking userTrace = new UserActivityTracking(userM.get().getUserCode(), "Retrieve", "ResponseDefs", "000.000.00.00", "Error", GlobalVars.EXCEPTIONSISSUE, "Retrieve ResponseDefs", "An error occurred while retrieving ResponseDefs", new Date());
            userActivityTrackingRepository.save(userTrace);


            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE, "some issues in getAllResponseDefs please check with your provider !");

        }

    }




        public ResponseApiJson<List<ResponseDef>> getOneResponseDef(ResponseDefId id) {
        String idRequest = "MSS_" + new Date().getTime() + (Math.random() * 9999);
            Optional<UserManagement> userM = globalVars.getUser(GlobalVars.getBearerTokenHeader());
        try {
            Optional<ResponseDef> responseDef = responseRepository.findById(id);
            List<ResponseDef> responseDefs = new ArrayList<>();
            responseDef.ifPresent(responseDefs::add);

            // Track user activity
            UserActivityTracking userTrace =new UserActivityTracking(userM.get().getUserCode(), "Retrieve", "ResponseDef", "000.000.00.00", "Success", GlobalVars.SUCCESS, "Retrieve ResponseDef", "ResponseDef retrieved successfully", new Date());
            userActivityTrackingRepository.save(userTrace);

            return new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS , "Get one responseDef succesfully  !" , responseDefs);
        } catch (Exception e)   {
            logger.info("Exception of  getOneResponseDef " + e.getMessage());

            // Track user activity for exception
            UserActivityTracking userTrace = new UserActivityTracking(userM.get().getUserCode(), "Retrieve", "ResponseDef", "000.000.00.00", "Error", GlobalVars.EXCEPTIONSISSUE, "Retrieve ResponseDef", "An error occurred while retrieving ResponseDef", new Date());
            userActivityTrackingRepository.save(userTrace);

            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE , " some issues in getOneResponseDef please check with your provider !");

        }
    }

    public ResponseApiJson<?> addResponseDef(ResponseDef responseDef){
        String idRequest = "AM_"  + new  Date().getTime() + (Math.random() * 9999);
        logger.info(responseDef.toString());
        Optional<UserManagement> userM = globalVars.getUser(GlobalVars.getBearerTokenHeader());
        try {
            Optional<ResponseDef> responseDeftoCheck = responseRepository.findById(responseDef.getId());
            if ( responseDeftoCheck.isEmpty()) {
                responseRepository.save(responseDef);

                // Track user activity
                UserActivityTracking userTrace =new UserActivityTracking(userM.get().getUserCode(), "Insert", "ResponseDef", "000.000.00.00", "Success", GlobalVars.SUCCESS, "Insert ResponseDef", "ResponseDef inserted successfully", new Date());
                userActivityTrackingRepository.save(userTrace);

                return new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS, "Insertde responseDef sucessfully  !");
            }else {
                // Track user activity
                UserActivityTracking userTrace =new UserActivityTracking(userM.get().getUserCode(), "Insert", "ResponseDef", "000.000.00.00", "Failed", GlobalVars.ALREADYEXIST, "Insert ResponseDef", "ResponseDef inserted failed", new Date());
                userActivityTrackingRepository.save(userTrace);

                return  new ResponseApiJson<>(idRequest , GlobalVars.ALREADYEXIST , "this PosResponseDef already exist !");

            }
        } catch (Exception e ){
            logger.info("Exception of getOneResponseDef "  + e.getMessage());

            // Track user activity for exception
            UserActivityTracking userTrace = new UserActivityTracking(userM.get().getUserCode(), "Insert", "ResponseDef", "000.000.00.00", "Error", GlobalVars.EXCEPTIONSISSUE, "Insert ResponseDef", "Some issues in addResponseDef please check with your provider !", new Date());
            userActivityTrackingRepository.save(userTrace);

            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE , "Some issues in addResponseDef please check with your provider !");
        }
    }

    public  ResponseApiJson<?> updateResponseDef(ResponseDef responseDef) throws IllegalAccessException {
        String  idRequest = "UMS_"  + new Date().getTime() + (Math.random() * 9999);
        Optional<UserManagement> userM = globalVars.getUser(GlobalVars.getBearerTokenHeader());
        try{
            Optional<ResponseDef> responseDefto = responseRepository.findById(responseDef.getId());
            GlobalVars globalVars = new GlobalVars();
            if (responseDefto.isPresent()){
                ResponseDef responseDef1 = (ResponseDef)  globalVars.construct(ResponseDef.class, responseDef , responseDefto.get());
                logger.info("responseDefto " + responseDef1.toString());
                responseRepository.save(responseDef1);
                // Track user activity
                UserActivityTracking userTrace =new UserActivityTracking(userM.get().getUserCode(), "Update", "ResponseDef", "000.000.00.00", "Success", GlobalVars.SUCCESS, "Update ResponseDef", "ResponseDef updated successfully", new Date());
                userActivityTrackingRepository.save(userTrace);

                return new ResponseApiJson<>(idRequest , GlobalVars.SUCCESS , "Update  responseDef  sucessfuly  !");

            } else {

                // Track user activity
                UserActivityTracking userTrace =new UserActivityTracking(userM.get().getUserCode(), "Update", "ResponseDef", "000.000.00.00", "Failed", GlobalVars.NOTEXIST, "Update ResponseDef", "ResponseDef updated failed", new Date());
                userActivityTrackingRepository.save(userTrace);

                return new ResponseApiJson<>(idRequest , GlobalVars.NOTEXIST , "this responseDef  does not exit   !");

            }
        } catch (Exception e) {
            logger.info("Exception of getOneResponseDef "  + e.getMessage());

            // Track user activity for exception
            UserActivityTracking userTrace = new UserActivityTracking(userM.get().getUserCode(), "Update", "ResponseDef", "000.000.00.00", "Error", GlobalVars.EXCEPTIONSISSUE, "Update ResponseDef", "Some issues in updateResponseService please check with your provider !", new Date());
            userActivityTrackingRepository.save(userTrace);


            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE , "Some issues in updateResponseService please check with your provider !");


        }
    }

    public  ResponseApiJson<?> deleteResponseDef(ResponseDefId id){
        String  idRequest = "DM_" + new Date().getTime() + (Math.random() * 9999);
        Optional<UserManagement> userM = globalVars.getUser(GlobalVars.getBearerTokenHeader());

        try {
            Optional<ResponseDef> responseDef = responseRepository.findById(id);
            responseDef.ifPresent(responseRepository ::delete);

            // Track user activity
            UserActivityTracking userTrace =new UserActivityTracking(userM.get().getUserCode(), "Delete", "ResponseDef", "000.000.00.00", "Success", GlobalVars.SUCCESS, "Delete ResponseDef", "ResponseDef deleted successfully", new Date());
            userActivityTrackingRepository.save(userTrace);

            return  new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS , "Delete responseDef succesfully !");
        }catch (Exception e){
            logger.info("Delete responseDef Exception: " +e.getMessage());

            // Track user activity for exception
            UserActivityTracking userTrace = new UserActivityTracking(userM.get().getUserCode(), "Delete", "ResponseDef", "000.000.00.00", "Error", GlobalVars.EXCEPTIONSISSUE, "Delete ResponseDef", "Some issues in deleteResponseDef please check with your provider !", new Date());
            userActivityTrackingRepository.save(userTrace);

            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE ,"deleteResponseDef terminated with issue");
        }
    }

}





