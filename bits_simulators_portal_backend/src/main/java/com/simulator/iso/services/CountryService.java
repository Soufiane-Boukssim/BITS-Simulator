package com.simulator.iso.services;

import com.simulator.config.GlobalVars;
import com.simulator.entities.CountryParam;
import com.simulator.entities.UserActivityTracking;
import com.simulator.entities.UserManagement;
import com.simulator.globalModels.ResponseApiJson;
import com.simulator.repository.CountryRepository;
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
public  class CountryService {
    private final Logger logger = LogManager.getLogger(CountryService.class);
    @Autowired
    CountryRepository countryRepository;

    @Autowired
    UserActivityTrackingRepository userActivityTrackingRepository;

    @Autowired
    GlobalVars globalVars;


    public ResponseApiJson<List<CountryParam>> getAllCountryParams() {
        String idRequest = "MS_" + new Date().getTime() + (Math.random() * 9999);
        Optional<UserManagement> userM = globalVars.getUser(GlobalVars.getBearerTokenHeader());
        try {
            List<CountryParam> countryParams = countryRepository.findAll();
            // Track user activity for exception
            UserActivityTracking userTrace = new UserActivityTracking(userM.get().getUserCode(), "Retrieve", "CountryParams", "000.000.00.00", "Success", GlobalVars.SUCCESS, "Retrieve CountryParams", "CountryParams retrieved successfully", new Date());
            userActivityTrackingRepository.save(userTrace);

            return new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS, "Get all country succesfully  !", countryParams);

        } catch (Exception e) {
            logger.info("Exception of this::getAllCountryParams " + e.getMessage());
            // Track user activity for exception
            UserActivityTracking userTrace = new UserActivityTracking(userM.get().getUserCode(), "Retrieve", "CountryParams", "000.000.00.00", "Error", GlobalVars.EXCEPTIONSISSUE, "Retrieve CountryParams", "An error occurred while retrieving CountryParams", new Date());
            userActivityTrackingRepository.save(userTrace);


            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE, "some issues in getAllCountryParams please check with your provider !");

        }
    }

    public ResponseApiJson<List<CountryParam>> getOneCountryParam(String id) {
        String idRequest = "MSS_" + new Date().getTime() + (Math.random() * 9999);
        Optional<UserManagement> userM = globalVars.getUser(GlobalVars.getBearerTokenHeader());
        try {
            Optional<CountryParam> countryParam = countryRepository.findById(id);
            List<CountryParam> countryParams = new ArrayList<>();
            countryParam.ifPresent(countryParams::add);

            // Track user activity for exception
            UserActivityTracking userTrace = new UserActivityTracking(userM.get().getUserCode(), "Retrieve", "CountryParam", "000.000.00.00", "Success", GlobalVars.SUCCESS, "Retrieve CountryParam", "CountryParam retrieved successfully", new Date());
            userActivityTrackingRepository.save(userTrace);

            return new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS , "Get one countryParam succesfully  !" , countryParams);
        } catch (Exception e)   {
            logger.info("Exception of  getOneCountryParam " + e.getMessage());

            // Track user activity for exception
            UserActivityTracking userTrace = new UserActivityTracking(userM.get().getUserCode(), "Retrieve", "CountryParam", "000.000.00.00", "Error", GlobalVars.EXCEPTIONSISSUE, "Retrieve CountryParam", "An error occurred while retrieving CountryParam", new Date());
            userActivityTrackingRepository.save(userTrace);

            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE , " some issues in getOneCountryParam please check with your provider !");

        }
    }

    public ResponseApiJson<?> addCountryParam(CountryParam countryParam){
        String idRequest = "AM_"  + new  Date().getTime() + (Math.random() * 9999);
        Optional<UserManagement> userM = globalVars.getUser(GlobalVars.getBearerTokenHeader());
        logger.info(countryParam.toString());
        try {
            Optional<CountryParam> countryParamtoCheck = countryRepository.findById(countryParam.getId());
            if ( countryParamtoCheck.isEmpty()) {
                countryRepository.save(countryParam);
                // user Activity tracking Hamza 29.02.2024 start
                UserActivityTracking userTrace = new UserActivityTracking(userM.get().getUserCode(),"Add", "country_param","000.000.00.00", "Success", GlobalVars.SUCCESS,"Add Country","Add Country with id : "+countryParam.getId()+" successfull",new Date());
                userActivityTrackingRepository.save(userTrace);
                // user Activity tracking Hamza 29.02.2024 end
                return new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS, "Insertde countryParam sucessfully  !");
            }else {
                // user Activity tracking Hamza 29.02.2024 start
                UserActivityTracking userTrace = new UserActivityTracking(userM.get().getUserCode(),"Add", "country_param","000.000.00.00", "Failed", GlobalVars.ALREADYEXIST,"Add Country","Country with id : "+countryParam.getId()+" already exist",new Date());
                userActivityTrackingRepository.save(userTrace);
                // user Activity tracking Hamza 29.02.2024 end
                return  new ResponseApiJson<>(idRequest , GlobalVars.ALREADYEXIST , "this CountryParam already exist !");

            }
        } catch (Exception e ){
            logger.info("Exception of getOneCountryParam "  + e.getMessage());
            // user Activity tracking Hamza 29.02.2024 start
            UserActivityTracking userTrace = new UserActivityTracking(userM.get().getUserCode(),"Add", "country_param","000.000.00.00", "Failed", GlobalVars.EXCEPTIONSISSUE,"Add Country","Some issues in addCountryParam please check with your provider !",new Date());
            userActivityTrackingRepository.save(userTrace);
            // user Activity tracking Hamza 29.02.2024 end
            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE , "Some issues in addCountryParam please check with your provider !");
        }
    }

    public  ResponseApiJson<?> updateCountryParam(CountryParam countryParam) throws IllegalAccessException {
        String  idRequest = "UMS_"  + new Date().getTime() + (Math.random() * 9999);
        Optional<UserManagement> userM = globalVars.getUser(GlobalVars.getBearerTokenHeader());
        try{
            Optional<CountryParam> countryParamto = countryRepository.findById(countryParam.getId());
            GlobalVars globalVars = new GlobalVars();
            if (countryParamto.isPresent()){
                CountryParam countryParam1 = (CountryParam)  globalVars.construct(CountryParam.class, countryParam , countryParamto.get());
                logger.info("countryParamto " + countryParam1.toString());
                countryRepository.save(countryParam1);
                // user Activity tracking Hamza 29.02.2024 start
                UserActivityTracking userTrace = new UserActivityTracking(userM.get().getUserCode(),"Update", "country_param","000.000.00.00", "Success", GlobalVars.SUCCESS,"Update Country","Update country with id : "+countryParam.getId()+" successfull!",new Date());
                userActivityTrackingRepository.save(userTrace);
                // user Activity tracking Hamza 29.02.2024 end
                return new ResponseApiJson<>(idRequest , GlobalVars.SUCCESS , "Update  countryParam  sucessfuly  !");

            } else {
                // user Activity tracking Hamza 29.02.2024 start
                UserActivityTracking userTrace = new UserActivityTracking(userM.get().getUserCode(),"Update", "country_param","000.000.00.00", "Failed", GlobalVars.NOTEXIST,"Update Country","country with id : "+countryParam.getId()+" does not exit !",new Date());
                userActivityTrackingRepository.save(userTrace);
                // user Activity tracking Hamza 29.02.2024 end
                return new ResponseApiJson<>(idRequest , GlobalVars.NOTEXIST , "this countryParam  does not exit   !");

            }
        } catch (Exception e) {
            logger.info("Exception of getOneCountryParam "  + e.getMessage());
            // user Activity tracking Hamza 29.02.2024 start
            UserActivityTracking userTrace = new UserActivityTracking(userM.get().getUserCode(),"Update", "country_param","000.000.00.00", "Failed", GlobalVars.EXCEPTIONSISSUE,"Update Country","Update country with id : "+countryParam.getId()+" terminated with issue !",new Date());
            userActivityTrackingRepository.save(userTrace);
            // user Activity tracking Hamza 29.02.2024 end
            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE , "Some issues in updateCountryService please check with your provider !");


        }
    }

    public  ResponseApiJson<?> deleteCountryParam(String id){
        String  idRequest = "DM_" + new Date().getTime() + (Math.random() * 9999);
        Optional<UserManagement> userM = globalVars.getUser(GlobalVars.getBearerTokenHeader());
        try {
            Optional<CountryParam> countryParam = countryRepository.findById(id);
            countryParam.ifPresent(countryRepository ::delete);
            // user Activity tracking Hamza 29.02.2024 start
            UserActivityTracking userTrace = new UserActivityTracking(userM.get().getUserCode(),"Delete", "country_param","000.000.00.00", "Success", GlobalVars.SUCCESS,"Delete Country","Delete country with id : "+id+" succesfully !",new Date());
            userActivityTrackingRepository.save(userTrace);
            // user Activity tracking Hamza 29.02.2024 end
            return  new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS , "Delete countryParam succesfully !");
        }catch (Exception e){
            logger.info("Delete countryParam Exception: " +e.getMessage());
            // user Activity tracking Hamza 29.02.2024 start
            UserActivityTracking userTrace = new UserActivityTracking(userM.get().getUserCode(),"Delete", "country_param","000.000.00.00", "Failed", GlobalVars.EXCEPTIONSISSUE,"Delete Country","Delete country with id : "+id+" terminated with issue !",new Date());
            userActivityTrackingRepository.save(userTrace);
            // user Activity tracking Hamza 29.02.2024 end
            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE ,"deleteCountryParam terminated with issue");
        }
    }

}






