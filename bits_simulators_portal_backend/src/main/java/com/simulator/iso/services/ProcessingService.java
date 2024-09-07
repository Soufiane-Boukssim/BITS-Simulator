package com.simulator.iso.services;

import com.simulator.config.GlobalVars;
import com.simulator.entities.ProcessingDef;
import com.simulator.entities.ProcessingDefId;
import com.simulator.entities.UserActivityTracking;
import com.simulator.entities.UserManagement;
import com.simulator.globalModels.ResponseApiJson;
import com.simulator.repository.ProcessingRepository;
import com.simulator.repository.UserActivityTrackingRepository;
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

    @Autowired
    UserActivityTrackingRepository userActivityTrackingRepository;

    @Autowired
    GlobalVars globalVars;





    public ResponseApiJson<List<ProcessingDef>> getAllProcessingDefs() {
        String idRequest = "MS_" + new Date().getTime() + (Math.random() * 9999);
        try {
            List<ProcessingDef> processingDefs = processingRepository.findAll();

                return new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS, "Get all processingDefs  succesfully  !", processingDefs);

        } catch (Exception e) {
            logger.info("Exception of this::getAllAccountDefs " + e.getMessage());
            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE, "some issues in getAllAccountDefs please check with your provider !");

        }

    }

    public ResponseApiJson<List<ProcessingDef>> getProcessingDefByProtocol(ProcessingDefId id) {
        String idRequest = "MS_" + new Date().getTime() + (Math.random() * 9999);
        logger.info("test id V2 :::>"+id);
        try {
            List<ProcessingDef> processingDefs = processingRepository.findByProtocolAndBankCode(id.getProcProtocol(), id.getBankCode());

            return new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS, "Get all processingDefs  succesfully  !", processingDefs);

        } catch (Exception e) {
            logger.info("Exception of this::getAllAccountDefs " + e.getMessage());
            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE, "some issues in getAllAccountDefs please check with your provider !");

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
        Optional<UserManagement> userM = globalVars.getUser(GlobalVars.getBearerTokenHeader());
        logger.info(processingDef.toString());
        try {
            Optional<ProcessingDef> processingDeftoCheck = processingRepository.findById(processingDef.getId());
            if ( processingDeftoCheck.isEmpty()) {
                processingRepository.save(processingDef);
                // user Activity tracking Hamza 29.02.2024 start
                UserActivityTracking userTrace = new UserActivityTracking(userM.get().getUserCode(),"Add", "processing_Def","000.000.00.00", "Success", GlobalVars.SUCCESS,"Add processing","Add processing with id : "+processingDef.getId()+" successfull",new Date());
                userActivityTrackingRepository.save(userTrace);
                // user Activity tracking Hamza 29.02.2024 end
                return new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS, "Insertde processingDef sucessfully  !");
            }else {
                // user Activity tracking Hamza 29.02.2024 start
                UserActivityTracking userTrace = new UserActivityTracking(userM.get().getUserCode(),"Add", "processing_Def","000.000.00.00", "Failed", GlobalVars.ALREADYEXIST,"Add processing","processing with id : "+processingDef.getId()+" already exist !",new Date());
                userActivityTrackingRepository.save(userTrace);
                // user Activity tracking Hamza 29.02.2024 end
                return  new ResponseApiJson<>(idRequest , GlobalVars.ALREADYEXIST , "this ProcessingDef already exist !");

            }
        } catch (Exception e ){
            logger.info("Exception of getOneProcessingDef "  + e.getMessage());
            // user Activity tracking Hamza 29.02.2024 start
            UserActivityTracking userTrace = new UserActivityTracking(userM.get().getUserCode(),"Add", "processing_Def","000.000.00.00", "Failed", GlobalVars.EXCEPTIONSISSUE,"Add processing","Some issues in addFunctDef please check provider !",new Date());
            userActivityTrackingRepository.save(userTrace);
            // user Activity tracking Hamza 29.02.2024 end
            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE , "Some issues in addProcessingDef please check with your provider !");
        }
    }

    public  ResponseApiJson<?> updateProcessingDef(ProcessingDef processingDef) throws IllegalAccessException {
        String  idRequest = "UMS_"  + new Date().getTime() + (Math.random() * 9999);
        Optional<UserManagement> userM = globalVars.getUser(GlobalVars.getBearerTokenHeader());
        try{
            Optional<ProcessingDef> processingDefto = processingRepository.findById(processingDef.getId());
            GlobalVars globalVars = new GlobalVars();
            if (processingDefto.isPresent()){
                ProcessingDef processingDef1 = (ProcessingDef)  globalVars.construct(ProcessingDef.class, processingDef , processingDefto.get());
                logger.info("processingDefto " + processingDef1.toString());
                processingRepository.save(processingDef1);
                // user Activity tracking Hamza 29.02.2024 start
                UserActivityTracking userTrace = new UserActivityTracking(userM.get().getUserCode(),"Update", "processing_Def","000.000.00.00", "Success", GlobalVars.SUCCESS,"Update processing","Update processing with id : "+processingDef.getId()+" successfull",new Date());
                userActivityTrackingRepository.save(userTrace);
                // user Activity tracking Hamza 29.02.2024 end
                return new ResponseApiJson<>(idRequest , GlobalVars.SUCCESS , "Update  processingDef  sucessfuly  !");

            } else {
                // user Activity tracking Hamza 29.02.2024 start
                UserActivityTracking userTrace = new UserActivityTracking(userM.get().getUserCode(),"Update", "processing_Def","000.000.00.00", "Failed", GlobalVars.NOTEXIST,"Update processing","processing with id : "+processingDef.getId()+" does not exist",new Date());
                userActivityTrackingRepository.save(userTrace);
                // user Activity tracking Hamza 29.02.2024 end
                return new ResponseApiJson<>(idRequest , GlobalVars.NOTEXIST , "this ProcessingDef  does not exit   !");

            }
        } catch (Exception e) {
            logger.info("Exception of getOneMtiDef "  + e.getMessage());
            // user Activity tracking Hamza 29.02.2024 start
            UserActivityTracking userTrace = new UserActivityTracking(userM.get().getUserCode(),"Update", "processing_Def","000.000.00.00", "Failed", GlobalVars.NOTEXIST,"Update processing","Some issues in updateProcessDEfService please check with your provider !",new Date());
            userActivityTrackingRepository.save(userTrace);
            // user Activity tracking Hamza 29.02.2024 end
            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE , "Some issues in updateProcessingService please check with your provider !");


        }
    }

    public  ResponseApiJson<?> deleteProcessingDef(ProcessingDefId id){
        String  idRequest = "DM_" + new Date().getTime() + (Math.random() * 9999);
        Optional<UserManagement> userM = globalVars.getUser(GlobalVars.getBearerTokenHeader());
        try {
            Optional<ProcessingDef> processingDef = processingRepository.findById(id);
            processingDef.ifPresent(processingRepository ::delete);
            // user Activity tracking Hamza 29.02.2024 start
            UserActivityTracking userTrace = new UserActivityTracking(userM.get().getUserCode(),"Delete", "processing_Def","000.000.00.00", "Success", GlobalVars.SUCCESS,"Delete processing","Delete processing with id : "+id+" successfull",new Date());
            userActivityTrackingRepository.save(userTrace);
            // user Activity tracking Hamza 29.02.2024 end
            return  new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS , "Delete processingDef succesfully !");
        }catch (Exception e){
            logger.info("Delete processingDef Exception: " +e.getMessage());
            // user Activity tracking Hamza 29.02.2024 start
            UserActivityTracking userTrace = new UserActivityTracking(userM.get().getUserCode(),"Delete", "processing_Def","000.000.00.00", "Failed", GlobalVars.NOTEXIST,"Delete processing","Delete processDef terminated with issue",new Date());
            userActivityTrackingRepository.save(userTrace);
            // user Activity tracking Hamza 29.02.2024 end
            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE ,"deleteProcessingDef terminated with issue");
        }
    }

}





