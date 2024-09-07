package com.simulator.iso.services;

import com.simulator.config.GlobalVars;
import com.simulator.entities.CommsParam;
import com.simulator.entities.CommsParamId;
import com.simulator.globalModels.ResponseApiJson;
import com.simulator.repository.CommsRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@Service
public  class CommsService {
    private final Logger logger = LogManager.getLogger(CommsService.class);
    @Autowired
    CommsRepository commsRepository;




    public ResponseApiJson<List<CommsParam>> getAllCommsParams(CommsParamId commsParamId) {
        String idRequest = "MS_" + new Date().getTime() + (Math.random() * 9999);
        try {
            List<CommsParam> commsParams = commsRepository.findByBankCodeAndCommProtocole(commsParamId.getBankCode(), commsParamId.getCommProtocol());

                return new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS, "Get all comms succesfully  !", commsParams);

        } catch (Exception e) {
            logger.info("Exception of this::getAllCommsParams " + e.getMessage());
            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE, "some issues in getAllCommsParams please check with your provider !");

        }

    }

    public ResponseApiJson<List<CommsParam>> getOneCommsParam(CommsParamId id) {
        String idRequest = "MSS_" + new Date().getTime() + (Math.random() * 9999);
        try {
            Optional<CommsParam> commsParam = commsRepository.findById(id);
            List<CommsParam> commsParams = new ArrayList<>();
            commsParam.ifPresent(commsParams::add);
            return new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS , "Get one commsParam succesfully  !" , commsParams);
        } catch (Exception e)   {
            logger.info("Exception of  getOneCommsParam " + e.getMessage());
            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE , " some issues in getOneCommsParam please check with your provider !");

        }
    }

    public ResponseApiJson<?> addCommsParam(CommsParam commsParam){
        String idRequest = "AM_"  + new  Date().getTime() + (Math.random() * 9999);
        logger.info(commsParam.toString());
        try {
            Optional<CommsParam> commsParamtoCheck = commsRepository.findById(commsParam.getId());
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

    public  ResponseApiJson<?> updateCommsParam(CommsParam commsParam) throws IllegalAccessException {
        String  idRequest = "UMS_"  + new Date().getTime() + (Math.random() * 9999);
        try{
            Optional<CommsParam> commsParamto = commsRepository.findById(commsParam.getId());
            GlobalVars globalVars = new GlobalVars();
            if (commsParamto.isPresent()){
                CommsParam commsParam1 = (CommsParam)  globalVars.construct(CommsParam.class, commsParam , commsParamto.get());
                logger.info("commsParamto " + commsParam1.toString());
                commsRepository.save(commsParam1);
                return new ResponseApiJson<>(idRequest , GlobalVars.SUCCESS , "Update  commsParam  sucessfuly  !");

            } else {
                return new ResponseApiJson<>(idRequest , GlobalVars.NOTEXIST , "this commsParam  does not exit   !");

            }
        } catch (Exception e) {
            logger.info("Exception of getOneCommsParam "  + e.getMessage());
            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE , "Some issues in updateCommsService please check with your provider !");


        }
    }

    public  ResponseApiJson<?> deleteCommsParam(CommsParamId id){
        String  idRequest = "DM_" + new Date().getTime() + (Math.random() * 9999);
        try {
            Optional<CommsParam> commsParam = commsRepository.findById(id);
            commsParam.ifPresent(commsRepository ::delete);
            return  new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS , "Delete commsParam succesfully !");
        }catch (Exception e){
            logger.info("Delete commsParam Exception: " +e.getMessage());
            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE ,"deleteCommsParam terminated with issue");
        }
    }

}





