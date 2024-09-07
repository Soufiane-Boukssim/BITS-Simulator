package com.simulator.pos.services;

import com.simulator.config.GlobalVars;
import com.simulator.entities.TerminalParam;
import com.simulator.entities.UserActivityTracking;
import com.simulator.entities.UserManagement;
import com.simulator.entities.pos.PosTerminalParam;
import com.simulator.entities.pos.PosTerminalParamId;
import com.simulator.globalModels.ResponseApiJson;
import com.simulator.repository.UserActivityTrackingRepository;
import com.simulator.repository.pos.PosTerminalRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@Service
public  class PosTerminalService {
    private final Logger logger = LogManager.getLogger(PosTerminalService.class);
    @Autowired
    PosTerminalRepository terminalRepository;


    @Autowired
    UserActivityTrackingRepository userActivityTrackingRepository;

    @Autowired
    GlobalVars globalVars;



    public ResponseApiJson<List<PosTerminalParam>> getAllTerminalParams(PosTerminalParamId terminalParamId) {
        String idRequest = "MS_" + new Date().getTime() + (Math.random() * 9999);
        Optional<UserManagement> userM = globalVars.getUser(GlobalVars.getBearerTokenHeader());
        try {
            List<PosTerminalParam> terminalParams = terminalRepository.findByBankCodeAndTerProtocol(terminalParamId.getBankCode(), terminalParamId.getTerProtocol());


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


    public ResponseApiJson<List<PosTerminalParam>> getOneTerminalParam(PosTerminalParamId id) {
        String idRequest = "MSS_" + new Date().getTime() + (Math.random() * 9999);
        Optional<UserManagement> userM = globalVars.getUser(GlobalVars.getBearerTokenHeader());

        try {
            Optional<PosTerminalParam> terminalParam = terminalRepository.findById(id);
            List<PosTerminalParam> terminalParams = new ArrayList<>();
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

    public ResponseApiJson<?> addTerminalParam(PosTerminalParam terminalParam){
        String idRequest = "AM_"  + new  Date().getTime() + (Math.random() * 9999);
        logger.info(terminalParam.toString());
        Optional<UserManagement> userM = globalVars.getUser(GlobalVars.getBearerTokenHeader());
        try {
            Optional<PosTerminalParam> terminalParamtoCheck = terminalRepository.findById(terminalParam.getId());
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

    public  ResponseApiJson<?> updateTerminalParam(PosTerminalParam terminalParam) throws IllegalAccessException {
        String  idRequest = "UMS_"  + new Date().getTime() + (Math.random() * 9999);
        Optional<UserManagement> userM = globalVars.getUser(GlobalVars.getBearerTokenHeader());
        try{
            Optional<PosTerminalParam> terminalParamto = terminalRepository.findById(terminalParam.getId());
            GlobalVars globalVars = new GlobalVars();
            if (terminalParamto.isPresent()){
                PosTerminalParam terminalParam1 = (PosTerminalParam)  globalVars.construct(TerminalParam.class, terminalParam , terminalParamto.get());
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

    public  ResponseApiJson<?> deleteTerminalParam(PosTerminalParamId id){
        String  idRequest = "DM_" + new Date().getTime() + (Math.random() * 9999);
        Optional<UserManagement> userM = globalVars.getUser(GlobalVars.getBearerTokenHeader());
        logger.info("id====>+"+id);

        try {
            Optional<PosTerminalParam> terminalParam = terminalRepository.findById(id);
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





