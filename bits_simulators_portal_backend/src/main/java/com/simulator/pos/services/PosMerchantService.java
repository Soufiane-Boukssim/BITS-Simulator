package com.simulator.pos.services;

import com.simulator.config.GlobalVars;
import com.simulator.entities.UserActivityTracking;
import com.simulator.entities.UserManagement;
import com.simulator.entities.pos.PosMerchantParam;
import com.simulator.entities.pos.PosMerchantParamId;
import com.simulator.globalModels.ResponseApiJson;
import com.simulator.iso.services.MerchantService;
import com.simulator.repository.UserActivityTrackingRepository;
import com.simulator.repository.pos.PosMerchantRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@Service
public  class PosMerchantService {
    private final Logger logger = LogManager.getLogger(MerchantService.class);
    @Autowired
    PosMerchantRepository merchantRepository;

    @Autowired
    UserActivityTrackingRepository userActivityTrackingRepository;

    @Autowired
    GlobalVars globalVars;

    public ResponseApiJson<List<PosMerchantParam>> getAllMerchantParams(PosMerchantParamId merchantParamId) {
        String idRequest = "MS_" + new Date().getTime() + (Math.random() * 9999);
        Optional<UserManagement> userM = globalVars.getUser(GlobalVars.getBearerTokenHeader());
        try {
            List<PosMerchantParam> merchantParams = merchantRepository.findByBankCodeAndMerProtocole(merchantParamId.getBankCode(), merchantParamId.getMerProtocol());
            // Track user activity
            UserActivityTracking userTrace =new UserActivityTracking(userM.get().getUserCode(), "Retrieve", "Merchant", "000.000.00.00", "Success", GlobalVars.SUCCESS, "Retrieve Merchant", "Merchant retrieved successfully", new Date());
            userActivityTrackingRepository.save(userTrace);

            return new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS, "Get all Merchant succesfully  !", merchantParams);


        } catch (Exception e) {
            logger.info("Exception of this::getAllMerchantParams " + e.getMessage());
            // Track user activity for exception
            UserActivityTracking userTrace = new UserActivityTracking(userM.get().getUserCode(), "Retrieve", "Merchant", "000.000.00.00", "Error", GlobalVars.EXCEPTIONSISSUE, "Retrieve Merchant", "some issues in getAllMerchantParams please check with your provider !", new Date());
            userActivityTrackingRepository.save(userTrace);
            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE, "some issues in getAllMerchantParams please check with your provider !");

        }


    }

    public ResponseApiJson<List<PosMerchantParam>> getOneMerchantParam(PosMerchantParamId id) {
        String idRequest = "MSS_" + new Date().getTime() + (Math.random() * 9999);
        try {
            Optional<PosMerchantParam> merchantParam = merchantRepository.findById(id);
            List<PosMerchantParam> merchantParams = new ArrayList<>();
            merchantParam.ifPresent(merchantParams::add);
            return new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS , "Get one merchantParam succesfully  !" , merchantParams);
        } catch (Exception e)   {
            logger.info("Exception of  getOneMerchantParam " + e.getMessage());
            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE , " some issues in getOneMerchantParam please check with your provider !");

        }
    }

    public ResponseApiJson<?> addMerchantParam(PosMerchantParam merchantParam){
        String idRequest = "AM_"  + new  Date().getTime() + (Math.random() * 9999);
        Optional<UserManagement> userM = globalVars.getUser(GlobalVars.getBearerTokenHeader());
        logger.info(merchantParam.toString());
        try {
            Optional<PosMerchantParam> merchantParamtoCheck = merchantRepository.findById(merchantParam.getId());
            if ( merchantParamtoCheck.isEmpty()) {
                merchantRepository.save(merchantParam);
                // Track user activity
//                UserActivityTracking userTrace =new UserActivityTracking(userM.get().getUserCode(), "Insert", "MerchantParam", "000.000.00.00", "Success", GlobalVars.SUCCESS, "Insert MerchantParam", "MerchantParam inserted successfully", new Date());
//                userActivityTrackingRepository.save(userTrace);
                return new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS, "Insertde merchantParam sucessfully  !");
            }else {
                // Track user activity
//                UserActivityTracking userTrace =new UserActivityTracking(userM.get().getUserCode(), "Insert", "MerchantParam", "000.000.00.00", "Failed", GlobalVars.ALREADYEXIST, "Insert MerchantParam", "this MerchantParam already exist !", new Date());
//                userActivityTrackingRepository.save(userTrace);

                return  new ResponseApiJson<>(idRequest , GlobalVars.ALREADYEXIST , "this MerchantParam already exist !");

            }
        } catch (Exception e ){
            logger.info("Exception of getOneMerchantParam "  + e.getMessage());
            // Track user activity for exception
//            UserActivityTracking userTrace = new UserActivityTracking(userM.get().getUserCode(), "Insert", "MerchantParam", "000.000.00.00", "Error", GlobalVars.EXCEPTIONSISSUE, "Insert MerchantParam", " Some issues in addMerchantParam please check with your provider !", new Date());
//            userActivityTrackingRepository.save(userTrace);


            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE , "Some issues in addMerchantParam please check with your provider !");
        }
    }

    public  ResponseApiJson<?> updateMerchantParam(PosMerchantParam merchantParam) throws IllegalAccessException {
        String  idRequest = "UMS_"  + new Date().getTime() + (Math.random() * 9999);
        Optional<UserManagement> userM = globalVars.getUser(GlobalVars.getBearerTokenHeader());
        try{
            Optional<PosMerchantParam> merchantParamto = merchantRepository.findById(merchantParam.getId());
            GlobalVars globalVars = new GlobalVars();
            if (merchantParamto.isPresent()){
                PosMerchantParam merchantParam1 = (PosMerchantParam)  globalVars.construct(PosMerchantParam.class, merchantParam , merchantParamto.get());
                logger.info("merchantParamto " + merchantParam1.toString());
                merchantRepository.save(merchantParam1);

                // Track user activity
                UserActivityTracking userTrace =new UserActivityTracking(userM.get().getUserCode(), "Update", "MerchantParam", "000.000.00.00", "Successfully", GlobalVars.SUCCESS, "Update MerchantParam", "MerchantParam updated successfully", new Date());
                userActivityTrackingRepository.save(userTrace);

                return new ResponseApiJson<>(idRequest , GlobalVars.SUCCESS , "Update  merchantParam  sucessfuly  !");

            } else {
                // Track user activity
                UserActivityTracking userTrace =new UserActivityTracking(userM.get().getUserCode(), "Update", "MerchantParam", "000.000.00.00", "Failed", GlobalVars.ALREADYEXIST, "Update MerchantParam", "this MerchantParam  does not exit !", new Date());
                userActivityTrackingRepository.save(userTrace);

                return new ResponseApiJson<>(idRequest , GlobalVars.NOTEXIST , "this merchantParam  does not exit   !");

            }
        } catch (Exception e) {
            logger.info("Exception of getOneMerchantParam "  + e.getMessage());

            // Track user activity for exception
            UserActivityTracking userTrace = new UserActivityTracking(userM.get().getUserCode(), "Update", "MerchantParam", "000.000.00.00", "Error", GlobalVars.EXCEPTIONSISSUE, "Update MerchantParam", " Some issues in updateMerchantService please check with your provider !", new Date());
            userActivityTrackingRepository.save(userTrace);

            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE , "Some issues in updateMerchantService please check with your provider !");


        }
    }

    public  ResponseApiJson<?> deleteMerchantParam(PosMerchantParamId id){
        String  idRequest = "DM_" + new Date().getTime() + (Math.random() * 9999);
        Optional<UserManagement> userM = globalVars.getUser(GlobalVars.getBearerTokenHeader());
        logger.info("id:::>"+id);
        try {
            Optional<PosMerchantParam> merchantParam = merchantRepository.findById(id);
            merchantParam.ifPresent(merchantRepository ::delete);

            // Track user activity
            UserActivityTracking userTrace =new UserActivityTracking(userM.get().getUserCode(), "Delete", "MerchantParam", "000.000.00.00", "Successfully", GlobalVars.SUCCESS, "Delete MerchantParam", "MerchantParam deleted successfully", new Date());
            userActivityTrackingRepository.save(userTrace);

            return  new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS , "Delete merchantParam succesfully !");
        }catch (Exception e){
            logger.info("Delete merchantParam Exception: " +e.getMessage());

            // Track user activity for exception
            UserActivityTracking userTrace = new UserActivityTracking(userM.get().getUserCode(), "Delete", "MerchantParam", "000.000.00.00", "Error", GlobalVars.EXCEPTIONSISSUE, "Delete MerchantParam", " Some issues in deleteMerchantParam please check with your provider !", new Date());
            userActivityTrackingRepository.save(userTrace);

            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE ,"deleteMerchantParam terminated with issue");
        }
    }

}





