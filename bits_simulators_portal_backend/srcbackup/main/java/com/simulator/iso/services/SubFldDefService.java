package com.simulator.iso.services;

import com.simulator.config.GlobalVars;
import com.simulator.entities.SubfldDefinition;
import com.simulator.entities.SubfldDefinitionId;
import com.simulator.globalModels.ResponseApiJson;
import com.simulator.repository.SubFldDefRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@Service
public class SubFldDefService {
    private final Logger logger = LogManager.getLogger(SubFldDefService.class);

    @Autowired
    SubFldDefRepository subFldDefRepository;



    public ResponseApiJson<List<SubfldDefinition>> getAllSubfldDefinitions() {
        String idRequest = "MS_" + new Date().getTime() + (Math.random() * 9999);
        try {
            List<SubfldDefinition> mccDefs = subFldDefRepository.findAll();
            return new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS, " get All Tags succesfully  !", mccDefs);

        } catch (Exception e) {
            logger.info("Exceception of this :: getAllSubfldDefinitions " + e.getMessage());
            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE, "some issues in getAllSubfldDefinitions please check with your providers !");
        }
    }

    public ResponseApiJson<List<SubfldDefinition>> getOneSubfldDefinition(SubfldDefinitionId id) {
        String idRequest = "MCCS_" + new Date().getTime() + (Math.random() * 9999);
        try {
            Optional<SubfldDefinition> mccDef = subFldDefRepository.findById(id);
            List<SubfldDefinition> mccDefs = new ArrayList<>();
            mccDef.ifPresent(mccDefs::add);
            return new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS, " Get one Tag successfully !", mccDefs);
        } catch (Exception e) {
            logger.info(("Exception of getOneSubfldDefinition" + e.getMessage()));
            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE, "some issues in getOneSubfldDefinition sucessfully !");
        }
    }

    public ResponseApiJson<?> addSubfldDefinition(SubfldDefinition mccDef) {
        String idRequest = "AMCS_" + new Date().getTime() + (Math.random() * 9999);
        logger.info(mccDef.toString());
        try {
            Optional<SubfldDefinition> mccDefOptional = subFldDefRepository.findById(mccDef.getSubfldDefinitionId());
            if (mccDefOptional.isEmpty()) {
                subFldDefRepository.save(mccDef);
                return new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS, " Insert de Tag succesfully");
            } else {
                return new ResponseApiJson<>(idRequest, GlobalVars.ALREADYEXIST, "this SubfldDefinition already exist !");
            }
        } catch (Exception e) {
            logger.info("Exception of getOneSubfldDefinition " + e.getMessage());
            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE, " Some issues in addSubfldDefinition please check provider !");
        }
    }





    public ResponseApiJson<?> updateSubfldDefinition(SubfldDefinition mccDef) throws IllegalAccessException {
        String idRequest = "UMCC_" + new Date().getTime() + (Math.random() * 9999);
        try {
            Optional<SubfldDefinition> mccDefOptional1 = subFldDefRepository.findById(mccDef.getSubfldDefinitionId());
            GlobalVars globalVars = new GlobalVars();
            if (mccDefOptional1.isPresent()) {
                SubfldDefinition mccDef1 = (SubfldDefinition) globalVars.construct(SubfldDefinition.class, mccDef, mccDefOptional1.get());
                logger.info("mccDefOptional1 " + mccDef1.toString());
                subFldDefRepository.save(mccDef1);
                return new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS, "Update  Tag  sucessfuly  !");
            } else {
                return new ResponseApiJson<>(idRequest, GlobalVars.NOTEXIST, "this SubfldDefinition  does not exit !");
            }

        } catch (Exception e) {
            logger.info("Exception of getOneSubfldDefinition " + e.getMessage());
            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE, "Some issues in updateMccDEfService please check with your provider !");
        }
    }
    public ResponseApiJson<?> deleteSubfldDefinition(SubfldDefinitionId id){
        String idRequest = "DMCC_" + new  Date().getTime() + (Math.random() * 9999);
        try {
            Optional<SubfldDefinition> mccDef = subFldDefRepository.findById(id);
            mccDef.ifPresent(subFldDefRepository :: delete);
            return new ResponseApiJson<>(idRequest , GlobalVars.SUCCESS ,"Delete Tag successfully");
        }catch ( Exception e ) {
            logger.info("Delete mccDef Exception  " + e.getMessage());
            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE,"Delete SubfldDefinition terminated with issue");
        }
    }
}


