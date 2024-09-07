package com.simulator.pos.services;

import com.simulator.config.GlobalVars;
import com.simulator.entities.CommsParam;
import com.simulator.entities.pos.PosCommsParam;
import com.simulator.entities.pos.PosCommsParamId;
import com.simulator.globalModels.ResponseApiJson;
import com.simulator.repository.UserActivityTrackingRepository;
import com.simulator.repository.pos.PosCommsRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@Service
public  class PosCommsService {
    private final Logger logger = LogManager.getLogger(PosCommsService.class);
    @Autowired
    PosCommsRepository commsRepository;



    @Autowired
    UserActivityTrackingRepository userActivityTrackingRepository;

    @Autowired
    GlobalVars globalVars;



    public ResponseApiJson<List<PosCommsParam>> getAllCommsParams(PosCommsParamId commsParamId) {
        String idRequest = "MS_" + new Date().getTime() + (Math.random() * 9999);
        try {
            List<PosCommsParam> commsParams = commsRepository.findByBankCodeAndCommProtocole(commsParamId.getBankCode(), commsParamId.getCommProtocol());

            return new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS, "Get all comms succesfully  !", commsParams);

        } catch (Exception e) {
            logger.info("Exception of this::getAllCommsParams " + e.getMessage());
            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE, "some issues in getAllCommsParams please check with your provider !");

        }

    }

    public ResponseApiJson<List<PosCommsParam>> getOneCommsParam(PosCommsParamId id) {
        String idRequest = "MSS_" + new Date().getTime() + (Math.random() * 9999);
        try {
            Optional<PosCommsParam> commsParam = commsRepository.findById(id);
            List<PosCommsParam> commsParams = new ArrayList<>();
            commsParam.ifPresent(commsParams::add);
            return new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS , "Get one commsParam succesfully  !" , commsParams);
        } catch (Exception e)   {
            logger.info("Exception of  getOneCommsParam " + e.getMessage());
            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE , " some issues in getOneCommsParam please check with your provider !");

        }
    }

    public ResponseApiJson<?> addCommsParam(PosCommsParam commsParam){
        String idRequest = "AM_"  + new  Date().getTime() + (Math.random() * 9999);
        logger.info(commsParam.toString());
        try {
            Optional<PosCommsParam> commsParamtoCheck = commsRepository.findById(commsParam.getId());
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

    public  ResponseApiJson<?> updateCommsParam(PosCommsParam commsParam) throws IllegalAccessException {
        String  idRequest = "UMS_"  + new Date().getTime() + (Math.random() * 9999);
        try{
            Optional<PosCommsParam> commsParamto = commsRepository.findById(commsParam.getId());
            GlobalVars globalVars = new GlobalVars();
            if (commsParamto.isPresent()){
                PosCommsParam commsParam1 = (PosCommsParam)  globalVars.construct(CommsParam.class, commsParam , commsParamto.get());
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

    public  ResponseApiJson<?> deleteCommsParam(PosCommsParamId id){
        String  idRequest = "DM_" + new Date().getTime() + (Math.random() * 9999);
        try {
            Optional<PosCommsParam> commsParam = commsRepository.findById(id);
            commsParam.ifPresent(commsRepository ::delete);
            return  new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS , "Delete commsParam succesfully !");
        }catch (Exception e){
            logger.info("Delete commsParam Exception: " +e.getMessage());
            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE ,"deleteCommsParam terminated with issue");
        }
    }

}





