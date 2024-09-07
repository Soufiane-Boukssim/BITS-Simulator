package com.simulator.pos.services;

import com.simulator.config.GlobalVars;
import com.simulator.entities.SubfldDefinition;
import com.simulator.entities.pos.PosSubfldDefinition;
import com.simulator.entities.pos.PosSubfldDefinitionId;
import com.simulator.globalModels.ResponseApiJson;
import com.simulator.repository.pos.PosSubFldDefRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@Service
public class PosSubFldDefService {
    private final Logger logger = LogManager.getLogger(PosSubFldDefService.class);

    @Autowired
    PosSubFldDefRepository subFldDefRepository;


    public ResponseApiJson<List<PosSubfldDefinition>> getAllSubfldDefinitions() {
        String idRequest = "MS_" + new Date().getTime() + (Math.random() * 9999);
        try {
            List<PosSubfldDefinition> mccDefs = subFldDefRepository.findAll();
            return new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS, " get All Tags succesfully  !", mccDefs);

        } catch (Exception e) {
            logger.info("Exceception of this :: getAllSubfldDefinitions " + e.getMessage());
            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE, "some issues in getAllSubfldDefinitions please check with your providers !");
        }
    }



    public ResponseApiJson<List<PosSubfldDefinition>> getSubfldDefinitionsByProtocols( PosSubfldDefinitionId id) {
        String idRequest = "MS_" + new Date().getTime() + (Math.random() * 9999);
        try {
            List<PosSubfldDefinition> mccDefs = subFldDefRepository.findByProtocolAndBankCode(id.getSubfldProtocole(), id.getBankCode());
            return new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS, " get All Tags succesfully  !", mccDefs);

        } catch (Exception e) {
            logger.info("Exceception of this :: getAllSubfldDefinitions " + e.getMessage());
            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE, "some issues in getAllSubfldDefinitions please check with your providers !");
        }
    }


    public ResponseApiJson<List<PosSubfldDefinition>> getOneSubfldDefinition(PosSubfldDefinitionId id) {
        String idRequest = "MCCS_" + new Date().getTime() + (Math.random() * 9999);
        try {
            Optional<PosSubfldDefinition> mccDef = subFldDefRepository.findById(id);
            List<PosSubfldDefinition> mccDefs = new ArrayList<>();
            mccDef.ifPresent(mccDefs::add);
            return new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS, " Get one Tag successfully !", mccDefs);
        } catch (Exception e) {
            logger.info(("Exception of getOneSubfldDefinition" + e.getMessage()));
            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE, "some issues in getOneSubfldDefinition sucessfully !");
        }
    }

    public ResponseApiJson<?> addSubfldDefinition(PosSubfldDefinition mccDef) {
        String idRequest = "AMCS_" + new Date().getTime() + (Math.random() * 9999);
        logger.info(mccDef.toString());
        try {
            Optional<PosSubfldDefinition> mccDefOptional = subFldDefRepository.findById(mccDef.getSubfldDefinitionId());
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





    public ResponseApiJson<?> updateSubfldDefinition(PosSubfldDefinition mccDef) throws IllegalAccessException {
        String idRequest = "UMCC_" + new Date().getTime() + (Math.random() * 9999);
        try {
            Optional<PosSubfldDefinition> mccDefOptional1 = subFldDefRepository.findById(mccDef.getSubfldDefinitionId());
            GlobalVars globalVars = new GlobalVars();
            if (mccDefOptional1.isPresent()) {
                PosSubfldDefinition mccDef1 = (PosSubfldDefinition) globalVars.construct(SubfldDefinition.class, mccDef, mccDefOptional1.get());
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
    public ResponseApiJson<?> deleteSubfldDefinition(PosSubfldDefinitionId id){
        String idRequest = "DMCC_" + new  Date().getTime() + (Math.random() * 9999);
        try {
            Optional<PosSubfldDefinition> mccDef = subFldDefRepository.findById(id);
            mccDef.ifPresent(subFldDefRepository :: delete);
            return new ResponseApiJson<>(idRequest , GlobalVars.SUCCESS ,"Delete Tag successfully");
        }catch ( Exception e ) {
            logger.info("Delete mccDef Exception  " + e.getMessage());
            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE,"Delete SubfldDefinition terminated with issue");
        }
    }
}


