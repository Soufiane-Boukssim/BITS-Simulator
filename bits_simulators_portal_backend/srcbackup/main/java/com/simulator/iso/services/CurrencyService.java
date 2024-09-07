package com.simulator.iso.services;

import com.simulator.config.GlobalVars;
import com.simulator.entities.CurrencyParam;
import com.simulator.globalModels.ResponseApiJson;
import com.simulator.repository.CurrencyRepository;
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

  /*  public List<MtiDef> getFilteredMtiList(String bankCode, String mtiProtocol) {
        return mtiRepository.findByBankCodeAndMtiProtocole(bankCode, mtiProtocol);
    }*/




    public ResponseApiJson<List<CurrencyParam>> getAllCurrencyParams() {
        String idRequest = "MS_" + new Date().getTime() + (Math.random() * 9999);

        try {
            List<CurrencyParam> currencyParams = currencyRepository.findAll();
            return new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS, "Get all currency succesfully  !", currencyParams);

        } catch (Exception e) {
            logger.info("Exception of this::getAllCurrencyParams " + e.getMessage());
            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE, "some issues in getAllCurrencyParams please check with your provider !");

        }


    }

    public ResponseApiJson<List<CurrencyParam>> getOneCurrencyParam(String id) {
        String idRequest = "MSS_" + new Date().getTime() + (Math.random() * 9999);
        try {
            Optional<CurrencyParam> currencyParam = currencyRepository.findById(id);
            List<CurrencyParam> currencyParams = new ArrayList<>();
            currencyParam.ifPresent(currencyParams::add);
            return new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS , "Get one currencyParam succesfully  !" , currencyParams);
        } catch (Exception e)   {
            logger.info("Exception of  getOneCurrencyParam " + e.getMessage());
            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE, " some issues in getOneCurrencyParam please check with your provider !");

        }
    }

    public ResponseApiJson<?> addCurrencyParam(CurrencyParam currencyParam){
        String idRequest = "AM_"  + new  Date().getTime() + (Math.random() * 9999);
        logger.info(currencyParam.toString());
        try {
            Optional<CurrencyParam> currencyParamtoCheck = currencyRepository.findById(currencyParam.getId());
            if ( currencyParamtoCheck.isEmpty()) {
                currencyRepository.save(currencyParam);
                return new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS, "Insertde currencyParam sucessfully  !");
            }else {
                return  new ResponseApiJson<>(idRequest , GlobalVars.ALREADYEXIST , "this CurrencyParam already exist !");

            }
        } catch (Exception e ){
            logger.info("Exception of getOneCurrencyParam "  + e.getMessage());
            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE, "Some issues in addCurrencyParam please check with your provider !");
        }
    }

    public  ResponseApiJson<?> updateCurrencyParam(CurrencyParam currencyParam) throws IllegalAccessException {
        String  idRequest = "UMS_"  + new Date().getTime() + (Math.random() * 9999);
        try{
            Optional<CurrencyParam> currencyParamto = currencyRepository.findById(currencyParam.getId());
            GlobalVars globalVars = new GlobalVars();
            if (currencyParamto.isPresent()){
                CurrencyParam currencyParam1 = (CurrencyParam)  globalVars.construct(CurrencyParam.class, currencyParam , currencyParamto.get());
                logger.info("CurrencyParamto " + currencyParam1.toString());
                currencyRepository.save(currencyParam1);
                return new ResponseApiJson<>(idRequest , GlobalVars.SUCCESS , "Update  currencyParam  sucessfuly  !");

            } else {
                return new ResponseApiJson<>(idRequest , GlobalVars.NOTEXIST , "this currencyParam  does not exit   !");

            }
        } catch (Exception e) {
            logger.info("Exception of getOneCurrencyParam "  + e.getMessage());
            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE , "Some issues in updateCurrencyService please check with your provider !");


        }
    }

    public  ResponseApiJson<?> deleteCurrencyParam(String id){
        String  idRequest = "DM_" + new Date().getTime() + (Math.random() * 9999);
        try {
            Optional<CurrencyParam> currencyParam =currencyRepository.findById(id);
            currencyParam.ifPresent(currencyRepository ::delete);
            return  new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS , "Delete currencyParam succesfully !");
        }catch (Exception e){
            logger.info("Delete currencyParam Exception: " +e.getMessage());
            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE ,"deleteCurrencyParam terminated with issue");
        }
    }

}





