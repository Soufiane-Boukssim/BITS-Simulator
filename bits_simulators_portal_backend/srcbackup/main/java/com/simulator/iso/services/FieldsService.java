package com.simulator.iso.services;

import com.simulator.config.GlobalVars;
import com.simulator.entities.FieldsDefinition;
import com.simulator.entities.FieldsDefinitionId;
import com.simulator.globalModels.ResponseApiJson;
import com.simulator.repository.FieldsRepository;
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




    public FieldsDefinition getFieldsDefinition(FieldsDefinitionId id) { return fieldsRepository.findById(id).orElse(null);
    }

    public List<FieldsDefinition> getFilteredFieldsList(String bankCode, Character fieldProtocole) {
        return fieldsRepository.findByBankCodeAndFieldsProtocole(bankCode, fieldProtocole);
    }

    public ResponseApiJson<List<FieldsDefinition>> getAllFieldsDefinitions() {
        String idRequest = "MS_" + new Date().getTime() + (Math.random() * 9999);
        try {
            List<FieldsDefinition> fieldsDefinitions = fieldsRepository.findAll();
            return new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS, "Get all Fields succesfully  !", fieldsDefinitions);

        } catch (Exception e) {
            logger.info("Exception of this::getAllFieldsDefinitions " + e.getMessage());
            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE, "some issues in getAllFieldsDefinitions please check with your provider !");

        }


    }

    public ResponseApiJson<List<FieldsDefinition>> getOneFieldsDefinition(FieldsDefinitionId id) {
        String idRequest = "MSS_" + new Date().getTime() + (Math.random() * 9999);
        try {
            Optional<FieldsDefinition> fieldsDefinition = fieldsRepository.findById(id);
            List<FieldsDefinition> fieldsDefinitions = new ArrayList<>();
            fieldsDefinition.ifPresent(fieldsDefinitions::add);
            return new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS , "Get one fieldsDefinition succesfully  !" , fieldsDefinitions);
        } catch (Exception e)   {
            logger.info("Exception of  getOneFieldsDefinition " + e.getMessage());
            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE , " some issues in getOneFieldsDefinition please check with your provider !");

        }
    }

    public ResponseApiJson<?> addFieldsDefinition(FieldsDefinition fieldsDefinition){
        String idRequest = "AM_"  + new  Date().getTime() + (Math.random() * 9999);
        logger.info(fieldsDefinition.toString());
        try {
            Optional<FieldsDefinition> fieldsDefinitiontoCheck = fieldsRepository.findById(fieldsDefinition.getId());
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

    public  ResponseApiJson<?> updateFieldsDefinition(FieldsDefinition fieldsDefinition) throws IllegalAccessException {
        String  idRequest = "UMS_"  + new Date().getTime() + (Math.random() * 9999);
        try{
            Optional<FieldsDefinition> fieldsDefinitionto = fieldsRepository.findById(fieldsDefinition.getId());
            GlobalVars globalVars = new GlobalVars();
            if (fieldsDefinitionto.isPresent()){
                FieldsDefinition fieldsDefinition1 = (FieldsDefinition)  globalVars.construct(FieldsDefinition.class, fieldsDefinition , fieldsDefinitionto.get());
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

    public  ResponseApiJson<?> deleteFieldsDefinition(FieldsDefinitionId id){
        String  idRequest = "DM_" + new Date().getTime() + (Math.random() * 9999);
        try {
            Optional<FieldsDefinition> fieldsDefinition = fieldsRepository.findById(id);
            fieldsDefinition.ifPresent(fieldsRepository ::delete);
            return  new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS , "Delete mtiDef succesfully !");
        }catch (Exception e){
            logger.info("Delete mtiDef Exception: " +e.getMessage());
            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE ,"deleteMtiDef terminated with issue");
        }
    }
}




