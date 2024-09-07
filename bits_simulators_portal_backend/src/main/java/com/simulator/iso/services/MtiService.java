package com.simulator.iso.services;

import com.simulator.config.GlobalVars;
import com.simulator.entities.*;
import com.simulator.globalModels.ResponseApiJson;
import com.simulator.repository.MtiRepository;
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
public  class MtiService {
    private final Logger logger = LogManager.getLogger(MtiService.class);
    @Autowired
    MtiRepository mtiRepository;


    @Autowired
    UserActivityTrackingRepository userActivityTrackingRepository;

    @Autowired
    GlobalVars globalVars;



/*
    public MtiDef getMtiDef(MtiDefId id) {
        return mtiRepository.findById(id).orElse(null);
    }
*/

/*

    public List<MtiDef> getFilteredMtiList(String bankCode, String mtiProtocol) {
        return mtiRepository.findByBankCodeAndMtiProtocole(bankCode, mtiProtocol);
    }
*/




  /*  public ResponseApiJson<List<MtiDef>> getAllMtiDefs() {
        String idRequest = "MS_" + new Date().getTime() + (Math.random() * 9999);

        try {
            List<MtiDef> mtiDefs = mtiRepository.findAll();
            return new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS, "Get all mti succesfully  !", mtiDefs);

        } catch (Exception e) {
            logger.info("Exception of this::getAllMtiDefs " + e.getMessage());
            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE, "some issues in getAllMtiDefs please check with your provider !");

        }


    }
*/




    public ResponseApiJson<List<MtiDef>> getAllMtiDefs(MtiDefId mtiDefId) {
        String idRequest = "MS_" + new Date().getTime() + (Math.random() * 9999);

        Optional<UserManagement> userM = globalVars.getUser(GlobalVars.getBearerTokenHeader());

        try {
            List<MtiDef> mtiDefs = mtiRepository.findByBankCodeAndMtiProtocole(mtiDefId.getBankCode(),mtiDefId.getMtiProtocol());

             // Track user activity
            UserActivityTracking userTrace =new UserActivityTracking(userM.get().getUserCode(), "Retrieve", "MtiDef", "000.000.00.00", "Success", GlobalVars.SUCCESS, "Retrieve MtiDef", "MtiDef retrieved successfully", new Date());
            userActivityTrackingRepository.save(userTrace);

                return new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS, "Get all MtiDef succesfully  !", mtiDefs);

        } catch (Exception e) {
            logger.info("Exception of this::getAllAccountDefs " + e.getMessage());

            // Track user activity for exception
            UserActivityTracking userTrace = new UserActivityTracking(userM.get().getUserCode(), "Retrieve", "MtiDef", "000.000.00.00", "Error", GlobalVars.EXCEPTIONSISSUE, "Retrieve MtiDef", "some issues in getAllMtiDefs please check with your provider !", new Date());
            userActivityTrackingRepository.save(userTrace);

            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE, "some issues in getAllMtiDefs please check with your provider !");

        }



    }
    public ResponseApiJson<List<MtiDef>> getOneMtiDef(MtiDefId id) {
        String idRequest = "MSS_" + new Date().getTime() + (Math.random() * 9999);
        Optional<UserManagement> userM = globalVars.getUser(GlobalVars.getBearerTokenHeader());
        try {
            Optional<MtiDef> mtiDef = mtiRepository.findById(id);
            List<MtiDef> mtiDefs = new ArrayList<>();
                mtiDef.ifPresent(mtiDefs::add);

            // Track user activity
            UserActivityTracking userTrace =new UserActivityTracking(userM.get().getUserCode(), "Retrieve", "MtiDef", "000.000.00.00", "Success", GlobalVars.SUCCESS, "Retrieve MtiDef", "MtiDef retrieved successfully", new Date());
            userActivityTrackingRepository.save(userTrace);

                return new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS , "Get one mtiDef succesfully  !" , mtiDefs);
            } catch (Exception e)   {
                logger.info("Exception of  getOneMtiDef " + e.getMessage());

            // Track user activity for exception
            UserActivityTracking userTrace = new UserActivityTracking(userM.get().getUserCode(), "Retrieve", "MtiDef", "000.000.00.00", "Error", GlobalVars.EXCEPTIONSISSUE, "Retrieve MtiDef", "some issues in getAllMtiDefs please check with your provider !", new Date());
            userActivityTrackingRepository.save(userTrace);

                return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE , " some issues in getOneMtiDef please check with your provider !");

            }
        }

        public ResponseApiJson<?> addMtiDef(MtiDef mtiDef){
             String idRequest = "AM_"  + new  Date().getTime() + (Math.random() * 9999);
            Optional<UserManagement> userM = globalVars.getUser(GlobalVars.getBearerTokenHeader());
             logger.info(mtiDef.toString());
             try {
                 Optional<MtiDef> mtiDeftoCheck = mtiRepository.findById(mtiDef.getId());
                 if ( mtiDeftoCheck.isEmpty()) {
                     mtiRepository.save(mtiDef);
                     // user Activity tracking Hamza 29.02.2024 start
                     UserActivityTracking userTrace = new UserActivityTracking(userM.get().getUserCode(),"Add", "mti_def","000.000.00.00", "Success", GlobalVars.SUCCESS,"Add Mti","Add Mti with id : "+mtiDef.getId().getMtiCode()+" successfull",new Date());
                     userActivityTrackingRepository.save(userTrace);
                     // user Activity tracking Hamza 29.02.2024 end
                     return new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS, "Insertde mtiDef sucessfully  !");
                 }else {
                     // user Activity tracking Hamza 29.02.2024 start
                     UserActivityTracking userTrace = new UserActivityTracking(userM.get().getUserCode(),"Add", "mti_def","000.000.00.00", "Failed", GlobalVars.ALREADYEXIST,"Add Mti","Mti with id : "+mtiDef.getId().getMtiCode()+" already exist !",new Date());
                     userActivityTrackingRepository.save(userTrace);
                     // user Activity tracking Hamza 29.02.2024 end
                     return  new ResponseApiJson<>(idRequest , GlobalVars.ALREADYEXIST , "this MtiDef already exist !");

                 }
             } catch (Exception e ){
                 logger.info("Exception of getOneMtiDef "  + e.getMessage());

                 // user Activity tracking Hamza 29.02.2024 start
                 UserActivityTracking userTrace = new UserActivityTracking(userM.get().getUserCode(),"Add", "mti_def","000.000.00.00", "Failed", GlobalVars.EXCEPTIONSISSUE,"Add Mti","Some issues in addMtiDef please check provider !",new Date());
                 userActivityTrackingRepository.save(userTrace);
                 // user Activity tracking Hamza 29.02.2024 end
                 return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE , "Some issues in addMtiDef please check with your provider !");
             }
        }

        public  ResponseApiJson<?> updateMtiDef(MtiDef mtiDef) throws IllegalAccessException {
            Optional<UserManagement> userM = globalVars.getUser(GlobalVars.getBearerTokenHeader());
              String  idRequest = "UMS_"  + new Date().getTime() + (Math.random() * 9999);
              try{
                  Optional<MtiDef> mtiDefto = mtiRepository.findById(mtiDef.getId());
                  GlobalVars globalVars = new GlobalVars();
                  if (mtiDefto.isPresent()){
                      MtiDef mtiDef1 = (MtiDef)  globalVars.construct(MtiDef.class, mtiDef , mtiDefto.get());
                      logger.info("mtiDefto " + mtiDef1.toString());
                      mtiRepository.save(mtiDef1);
                      // user Activity tracking Hamza 29.02.2024 start
                      UserActivityTracking userTrace = new UserActivityTracking(userM.get().getUserCode(),"Update", "mti_def","000.000.00.00", "Success", GlobalVars.SUCCESS,"Update Mti","Update Mti with id : "+mtiDef.getId().getMtiCode()+" successfull",new Date());
                      userActivityTrackingRepository.save(userTrace);
                      // user Activity tracking Hamza 29.02.2024 end
                      return new ResponseApiJson<>(idRequest , GlobalVars.SUCCESS , "Update  mtiDef  sucessfuly  !");

                  } else {
                      // user Activity tracking Hamza 29.02.2024 start
                      UserActivityTracking userTrace = new UserActivityTracking(userM.get().getUserCode(),"Update", "mti_def","000.000.00.00", "Failed", GlobalVars.NOTEXIST,"Update Mti","Mti with id : "+mtiDef.getId().getMtiCode()+" does not exist",new Date());
                      userActivityTrackingRepository.save(userTrace);
                      // user Activity tracking Hamza 29.02.2024 end
                      return new ResponseApiJson<>(idRequest , GlobalVars.NOTEXIST , "this mtiDef  does not exit   !");

                  }
              } catch (Exception e) {
                  logger.info("Exception of getOneMtiDef "  + e.getMessage());
                  // user Activity tracking Hamza 29.02.2024 start
                  UserActivityTracking userTrace = new UserActivityTracking(userM.get().getUserCode(),"Update", "mti_def","000.000.00.00", "Failed", GlobalVars.NOTEXIST,"Update Mti","Some issues in updateMtiDEfService please check with your provider !",new Date());
                  userActivityTrackingRepository.save(userTrace);
                  // user Activity tracking Hamza 29.02.2024 end
                  return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE , "Some issues in updateMtiService please check with your provider !");


              }
        }

        public  ResponseApiJson<?> deleteMtiDef(MtiDefId id){
        String  idRequest = "DM_" + new Date().getTime() + (Math.random() * 9999);
        Optional<UserManagement> userM = globalVars.getUser(GlobalVars.getBearerTokenHeader());
        try {
            Optional<MtiDef> mtiDef = mtiRepository.findById(id);
            mtiDef.ifPresent(mtiRepository ::delete);
            // user Activity tracking Hamza 29.02.2024 start
            UserActivityTracking userTrace = new UserActivityTracking(userM.get().getUserCode(),"Delete", "mti_def","000.000.00.00", "Success", GlobalVars.SUCCESS,"Delete Mti","Delete Mti with id : "+id+" successfull",new Date());
            userActivityTrackingRepository.save(userTrace);
            // user Activity tracking Hamza 29.02.2024 end
            return  new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS , "Delete mtiDef succesfully !");
        }catch (Exception e){
            logger.info("Delete mtiDef Exception: " +e.getMessage());
            // user Activity tracking Hamza 29.02.2024 start
            UserActivityTracking userTrace = new UserActivityTracking(userM.get().getUserCode(),"Delete", "mti_def","000.000.00.00", "Failed", GlobalVars.NOTEXIST,"Delete Mti","Delete MtiDef terminated with issue",new Date());
            userActivityTrackingRepository.save(userTrace);
            // user Activity tracking Hamza 29.02.2024 end
            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE ,"deleteMtiDef terminated with issue");
        }
        }

}





