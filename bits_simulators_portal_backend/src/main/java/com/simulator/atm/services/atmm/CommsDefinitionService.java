package com.simulator.atm.services.atmm;

import com.simulator.config.GlobalVars;
import com.simulator.entities.CommsDefinition;
import com.simulator.entities.CommsDefinitionId;
import com.simulator.globalModels.ResponseApiJson;
import com.simulator.repository.CommsDefinitionRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CommsDefinitionService {
    private static final Logger logger = LogManager.getLogger(CommsDefinitionService.class);

    @Autowired
    private CommsDefinitionRepository commsRepository;


    public ResponseApiJson<List<CommsDefinition>> getAllCommsParams(CommsDefinitionId commsParamId) {
        String idRequest = "MS_" + new Date().getTime() + (Math.random() * 9999);
        try {
            List<CommsDefinition> commsParams = commsRepository.findByBankCodeAndCommProtocole(commsParamId.getBankCode());

            return new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS, "Get all comms succesfully  !", commsParams);

        } catch (Exception e) {
            logger.info("Exception of this::getAllCommsParams " + e.getMessage());
            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE, "some issues in getAllCommsParams please check with your provider !");

        }

    }

    public ResponseApiJson<List<CommsDefinition>> getOneCommsParam(CommsDefinitionId id) {
        String idRequest = "MSS_" + new Date().getTime() + (Math.random() * 9999);
        try {
            Optional<CommsDefinition> commsParam = commsRepository.findById(id);
            List<CommsDefinition> commsParams = new ArrayList<>();
            commsParam.ifPresent(commsParams::add);
            return new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS , "Get one commsParam succesfully  !" , commsParams);
        } catch (Exception e)   {
            logger.info("Exception of  getOneCommsParam " + e.getMessage());
            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE , " some issues in getOneCommsParam please check with your provider !");

        }
    }

    public ResponseApiJson<?> addCommsParam(CommsDefinition commsParam){
        String idRequest = "AM_"  + new  Date().getTime() + (Math.random() * 9999);
        logger.info(commsParam.toString());
        try {
            Optional<CommsDefinition> commsParamtoCheck = commsRepository.findById(commsParam.getId());
            if ( commsParamtoCheck.isEmpty()) {
                commsRepository.save(commsParam);
                return new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS, "Insertde commsParam sucessfully  !");
            }else {
                return  new ResponseApiJson<>(idRequest , GlobalVars.ALREADYEXIST , "this CommsParam already exist !");

            }
        } catch (Exception e ){
            logger.info("Exception of getOneCommsParam "  + e.getMessage());
            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE , "Some issues in addCommsParam please check with your provider !");
        }
    }

    public  ResponseApiJson<?> updateCommsParam(CommsDefinition commsParam) throws IllegalAccessException {
        String  idRequest = "UMS_"  + new Date().getTime() + (Math.random() * 9999);
        try{
            Optional<CommsDefinition> commsParamto = commsRepository.findById(commsParam.getId());
            GlobalVars globalVars = new GlobalVars();
            if (commsParamto.isPresent()){
                CommsDefinition commsParam1 = commsRepository.save(commsParam);
//                logger.info("commsParamto " + commsParam1.toString());
//                commsRepository.save(commsParam1);
                return new ResponseApiJson<>(idRequest , GlobalVars.SUCCESS , "Update  commsParam  sucessfuly  !",commsParam1);

            } else {
                return new ResponseApiJson<>(idRequest , GlobalVars.NOTEXIST , "this commsParam  does not exit   !");

            }
        } catch (Exception e) {
            logger.info("Exception of getOneCommsParam "  + e.getMessage());
            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE , "Some issues in updateCommsService please check with your provider !");


        }
    }

    public  ResponseApiJson<?> deleteCommsParam(CommsDefinitionId id){
        String  idRequest = "DM_" + new Date().getTime() + (Math.random() * 9999);
        try {
            Optional<CommsDefinition> commsParam = commsRepository.findById(id);
            commsParam.ifPresent(commsRepository ::delete);
            return  new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS , "Delete commsParam succesfully !");
        }catch (Exception e){
            logger.info("Delete commsParam Exception: " +e.getMessage());
            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE ,"deleteCommsParam terminated with issue");
        }
    }

}

