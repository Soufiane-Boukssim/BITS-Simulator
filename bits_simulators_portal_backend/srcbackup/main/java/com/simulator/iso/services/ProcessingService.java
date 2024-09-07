package com.simulator.iso.services;

import com.simulator.config.GlobalVars;
import com.simulator.entities.ProcessingDef;
import com.simulator.entities.ProcessingDefId;
import com.simulator.globalModels.ResponseApiJson;
import com.simulator.repository.ProcessingRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@Service
public  class ProcessingService {
    private final Logger logger = LogManager.getLogger(ProcessingService.class);
    @Autowired
    ProcessingRepository processingRepository;


    public ProcessingDef getProcessingDef(ProcessingDefId id) { return processingRepository.findById(id).orElse(null);
    }

    public List<ProcessingDef> getFilteredProcessingList(String bankCode, Character procProtocol) {
        return processingRepository.findByBankCodeAndProcProtocol(bankCode, procProtocol);
    }



    public ResponseApiJson<List<ProcessingDef>> getAllProcessingDefs() {
        String idRequest = "MS_" + new Date().getTime() + (Math.random() * 9999);

        try {
            List<ProcessingDef> processingDefs = processingRepository.findAll();
            return new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS, "Get all Processing succesfully  !", processingDefs);

        } catch (Exception e) {
            logger.info("Exception of this::getAllProcessingDefs " + e.getMessage());
            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE, "some issues in getAllProcessingDefs please check with your provider !");

        }


    }

    public ResponseApiJson<List<ProcessingDef>> getOneProcessingDef(ProcessingDefId id) {
        String idRequest = "MSS_" + new Date().getTime() + (Math.random() * 9999);
        try {
            Optional<ProcessingDef> processingDef = processingRepository.findById(id);
            List<ProcessingDef> processingDefs = new ArrayList<>();
            processingDef.ifPresent(processingDefs::add);
            return new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS , "Get one ProcessingDef succesfully  !" , processingDefs);
        } catch (Exception e)   {
            logger.info("Exception of  getOneProcessingDef " + e.getMessage());
            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE , " some issues in getOneProcessingDef please check with your provider !");

        }
    }

    public ResponseApiJson<?> addProcessingDef(ProcessingDef processingDef){
        String idRequest = "AM_"  + new  Date().getTime() + (Math.random() * 9999);
        logger.info(processingDef.toString());
        try {
            Optional<ProcessingDef> processingDeftoCheck = processingRepository.findById(processingDef.getId());
            if ( processingDeftoCheck.isEmpty()) {
                processingRepository.save(processingDef);
                return new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS, "Insertde processingDef sucessfully  !");
            }else {
                return  new ResponseApiJson<>(idRequest , GlobalVars.ALREADYEXIST , "this ProcessingDef already exist !");

            }
        } catch (Exception e ){
            logger.info("Exception of getOneProcessingDef "  + e.getMessage());
            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE , "Some issues in addProcessingDef please check with your provider !");
        }
    }

    public  ResponseApiJson<?> updateProcessingDef(ProcessingDef processingDef) throws IllegalAccessException {
        String  idRequest = "UMS_"  + new Date().getTime() + (Math.random() * 9999);
        try{
            Optional<ProcessingDef> processingDefto = processingRepository.findById(processingDef.getId());
            GlobalVars globalVars = new GlobalVars();
            if (processingDefto.isPresent()){
                ProcessingDef processingDef1 = (ProcessingDef)  globalVars.construct(ProcessingDef.class, processingDef , processingDefto.get());
                logger.info("processingDefto " + processingDef1.toString());
                processingRepository.save(processingDef1);
                return new ResponseApiJson<>(idRequest , GlobalVars.SUCCESS , "Update  processingDef  sucessfuly  !");

            } else {
                return new ResponseApiJson<>(idRequest , GlobalVars.NOTEXIST , "this ProcessingDef  does not exit   !");

            }
        } catch (Exception e) {
            logger.info("Exception of getOneMtiDef "  + e.getMessage());
            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE , "Some issues in updateProcessingService please check with your provider !");


        }
    }

    public  ResponseApiJson<?> deleteProcessingDef(ProcessingDefId id){
        String  idRequest = "DM_" + new Date().getTime() + (Math.random() * 9999);
        try {
            Optional<ProcessingDef> processingDef = processingRepository.findById(id);
            processingDef.ifPresent(processingRepository ::delete);
            return  new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS , "Delete processingDef succesfully !");
        }catch (Exception e){
            logger.info("Delete processingDef Exception: " +e.getMessage());
            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE ,"deleteProcessingDef terminated with issue");
        }
    }

}





