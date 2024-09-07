package com.simulator.iso.services;

import com.simulator.config.GlobalVars;
import com.simulator.entities.ResponseDef;
import com.simulator.entities.ResponseDefId;
import com.simulator.globalModels.ResponseApiJson;
import com.simulator.repository.ResponseRepository;
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



    public ResponseDef getResponseDef(ResponseDefId id) { return responseRepository.findById(id).orElse(null);
    }


    public List<ResponseDef> getFilteredResponseList(String bankCode, Character respProtocol) {
        return responseRepository.findByBankCodeAndRespProtocol(bankCode, respProtocol);
    }



    public ResponseApiJson<List<ResponseDef>> getAllResponseDefs() {
        String idRequest = "MS_" + new Date().getTime() + (Math.random() * 9999);

        try {
            List<ResponseDef> responseDefs = responseRepository.findAll();
            return new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS, "Get all response succesfully  !", responseDefs);

        } catch (Exception e) {
            logger.info("Exception of this::getAllResponseDefs " + e.getMessage());
            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE, "some issues in getAllResponseDefs please check with your provider !");

        }


    }

    public ResponseApiJson<List<ResponseDef>> getOneResponseDef(ResponseDefId id) {
        String idRequest = "MSS_" + new Date().getTime() + (Math.random() * 9999);
        try {
            Optional<ResponseDef> responseDef = responseRepository.findById(id);
            List<ResponseDef> responseDefs = new ArrayList<>();
            responseDef.ifPresent(responseDefs::add);
            return new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS , "Get one responseDef succesfully  !" , responseDefs);
        } catch (Exception e)   {
            logger.info("Exception of  getOneResponseDef " + e.getMessage());
            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE , " some issues in getOneResponseDef please check with your provider !");

        }
    }

    public ResponseApiJson<?> addResponseDef(ResponseDef responseDef){
        String idRequest = "AM_"  + new  Date().getTime() + (Math.random() * 9999);
        logger.info(responseDef.toString());
        try {
            Optional<ResponseDef> responseDeftoCheck = responseRepository.findById(responseDef.getId());
            if ( responseDeftoCheck.isEmpty()) {
                responseRepository.save(responseDef);
                return new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS, "Insertde responseDef sucessfully  !");
            }else {
                return  new ResponseApiJson<>(idRequest , GlobalVars.ALREADYEXIST , "this PosResponseDef already exist !");

            }
        } catch (Exception e ){
            logger.info("Exception of getOneResponseDef "  + e.getMessage());
            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE , "Some issues in addResponseDef please check with your provider !");
        }
    }

    public  ResponseApiJson<?> updateResponseDef(ResponseDef responseDef) throws IllegalAccessException {
        String  idRequest = "UMS_"  + new Date().getTime() + (Math.random() * 9999);
        try{
            Optional<ResponseDef> responseDefto = responseRepository.findById(responseDef.getId());
            GlobalVars globalVars = new GlobalVars();
            if (responseDefto.isPresent()){
                ResponseDef responseDef1 = (ResponseDef)  globalVars.construct(ResponseDef.class, responseDef , responseDefto.get());
                logger.info("responseDefto " + responseDef1.toString());
                responseRepository.save(responseDef1);
                return new ResponseApiJson<>(idRequest , GlobalVars.SUCCESS , "Update  responseDef  sucessfuly  !");

            } else {
                return new ResponseApiJson<>(idRequest , GlobalVars.NOTEXIST , "this responseDef  does not exit   !");

            }
        } catch (Exception e) {
            logger.info("Exception of getOneResponseDef "  + e.getMessage());
            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE , "Some issues in updateResponseService please check with your provider !");


        }
    }

    public  ResponseApiJson<?> deleteResponseDef(ResponseDefId id){
        String  idRequest = "DM_" + new Date().getTime() + (Math.random() * 9999);
        try {
            Optional<ResponseDef> responseDef = responseRepository.findById(id);
            responseDef.ifPresent(responseRepository ::delete);
            return  new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS , "Delete responseDef succesfully !");
        }catch (Exception e){
            logger.info("Delete responseDef Exception: " +e.getMessage());
            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE ,"deleteResponseDef terminated with issue");
        }
    }

}





