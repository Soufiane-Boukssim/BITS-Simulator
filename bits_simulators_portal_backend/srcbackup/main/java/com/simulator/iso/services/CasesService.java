package com.simulator.iso.services;

import com.simulator.config.GlobalVars;
import com.simulator.entities.CasesDefinition;
import com.simulator.entities.CasesDefinitionId;
import com.simulator.globalModels.ResponseApiJson;
import com.simulator.repository.CasesRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@Service
public  class CasesService {
    private final Logger logger = LogManager.getLogger(CasesService.class);
    @Autowired
    CasesRepository casesRepository;


    public CasesDefinition getCasesDefinition(CasesDefinitionId id) {return casesRepository.findById(id).orElse(null);
    }



    public List<CasesDefinition> getFilteredCasesList(String bankCode, Character caseProtocole) {
        return casesRepository.findByBankCodeAndCaseProtocole(bankCode, caseProtocole);
    }

    public ResponseApiJson<List<CasesDefinition>> getAllCasesDefinitions() {
        String idRequest = "MS_" + new Date().getTime() + (Math.random() * 9999);
        try {
            List<CasesDefinition> casesDefinitions = casesRepository.findAll();
            return new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS, "Get all Cases succesfully  !", casesDefinitions);

        } catch (Exception e) {
            logger.info("Exception of this::getAllCasesDefinitions " + e.getMessage());
            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE, "some issues in getAllCasesDefinitions please check with your provider !");

        }


    }

    public ResponseApiJson<List<CasesDefinition>> getOneCasesDefinition(CasesDefinitionId id) {
        String idRequest = "MSS_" + new Date().getTime() + (Math.random() * 9999);
        try {
            Optional<CasesDefinition> casesDefinition = casesRepository.findById(id);
            List<CasesDefinition> CasesDefinitions = new ArrayList<>();
            casesDefinition.ifPresent(CasesDefinitions::add);
            return new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS , "Get one casesDefinition succesfully  !" , CasesDefinitions);
        } catch (Exception e)   {
            logger.info("Exception of  getOneCasesDefinition " + e.getMessage());
            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE , " some issues in getOneCasesDefinition please check with your provider !");

        }
    }

    public ResponseApiJson<?> addCasesDefinition(CasesDefinition casesDefinition){
        String idRequest = "AM_"  + new  Date().getTime() + (Math.random() * 9999);
        logger.info(casesDefinition.toString());
        try {
            Optional<CasesDefinition> casesDefinitiontoCheck = casesRepository.findById(casesDefinition.getId());
            if ( casesDefinitiontoCheck.isEmpty()) {
                casesRepository.save(casesDefinition);
                return new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS, "Insertde casesDefinition sucessfully  !");
            }else {
                return  new ResponseApiJson<>(idRequest , GlobalVars.ALREADYEXIST , "this CasesDefinition already exist !");

            }
        } catch (Exception e ){
            logger.info("Exception of getOneCasesDefinition "  + e.getMessage());
            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE , "Some issues in addCasesDefinition please check with your provider !");
        }
    }

    public  ResponseApiJson<?> updateCasesDefinition(CasesDefinition casesDefinition) throws IllegalAccessException {
        String  idRequest = "UMS_"  + new Date().getTime() + (Math.random() * 9999);
        try{
            Optional<CasesDefinition> casesDefinitionto = casesRepository.findById(casesDefinition.getId());
            GlobalVars globalVars = new GlobalVars();
            if (casesDefinitionto.isPresent()){
                CasesDefinition casesDefinition1 = (CasesDefinition)  globalVars.construct(CasesDefinition.class, casesDefinition , casesDefinitionto.get());
                logger.info("casesDefinitionto " + casesDefinition1.toString());
                casesRepository.save(casesDefinition1);
                return new ResponseApiJson<>(idRequest , GlobalVars.SUCCESS , "Update  casesDefinition  sucessfuly  !");

            } else {
                return new ResponseApiJson<>(idRequest , GlobalVars.NOTEXIST , "this casesDefinition  does not exit   !");

            }
        } catch (Exception e) {
            logger.info("Exception of getOneCasesDefinition "  + e.getMessage());
            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE , "Some issues in updateCasesService please check with your provider !");


        }
    }

    public  ResponseApiJson<?> deleteCasesDefinition(CasesDefinitionId id){
        String  idRequest = "DM_" + new Date().getTime() + (Math.random() * 9999);
        try {
            Optional<CasesDefinition> casesDefinition = casesRepository.findById(id);
            casesDefinition.ifPresent(casesRepository ::delete);
            return  new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS , "Delete casesDefinition succesfully !");
        }catch (Exception e){
            logger.info("Delete casesDefinition Exception: " +e.getMessage());
            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE ,"deleteCasesDefinition terminated with issue");
        }
    }
}





