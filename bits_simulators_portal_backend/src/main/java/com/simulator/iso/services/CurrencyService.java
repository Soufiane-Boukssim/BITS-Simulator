package com.simulator.iso.services;

import com.simulator.config.GlobalVars;
import com.simulator.entities.CurrencyParam;
import com.simulator.entities.UserActivityTracking;
import com.simulator.entities.UserManagement;
import com.simulator.globalModels.ResponseApiJson;
import com.simulator.repository.CurrencyRepository;
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
public  class CurrencyService {
    private final Logger logger = LogManager.getLogger(CurrencyService.class);
    @Autowired
    CurrencyRepository currencyRepository;

    @Autowired
    UserActivityTrackingRepository userActivityTrackingRepository;

    @Autowired
    GlobalVars globalVars;


    public ResponseApiJson<List<CurrencyParam>> getAllCurrencyParams() {
        String idRequest = "MS_" + new Date().getTime() + (Math.random() * 9999);
        Optional<UserManagement> userM = globalVars.getUser(GlobalVars.getBearerTokenHeader());
        try {
            List<CurrencyParam> currencyParams = currencyRepository.findAll();

            // Track user activity
            UserActivityTracking userTrace = new UserActivityTracking(userM.get().getUserCode(), "Retrieve", "CurrencyParams", "000.000.00.00", "Success", GlobalVars.SUCCESS, "Retrieve CurrencyParams", "CurrencyParams retrieved successfully", new Date());
            userActivityTrackingRepository.save(userTrace);

            return new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS, "Get all currency succesfully  !", currencyParams);

        } catch (Exception e) {
            logger.info("Exception of this::getAllCurrencyParams " + e.getMessage());

            // Track user activity for exception
            UserActivityTracking userTrace = new UserActivityTracking(userM.get().getUserCode(), "Retrieve", "CurrencyParams", "000.000.00.00", "Error", GlobalVars.EXCEPTIONSISSUE, "Retrieve CurrencyParams", "An error occurred while retrieving CurrencyParams", new Date());
            userActivityTrackingRepository.save(userTrace);

            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE, "some issues in getAllCurrencyParams please check with your provider !");

        }


    }

    public ResponseApiJson<List<CurrencyParam>> getOneCurrencyParam(String id) {
        String idRequest = "MSS_" + new Date().getTime() + (Math.random() * 9999);
        Optional<UserManagement> userM = globalVars.getUser(GlobalVars.getBearerTokenHeader());
        try {
            Optional<CurrencyParam> currencyParam = currencyRepository.findById(id);
            List<CurrencyParam> currencyParams = new ArrayList<>();
            currencyParam.ifPresent(currencyParams::add);

            // Track user activity
            UserActivityTracking userTrace = new UserActivityTracking(userM.get().getUserCode(), "Retrieve", "CurrencyParam", "000.000.00.00", "Success", GlobalVars.SUCCESS, "Retrieve CurrencyParam", "CurrencyParam retrieved successfully", new Date());
            userActivityTrackingRepository.save(userTrace);

            return new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS , "Get one currencyParam succesfully  !" , currencyParams);
        } catch (Exception e)   {
            logger.info("Exception of  getOneCurrencyParam " + e.getMessage());

            // Track user activity for exception
            UserActivityTracking userTrace = new UserActivityTracking(userM.get().getUserCode(), "Retrieve", "CurrencyParam", "000.000.00.00", "Error", GlobalVars.EXCEPTIONSISSUE, "Retrieve CurrencyParam", "An error occurred while retrieving CurrencyParam", new Date());
            userActivityTrackingRepository.save(userTrace);

            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE, " some issues in getOneCurrencyParam please check with your provider !");

        }
    }

    public ResponseApiJson<?> addCurrencyParam(CurrencyParam currencyParam){
        String idRequest = "AM_"  + new  Date().getTime() + (Math.random() * 9999);
        Optional<UserManagement> userM = globalVars.getUser(GlobalVars.getBearerTokenHeader());
        logger.info(currencyParam.toString());
        try {
            Optional<CurrencyParam> currencyParamtoCheck = currencyRepository.findById(currencyParam.getId());
            if ( currencyParamtoCheck.isEmpty()) {
                currencyRepository.save(currencyParam);
                // user Activity tracking Hamza 29.02.2024 start
                UserActivityTracking userTrace = new UserActivityTracking(userM.get().getUserCode(),"Add", "currency_param","000.000.00.00", "Success", GlobalVars.SUCCESS,"Add Currency","Add Currency with id : "+currencyParam.getId()+" successfull",new Date());
                userActivityTrackingRepository.save(userTrace);
                // user Activity tracking Hamza 29.02.2024 end
                return new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS, "Insertde currencyParam sucessfully  !");
            }else {
                // user Activity tracking Hamza 29.02.2024 start
                UserActivityTracking userTrace = new UserActivityTracking(userM.get().getUserCode(),"Add", "currency_param","000.000.00.00", "Failed", GlobalVars.ALREADYEXIST,"Add Currency","Currency with id : "+currencyParam.getId()+" already exist",new Date());
                userActivityTrackingRepository.save(userTrace);
                // user Activity tracking Hamza 29.02.2024 end
                return  new ResponseApiJson<>(idRequest , GlobalVars.ALREADYEXIST , "this CurrencyParam already exist !");

            }
        } catch (Exception e ){
            logger.info("Exception of getOneCurrencyParam "  + e.getMessage());

            // user Activity tracking Hamza 29.02.2024 start
            UserActivityTracking userTrace = new UserActivityTracking(userM.get().getUserCode(),"Add", "currency_param","000.000.00.00", "Failed", GlobalVars.EXCEPTIONSISSUE,"Add Currency","Some issues in addCurrencyParam",new Date());
            userActivityTrackingRepository.save(userTrace);
            // user Activity tracking Hamza 29.02.2024 end
            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE, "Some issues in addCurrencyParam please check with your provider !");
        }
    }

    public  ResponseApiJson<?> updateCurrencyParam(CurrencyParam currencyParam) throws IllegalAccessException {
        String  idRequest = "UMS_"  + new Date().getTime() + (Math.random() * 9999);
        Optional<UserManagement> userM = globalVars.getUser(GlobalVars.getBearerTokenHeader());
        try{
            Optional<CurrencyParam> currencyParamto = currencyRepository.findById(currencyParam.getId());
            GlobalVars globalVars = new GlobalVars();
            if (currencyParamto.isPresent()){
                CurrencyParam currencyParam1 = (CurrencyParam)  globalVars.construct(CurrencyParam.class, currencyParam , currencyParamto.get());
                logger.info("CurrencyParamto " + currencyParam1.toString());
                currencyRepository.save(currencyParam1);
                // user Activity tracking Hamza 29.02.2024 start
                UserActivityTracking userTrace = new UserActivityTracking(userM.get().getUserCode(),"Update", "currency_param","000.000.00.00", "Success", GlobalVars.SUCCESS,"Update Currency","Update Currency with id : "+currencyParam.getId()+" successfull",new Date());
                userActivityTrackingRepository.save(userTrace);
                // user Activity tracking Hamza 29.02.2024 end
                return new ResponseApiJson<>(idRequest , GlobalVars.SUCCESS , "Update  currencyParam  sucessfuly  !");

            } else {
                // user Activity tracking Hamza 29.02.2024 start
                UserActivityTracking userTrace = new UserActivityTracking(userM.get().getUserCode(),"Update", "currency_param","000.000.00.00", "Failed", GlobalVars.NOTEXIST,"Update Currency","Currency with id : "+currencyParam.getId()+" does not exit !",new Date());
                userActivityTrackingRepository.save(userTrace);
                // user Activity tracking Hamza 29.02.2024 end
                return new ResponseApiJson<>(idRequest , GlobalVars.NOTEXIST , "this currencyParam  does not exit   !");

            }
        } catch (Exception e) {
            logger.info("Exception of getOneCurrencyParam "  + e.getMessage());
            // user Activity tracking Hamza 29.02.2024 start
            UserActivityTracking userTrace = new UserActivityTracking(userM.get().getUserCode(),"Update", "currency_param","000.000.00.00", "Failed", GlobalVars.EXCEPTIONSISSUE,"Update Currency","Some issues in updateCurrencyService please check with your provider !",new Date());
            userActivityTrackingRepository.save(userTrace);
            // user Activity tracking Hamza 29.02.2024 end
            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE , "Some issues in updateCurrencyService please check with your provider !");


        }
    }

    public  ResponseApiJson<?> deleteCurrencyParam(String id){
        String  idRequest = "DM_" + new Date().getTime() + (Math.random() * 9999);
        Optional<UserManagement> userM = globalVars.getUser(GlobalVars.getBearerTokenHeader());
        try {
            Optional<CurrencyParam> currencyParam =currencyRepository.findById(id);
            currencyParam.ifPresent(currencyRepository ::delete);
            // user Activity tracking Hamza 29.02.2024 start
            UserActivityTracking userTrace = new UserActivityTracking(userM.get().getUserCode(),"Delete", "currency_param","000.000.00.00", "Success", GlobalVars.SUCCESS,"Delete Currency","Delete Currency with id : "+id+" successfull",new Date());
            userActivityTrackingRepository.save(userTrace);
            // user Activity tracking Hamza 29.02.2024 end
            return  new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS , "Delete currencyParam succesfully !");
        }catch (Exception e){
            logger.info("Delete currencyParam Exception: " +e.getMessage());
            // user Activity tracking Hamza 29.02.2024 start
            UserActivityTracking userTrace = new UserActivityTracking(userM.get().getUserCode(),"Delete", "currency_param","000.000.00.00", "Failed", GlobalVars.EXCEPTIONSISSUE,"Delete Currency","deleteCurrencyParam terminated with issue",new Date());
            userActivityTrackingRepository.save(userTrace);
            // user Activity tracking Hamza 29.02.2024 end
            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE ,"deleteCurrencyParam terminated with issue");
        }
    }

}





