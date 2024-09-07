package com.simulator.iso.services;

import com.simulator.config.GlobalVars;
import com.simulator.entities.CountryParam;
import com.simulator.globalModels.ResponseApiJson;
import com.simulator.repository.CountryRepository;
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



    public CountryParam getCountryParam(String id) {
        return countryRepository.findById(id).orElse(null);
    }


   /* public List<CountryParam> getFilteredMtiList(String bankCode, String mtiProtocol) {
        return mtiRepository.findByBankCodeAndMtiProtocole(bankCode, mtiProtocol);
    }
*/



    public ResponseApiJson<List<CountryParam>> getAllCountryParams() {
        String idRequest = "MS_" + new Date().getTime() + (Math.random() * 9999);

        try {
            List<CountryParam> countryParams = countryRepository.findAll();
            return new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS, "Get all country succesfully  !", countryParams);

        } catch (Exception e) {
            logger.info("Exception of this::getAllCountryParams " + e.getMessage());
            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE, "some issues in getAllCountryParams please check with your provider !");

        }


    }

    public ResponseApiJson<List<CountryParam>> getOneCountryParam(String id) {
        String idRequest = "MSS_" + new Date().getTime() + (Math.random() * 9999);
        try {
            Optional<CountryParam> countryParam = countryRepository.findById(id);
            List<CountryParam> countryParams = new ArrayList<>();
            countryParam.ifPresent(countryParams::add);
            return new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS , "Get one countryParam succesfully  !" , countryParams);
        } catch (Exception e)   {
            logger.info("Exception of  getOneCountryParam " + e.getMessage());
            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE , " some issues in getOneCountryParam please check with your provider !");

        }
    }

    public ResponseApiJson<?> addCountryParam(CountryParam countryParam){
        String idRequest = "AM_"  + new  Date().getTime() + (Math.random() * 9999);
        logger.info(countryParam.toString());
        try {
            Optional<CountryParam> countryParamtoCheck = countryRepository.findById(countryParam.getId());
            if ( countryParamtoCheck.isEmpty()) {
                countryRepository.save(countryParam);
                return new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS, "Insertde countryParam sucessfully  !");
            }else {
                return  new ResponseApiJson<>(idRequest , GlobalVars.ALREADYEXIST , "this CountryParam already exist !");

            }
        } catch (Exception e ){
            logger.info("Exception of getOneCountryParam "  + e.getMessage());
            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE , "Some issues in addCountryParam please check with your provider !");
        }
    }

    public  ResponseApiJson<?> updateCountryParam(CountryParam countryParam) throws IllegalAccessException {
        String  idRequest = "UMS_"  + new Date().getTime() + (Math.random() * 9999);
        try{
            Optional<CountryParam> countryParamto = countryRepository.findById(countryParam.getId());
            GlobalVars globalVars = new GlobalVars();
            if (countryParamto.isPresent()){
                CountryParam countryParam1 = (CountryParam)  globalVars.construct(CountryParam.class, countryParam , countryParamto.get());
                logger.info("countryParamto " + countryParam1.toString());
                countryRepository.save(countryParam1);
                return new ResponseApiJson<>(idRequest , GlobalVars.SUCCESS , "Update  countryParam  sucessfuly  !");

            } else {
                return new ResponseApiJson<>(idRequest , GlobalVars.NOTEXIST , "this countryParam  does not exit   !");

            }
        } catch (Exception e) {
            logger.info("Exception of getOneCountryParam "  + e.getMessage());
            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE , "Some issues in updateCountryService please check with your provider !");


        }
    }

    public  ResponseApiJson<?> deleteCountryParam(String id){
        String  idRequest = "DM_" + new Date().getTime() + (Math.random() * 9999);
        try {
            Optional<CountryParam> countryParam = countryRepository.findById(id);
            countryParam.ifPresent(countryRepository ::delete);
            return  new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS , "Delete countryParam succesfully !");
        }catch (Exception e){
            logger.info("Delete countryParam Exception: " +e.getMessage());
            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE ,"deleteCountryParam terminated with issue");
        }
    }

}






