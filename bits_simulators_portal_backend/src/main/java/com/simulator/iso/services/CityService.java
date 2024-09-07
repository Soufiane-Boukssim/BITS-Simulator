package com.simulator.iso.services;

import com.simulator.config.GlobalVars;
import com.simulator.entities.City;
import com.simulator.entities.UserActivityTracking;
import com.simulator.entities.UserManagement;
import com.simulator.globalModels.ResponseApiJson;
import com.simulator.repository.CityRepository;
import com.simulator.repository.UserActivityTrackingRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CityService {

    private final Logger logger = LogManager.getLogger(CityService.class);

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    UserActivityTrackingRepository userActivityTrackingRepository;

    @Autowired
    GlobalVars globalVars;


    public ResponseApiJson<List<City>> getAllCities() {
        String idRequest = "MS_" + new Date().getTime() + (Math.random() * 9999);

        try {
            List<City> cities = cityRepository.findAll();
            return new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS, "Get all cities succesfully  !", cities);

        } catch (Exception e) {
            logger.info("Exception of this::getAllCities " + e.getMessage());
            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE, "some issues in getAllCities please check with your provider !");

        }
    }


    public ResponseApiJson<?> addCity(City city){
        String idRequest = "AM_"  + new  Date().getTime() + (Math.random() * 9999);
        Optional<UserManagement> userM = globalVars.getUser(GlobalVars.getBearerTokenHeader());
        logger.info(city.toString());
        try {
            Optional<City> cityCheck = cityRepository.findById(city.getCityCode());
            if ( cityCheck.isEmpty()) {
                cityRepository.save(city);
                // user Activity tracking Hamza 29.02.2024 start
                    UserActivityTracking userTrace = new UserActivityTracking(userM.get().getUserCode(),"Add", "City","000.000.00.00", "Success", GlobalVars.SUCCESS,"Add City","City with id : "+city.getCityCode()+" added successfully !",new Date());
                userActivityTrackingRepository.save(userTrace);
                // user Activity tracking Hamza 29.02.2024 end
                return new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS, "Insertde city sucessfully  !");
            }else {
                // user Activity tracking Hamza 29.02.2024 start
                UserActivityTracking userTrace = new UserActivityTracking(userM.get().getUserCode(),"Add", "City","000.000.00.00", "Failed", GlobalVars.ALREADYEXIST,"Add City","City with id : "+city.getCityCode()+" already exists!",new Date());
                userActivityTrackingRepository.save(userTrace);
                // user Activity tracking Hamza 29.02.2024 end
                return  new ResponseApiJson<>(idRequest , GlobalVars.ALREADYEXIST , "this City already exist !");

            }
        } catch (Exception e ){
            logger.info("Exception of addCity "  + e.getMessage());
            // user Activity tracking Hamza 29.02.2024 start
            UserActivityTracking userTrace = new UserActivityTracking(userM.get().getUserCode(),"Add", "City","000.000.00.00", "Failed", GlobalVars.EXCEPTIONSISSUE,"Add City","Some issues in adding city with id : "+city.getCityCode()+" please check with your provider !",new Date());
            userActivityTrackingRepository.save(userTrace);
            // user Activity tracking Hamza 29.02.2024 end
            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE , "Some issues in city please check with your provider !");
        }
    }

    public  ResponseApiJson<?> updateCountryParam(City city) throws IllegalAccessException {
        String  idRequest = "UMS_"  + new Date().getTime() + (Math.random() * 9999);
        Optional<UserManagement> userM = globalVars.getUser(GlobalVars.getBearerTokenHeader());
        try{
            logger.info("City ::> " + city);
            Optional<City> cityto = cityRepository.findById(city.getCityCode());
            GlobalVars globalVars = new GlobalVars();
            if (cityto.isPresent()){
                City city1 = (City)  globalVars.construct(City.class, city , cityto.get());
                logger.info("countryParamto " + city1.toString());
                cityRepository.save(city1);
                // user Activity tracking Hamza 29.02.2024 start
                UserActivityTracking userTrace = new UserActivityTracking(userM.get().getUserCode(),"Update", "City","000.000.00.00", "Success", GlobalVars.SUCCESS,"Update City","city with id : "+city.getCityCode()+" sucessfuly Updated  !",new Date());
                userActivityTrackingRepository.save(userTrace);
                // user Activity tracking Hamza 29.02.2024 end
                return new ResponseApiJson<>(idRequest , GlobalVars.SUCCESS , "Update  city  sucessfuly  !");

            } else {
                // user Activity tracking Hamza 29.02.2024 start
                UserActivityTracking userTrace = new UserActivityTracking(userM.get().getUserCode(),"Update", "City","000.000.00.00", "Failed", GlobalVars.NOTEXIST,"Update City","this city with id : "+city.getCityCode()+" does not exit   !",new Date());
                userActivityTrackingRepository.save(userTrace);
                // user Activity tracking Hamza 29.02.2024 end
                return new ResponseApiJson<>(idRequest , GlobalVars.NOTEXIST , "this city  does not exit   !");

            }
        } catch (Exception e) {
            logger.info("Exception of updateCity "  + e.getMessage());
            // user Activity tracking Hamza 29.02.2024 start
            UserActivityTracking userTrace = new UserActivityTracking(userM.get().getUserCode(),"Update", "City","000.000.00.00", "Failed", GlobalVars.EXCEPTIONSISSUE,"Update City","Some issues in updateCity with id : "+city.getCityCode()+" please check with your provider !",new Date());
            userActivityTrackingRepository.save(userTrace);
            // user Activity tracking Hamza 29.02.2024 end

            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE , "Some issues in updateCity please check with your provider !");


        }
    }

    public  ResponseApiJson<?> deleteCity(String id){
        String  idRequest = "DM_" + new Date().getTime() + (Math.random() * 9999);
        Optional<UserManagement> userM = globalVars.getUser(GlobalVars.getBearerTokenHeader());
        try {
            Optional<City> city = cityRepository.findById(id);
            city.ifPresent(cityRepository ::delete);
            // user Activity tracking Hamza 29.02.2024 start
            UserActivityTracking userTrace = new UserActivityTracking(userM.get().getUserCode(),"Delete", "City","000.000.00.00", "Success", GlobalVars.SUCCESS,"Delete City","Delete city with id : "+id+" succesfully !",new Date());
            userActivityTrackingRepository.save(userTrace);
            // user Activity tracking Hamza 29.02.2024 end
            return  new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS , "Delete city succesfully !");
        }catch (Exception e){
            // user Activity tracking Hamza 29.02.2024 start
            UserActivityTracking userTrace = new UserActivityTracking(userM.get().getUserCode(),"Delete", "City","000.000.00.00", "Failed", GlobalVars.EXCEPTIONSISSUE,"Delete City","deleteCity with id : "+id+", terminated with issue",new Date());
            userActivityTrackingRepository.save(userTrace);
            // user Activity tracking Hamza 29.02.2024 end
            logger.info("Delete city Exception: " +e.getMessage());
            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE ,"deleteCity terminated with issue");
        }
    }
}
