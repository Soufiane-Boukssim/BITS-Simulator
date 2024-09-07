package com.simulator.iso.services;

import com.simulator.config.GlobalVars;
import com.simulator.entities.TerminalParam;
import com.simulator.entities.TerminalParamId;
import com.simulator.entities.UserActivityTracking;
import com.simulator.entities.UserManagement;
import com.simulator.globalModels.ResponseApiJson;
import com.simulator.repository.TerminalRepository;
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
public  class TerminalService {
    private final Logger logger = LogManager.getLogger(TerminalService.class);
    @Autowired
    TerminalRepository terminalRepository;


    @Autowired
    UserActivityTrackingRepository userActivityTrackingRepository;

    @Autowired
    GlobalVars globalVars;


  /*  public TerminalParam getTerminalParam(TerminalParamId id) { return terminalRepository.findById(id).orElse(null);
    }*/

  /*  public List<TerminalParam> getFilteredTerminalList(String bankCode, Character terProtocol) {
        return terminalRepository.findByBankCodeAndTerProtocol(bankCode, terProtocol);
    }
*/



   /* public ResponseApiJson<List<TerminalParam>> getAllTerminalParams() {
        String idRequest = "MS_" + new Date().getTime() + (Math.random() * 9999);

        try {
            List<TerminalParam> TerminalParams = terminalRepository.findAll();
            return new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS, "Get all Terminal succesfully  !", TerminalParams);

        } catch (Exception e) {
            logger.info("Exception of this::getAllTerminalParams " + e.getMessage());
            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE, "some issues in getAllTerminalParams please check with your provider !");

        }


    }*/





    public ResponseApiJson<List<TerminalParam>> getAllTerminalParams(TerminalParamId terminalParamId) {
        String idRequest = "MS_" + new Date().getTime() + (Math.random() * 9999);
        Optional<UserManagement> userM = globalVars.getUser(GlobalVars.getBearerTokenHeader());
        try {
            List<TerminalParam> terminalParams = terminalRepository.findByBankCodeAndTerProtocol(terminalParamId.getBankCode(), terminalParamId.getTerProtocol());


            // Track user activity
            UserActivityTracking userTrace =new UserActivityTracking(userM.get().getUserCode(), "Retrieve", "TerminalParam", "000.000.00.00", "Success", GlobalVars.SUCCESS, "Retrieve TerminalParam", "TerminalParam retrieved successfully", new Date());
            userActivityTrackingRepository.save(userTrace);


            return new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS, "Get all TerminalParam succesfully  !", terminalParams);

        } catch (Exception e) {
            logger.info("Exception of this::getAllTerminalParam " + e.getMessage());

            // Track user activity for exception
            UserActivityTracking userTrace = new UserActivityTracking(userM.get().getUserCode(), "Retrieve", "TerminalParam", "000.000.00.00", "Error", GlobalVars.EXCEPTIONSISSUE, "Retrieve TerminalParam", "some issues in getAllTerminalParam please check with your provider !", new Date());
            userActivityTrackingRepository.save(userTrace);


            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE, "some issues in getAllTerminalParam please check with your provider !");

        }


    }


        public ResponseApiJson<List<TerminalParam>> getOneTerminalParam(TerminalParamId id) {
        String idRequest = "MSS_" + new Date().getTime() + (Math.random() * 9999);
            Optional<UserManagement> userM = globalVars.getUser(GlobalVars.getBearerTokenHeader());

            try {
            Optional<TerminalParam> terminalParam = terminalRepository.findById(id);
            List<TerminalParam> terminalParams = new ArrayList<>();
            terminalParam.ifPresent(terminalParams::add);

            // Track user activity
            UserActivityTracking userTrace =new UserActivityTracking(userM.get().getUserCode(), "Retrieve", "TerminalParam", "000.000.00.00", "Success", GlobalVars.SUCCESS, "Retrieve TerminalParam", "TerminalParam retrieved successfully", new Date());
            userActivityTrackingRepository.save(userTrace);

            return new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS , "Get one terminalParam succesfully  !" , terminalParams);
        } catch (Exception e)   {
            logger.info("Exception of  getOneTerminalParam" + e.getMessage());
                // Track user activity for exception
                UserActivityTracking userTrace = new UserActivityTracking(userM.get().getUserCode(), "Retrieve", "TerminalParam", "000.000.00.00", "Error", GlobalVars.EXCEPTIONSISSUE, "Retrieve TerminalParam", "some issues in getOneTerminalParam please check with your provider !", new Date());
                userActivityTrackingRepository.save(userTrace);

            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE , " some issues in getOneTerminalParam please check with your provider !");

        }
    }

    public ResponseApiJson<?> addTerminalParam(TerminalParam terminalParam){
        String idRequest = "AM_"  + new  Date().getTime() + (Math.random() * 9999);
        logger.info(terminalParam.toString());
        Optional<UserManagement> userM = globalVars.getUser(GlobalVars.getBearerTokenHeader());
        try {
            Optional<TerminalParam> terminalParamtoCheck = terminalRepository.findById(terminalParam.getId());
            if ( terminalParamtoCheck.isEmpty()) {
                terminalRepository.save(terminalParam);

                // Track user activity
                UserActivityTracking userTrace =new UserActivityTracking(userM.get().getUserCode(), "Insert", "terminalParam", "000.000.00.00", "Success", GlobalVars.SUCCESS, "Insert terminalParam", "terminalParam inserted successfully", new Date());
                userActivityTrackingRepository.save(userTrace);


                return new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS, "Insertde terminalParam sucessfully  !");
            }else {
                // Track user activity
                UserActivityTracking userTrace =new UserActivityTracking(userM.get().getUserCode(), "Insert", "terminalParam", "000.000.00.00", "Failed", GlobalVars.ALREADYEXIST, "Insert terminalParam", "this terminalParam already exist !", new Date());
                userActivityTrackingRepository.save(userTrace);
                return  new ResponseApiJson<>(idRequest , GlobalVars.ALREADYEXIST , "this TerminalParam already exist !");

            }
        } catch (Exception e ){
            logger.info("Exception of getOneTerminalParam "  + e.getMessage());

            // Track user activity for exception
            UserActivityTracking userTrace = new UserActivityTracking(userM.get().getUserCode(), "Insert", "TerminalParam", "000.000.00.00", "Error", GlobalVars.EXCEPTIONSISSUE, "Insert TerminalParam", " Some issues in addTerminalParam please check with your provider !", new Date());
            userActivityTrackingRepository.save(userTrace);

            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE , "Some issues in addTerminalParam please check with your provider !");
        }
    }

    public  ResponseApiJson<?> updateTerminalParam(TerminalParam terminalParam) throws IllegalAccessException {
        String  idRequest = "UMS_"  + new Date().getTime() + (Math.random() * 9999);
        Optional<UserManagement> userM = globalVars.getUser(GlobalVars.getBearerTokenHeader());
        try{
            Optional<TerminalParam> terminalParamto = terminalRepository.findById(terminalParam.getId());
            GlobalVars globalVars = new GlobalVars();
            if (terminalParamto.isPresent()){
                TerminalParam terminalParam1 = (TerminalParam)  globalVars.construct(TerminalParam.class, terminalParam , terminalParamto.get());
                logger.info("terminalParamto " + terminalParam1.toString());
                terminalRepository.save(terminalParam1);

                // Track user activity
                UserActivityTracking userTrace =new UserActivityTracking(userM.get().getUserCode(), "Update", "terminalParam", "000.000.00.00", "Successfully", GlobalVars.SUCCESS, "Update terminalParam", "terminalParam updated successfully", new Date());
                userActivityTrackingRepository.save(userTrace);


                return new ResponseApiJson<>(idRequest , GlobalVars.SUCCESS , "Update  terminalParam  sucessfuly  !");

            } else {

                // Track user activity
                UserActivityTracking userTrace =new UserActivityTracking(userM.get().getUserCode(), "Update", "terminalParam", "000.000.00.00", "Failed", GlobalVars.ALREADYEXIST, "Update terminalParam", "this terminalParam  does not exit !", new Date());
                userActivityTrackingRepository.save(userTrace);

                return new ResponseApiJson<>(idRequest , GlobalVars.NOTEXIST , "this terminalParam  does not exit   !");

            }
        } catch (Exception e) {
            logger.info("Exception of getOneTerminalParam "  + e.getMessage());
            // Track user activity for exception
            UserActivityTracking userTrace = new UserActivityTracking(userM.get().getUserCode(), "Update", "terminalParam", "000.000.00.00", "Error", GlobalVars.EXCEPTIONSISSUE, "Update terminalParam", " Some issues in updateTerminalService please check with your provider !", new Date());
            userActivityTrackingRepository.save(userTrace);

            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE , "Some issues in updateTerminalService please check with your provider !");


        }
    }

    public  ResponseApiJson<?> deleteTerminalParam(TerminalParamId id){
        String  idRequest = "DM_" + new Date().getTime() + (Math.random() * 9999);
        Optional<UserManagement> userM = globalVars.getUser(GlobalVars.getBearerTokenHeader());
        logger.info("id====>+"+id);

        try {
            Optional<TerminalParam> terminalParam = terminalRepository.findById(id);
            logger.info("terminalParam====>+"+terminalParam);
            terminalParam.ifPresent(terminalRepository ::delete);

            // Track user activity
            //UserActivityTracking userTrace =new UserActivityTracking(userM.get().getUserCode(), "Delete", "TerminalParam", "000.000.00.00", "Successfully", GlobalVars.SUCCESS, "Delete TerminalParam", "TerminalParam deleted successfully", new Date());
           // userActivityTrackingRepository.save(userTrace);

            return  new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS , "Delete TerminalParam succesfully !");
        }catch (Exception e){
            logger.info("Delete mtiDef Exception: " +e.getMessage());
            // Track user activity for exception
          //  UserActivityTracking userTrace = new UserActivityTracking(userM.get().getUserCode(), "Delete", "TerminalParam", "000.000.00.00", "Error", GlobalVars.EXCEPTIONSISSUE, "Delete TerminalParam", " Some issues in deleteTerminalParam please check with your provider !", new Date());
           // userActivityTrackingRepository.save(userTrace);
            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE ,"deleteTerminalParam terminated with issue");
        }
    }

}





