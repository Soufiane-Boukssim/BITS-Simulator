package com.simulator.pos.services;

import com.simulator.config.GlobalVars;
import com.simulator.entities.pos.PosFieldsDefinition;
import com.simulator.entities.pos.PosFieldsDefinitionId;
import com.simulator.globalModels.ResponseApiJson;
import com.simulator.repository.pos.PosFieldsRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@Service
public  class PosFieldsService {
    private final Logger logger = LogManager.getLogger(PosFieldsService.class);
    @Autowired
    PosFieldsRepository fieldsRepository;




  /*  public PosFieldsDefinition getFieldsDefinition(PosFieldsDefinitionId id) { return fieldsRepository.findById(id).orElse(null);
    }*/

  /*  public List<PosFieldsDefinition> getFilteredFieldsList(String bankCode, Character fieldProtocole) {
        return fieldsRepository.findByBankCodeAndFieldsProtocole(bankCode, fieldProtocole);
    }*/

  /*  public ResponseApiJson<List<PosFieldsDefinition>> getAllFieldsDefinitions() {
        String idRequest = "MS_" + new Date().getTime() + (Math.random() * 9999);
        try {
            List<PosFieldsDefinition> fieldsDefinitions = fieldsRepository.findAll();
            return new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS, "Get all Fields succesfully  !", fieldsDefinitions);

        } catch (Exception e) {
            logger.info("Exception of this::getAllFieldsDefinitions " + e.getMessage());
            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE, "some issues in getAllFieldsDefinitions please check with your provider !");

        }


    }*/

    public ResponseApiJson<List<PosFieldsDefinition>> getAllFieldsDefinitions() {
        String idRequest = "MS_" + new Date().getTime() + (Math.random() * 9999);
        try {
            List<PosFieldsDefinition> fieldsDefinitions = fieldsRepository.findAll();

                return new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS, "Get all fielssuccesfully  !", fieldsDefinitions);

        } catch (Exception e) {
            logger.info("Exception of this::getAllFieldsDefinitions " + e.getMessage());
            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE, "some issues in getAllFieldsDefinitions please check with your provider !");

        }

    }



    public ResponseApiJson<List<PosFieldsDefinition>> getOneFieldsDefinition(PosFieldsDefinitionId id) {
        String idRequest = "MSS_" + new Date().getTime() + (Math.random() * 9999);
        try {
            Optional<PosFieldsDefinition> fieldsDefinition = fieldsRepository.findById(id);
            List<PosFieldsDefinition> fieldsDefinitions = new ArrayList<>();
            fieldsDefinition.ifPresent(fieldsDefinitions::add);
            return new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS , "Get one fieldsDefinition succesfully  !" , fieldsDefinitions);
        } catch (Exception e)   {
            logger.info("Exception of  getOneFieldsDefinition " + e.getMessage());
            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE , " some issues in getOneFieldsDefinition please check with your provider !");

        }
    }

    public ResponseApiJson<?> addFieldsDefinition(PosFieldsDefinition fieldsDefinition){
        String idRequest = "AM_"  + new  Date().getTime() + (Math.random() * 9999);
        logger.info(fieldsDefinition.toString());
        try {
            Optional<PosFieldsDefinition> fieldsDefinitiontoCheck = fieldsRepository.findById(fieldsDefinition.getId());
            if ( fieldsDefinitiontoCheck.isEmpty()) {
                fieldsRepository.save(fieldsDefinition);
                return new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS, "Insertde fieldsDefinition sucessfully  !");
            }else {
                return  new ResponseApiJson<>(idRequest , GlobalVars.ALREADYEXIST , "this fieldsDefinition already exist !");

            }
        } catch (Exception e ){
            logger.info("Exception of getOneFieldsDefinition "  + e.getMessage());
            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE , "Some issues in addFieldsDefinition please check with your provider !");
        }
    }

    public  ResponseApiJson<?> updateFieldsDefinition(PosFieldsDefinition fieldsDefinition) throws IllegalAccessException {
        String  idRequest = "UMS_"  + new Date().getTime() + (Math.random() * 9999);
        try{
            Optional<PosFieldsDefinition> fieldsDefinitionto = fieldsRepository.findById(fieldsDefinition.getId());
            GlobalVars globalVars = new GlobalVars();
            if (fieldsDefinitionto.isPresent()){
                PosFieldsDefinition fieldsDefinition1 = (PosFieldsDefinition)  globalVars.construct(PosFieldsDefinition.class, fieldsDefinition , fieldsDefinitionto.get());
                logger.info("FieldsDefinitionto " + fieldsDefinition1.toString());
                fieldsRepository.save(fieldsDefinition1);
                return new ResponseApiJson<>(idRequest , GlobalVars.SUCCESS , "Update  fieldsDefinition  sucessfuly  !");

            } else {
                return new ResponseApiJson<>(idRequest , GlobalVars.NOTEXIST , "this fieldsDefinition  does not exit   !");

            }
        } catch (Exception e) {
            logger.info("Exception of getOneFieldsDefinition "  + e.getMessage());
            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE , "Some issues in updateFieldsService please check with your provider !");


        }
    }

    public  ResponseApiJson<?> deleteFieldsDefinition(PosFieldsDefinitionId id){
        String  idRequest = "DM_" + new Date().getTime() + (Math.random() * 9999);
        try {
            Optional<PosFieldsDefinition> fieldsDefinition = fieldsRepository.findById(id);
            fieldsDefinition.ifPresent(fieldsRepository ::delete);
            return  new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS , "Delete mtiDef succesfully !");
        }catch (Exception e){
            logger.info("Delete mtiDef Exception: " +e.getMessage());
            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE ,"deleteMtiDef terminated with issue");
        }
    }
}




