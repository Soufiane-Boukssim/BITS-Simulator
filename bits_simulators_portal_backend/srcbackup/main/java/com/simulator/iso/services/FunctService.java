package com.simulator.iso.services;

import com.simulator.config.GlobalVars;
import com.simulator.entities.FunctDef;
import com.simulator.entities.FunctDefId;
import com.simulator.globalModels.ResponseApiJson;
import com.simulator.repository.FunctRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@Service
public  class FunctService {
    private final Logger logger = LogManager.getLogger(FunctService.class);
    @Autowired
    FunctRepository functRepository;


    public FunctDef getFunctDef(FunctDefId id) {
        return functRepository.findById(id).orElse(null);
    }



    public List<FunctDef> getFilteredFunctList(String bankCode, Character fctProtocol) {
        return functRepository.findByBankCodeAndFctProtocole(bankCode, fctProtocol);
    }

    public ResponseApiJson<List<FunctDef>> getAllFunctDefs() {
        String idRequest = "MS_" + new Date().getTime() + (Math.random() * 9999);
        try {
            List<FunctDef> functDefs = functRepository.findAll();
            return new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS, "Get all Funct succesfully  !", functDefs);

        } catch (Exception e) {
            logger.info("Exception of this::getAllFunctDefs " + e.getMessage());
            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE, "some issues in getAllFunctDefs please check with your provider !");

        }


    }

    public ResponseApiJson<List<FunctDef>> getOneFunctDef(FunctDefId id) {
        String idRequest = "MSS_" + new Date().getTime() + (Math.random() * 9999);
        try {
            Optional<FunctDef> functDef = functRepository.findById(id);
            List<FunctDef> functDefs = new ArrayList<>();
            functDef.ifPresent(functDefs::add);
            return new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS , "Get one functDef succesfully  !" , functDefs);
        } catch (Exception e)   {
            logger.info("Exception of  getOneFunctDef " + e.getMessage());
            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE , " some issues in getOneFunctDef please check with your provider !");

        }
    }

    public ResponseApiJson<?> addFunctDef(FunctDef functDef){
        String idRequest = "AM_"  + new  Date().getTime() + (Math.random() * 9999);
        logger.info(functDef.toString());
        try {
            Optional<FunctDef> functDeftoCheck = functRepository.findById(functDef.getId());
            if ( functDeftoCheck.isEmpty()) {
                functRepository.save(functDef);
                return new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS, "Insertde functDef sucessfully  !");
            }else {
                return  new ResponseApiJson<>(idRequest , GlobalVars.ALREADYEXIST , "this FunctDef already exist !");

            }
        } catch (Exception e ){
            logger.info("Exception of getOneFunctDef "  + e.getMessage());
            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE , "Some issues in addFunctDef please check with your provider !");
        }
    }

    public  ResponseApiJson<?> updateFunctDef(FunctDef functDef) throws IllegalAccessException {
        String  idRequest = "UMS_"  + new Date().getTime() + (Math.random() * 9999);
        try{
            Optional<FunctDef> functDefto = functRepository.findById(functDef.getId());
            GlobalVars globalVars = new GlobalVars();
            if (functDefto.isPresent()){
                FunctDef functDef1 = (FunctDef)  globalVars.construct(FunctDef.class, functDef , functDefto.get());
                logger.info("functDefto " + functDef1.toString());
                functRepository.save(functDef1);
                return new ResponseApiJson<>(idRequest , GlobalVars.SUCCESS , "Update  functDef  sucessfuly  !");

            } else {
                return new ResponseApiJson<>(idRequest , GlobalVars.NOTEXIST , "this functDef  does not exit   !");

            }
        } catch (Exception e) {
            logger.info("Exception of getOneFunctDef "  + e.getMessage());
            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE , "Some issues in updateFunctService please check with your provider !");


        }
    }

    public  ResponseApiJson<?> deleteFunctDef(FunctDefId id){
        String  idRequest = "DM_" + new Date().getTime() + (Math.random() * 9999);
        try {
            Optional<FunctDef> functDef = functRepository.findById(id);
            functDef.ifPresent(functRepository ::delete);
            return  new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS , "Delete functDef succesfully !");
        }catch (Exception e){
            logger.info("Delete functDef Exception: " +e.getMessage());
            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE ,"deleteFunctDef terminated with issue");
        }
    }
}




