package com.simulator.iso.services;

import com.simulator.config.GlobalVars;
import com.simulator.entities.ExchangeRateParam;
import com.simulator.entities.ExchangeRateParamId;
import com.simulator.globalModels.ResponseApiJson;
import com.simulator.repository.ExchangeRateRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@Service
public  class ExchangeRateService {
    private final Logger logger = LogManager.getLogger(ExchangeRateService.class);
    @Autowired
    ExchangeRateRepository exchangeRateRepository;



    public ExchangeRateParam getExchangeRateParam(ExchangeRateParamId id) {return exchangeRateRepository.findById(id).orElse(null);
    }


    public List<ExchangeRateParam> getFilteredExchangeRateList(String bankCode, String rateProtocol) {
        return exchangeRateRepository.findByBankCodeAndRateProtocole(bankCode, rateProtocol);
    }



    public ResponseApiJson<List<ExchangeRateParam>> getAllExchangeRateParams() {
        String idRequest = "MS_" + new Date().getTime() + (Math.random() * 9999);

        try {
            List<ExchangeRateParam> exchangeRateParams = exchangeRateRepository.findAll();
            return new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS, "Get all ExchangeRate succesfully  !", exchangeRateParams);

        } catch (Exception e) {
            logger.info("Exception of this::getAllExchangeRateParams " + e.getMessage());
            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE, "some issues in getAllExchangeRateParams please check with your provider !");

        }


    }

    public ResponseApiJson<List<ExchangeRateParam>> getOneExchangeRateParam(ExchangeRateParamId id) {
        String idRequest = "MSS_" + new Date().getTime() + (Math.random() * 9999);
        try {
            Optional<ExchangeRateParam> exchangeRateParam = exchangeRateRepository.findById(id);
            List<ExchangeRateParam> exchangeRateParams = new ArrayList<>();
            exchangeRateParam.ifPresent(exchangeRateParams::add);
            return new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS , "Get one ExchangeRate succesfully  !" , exchangeRateParams);
        } catch (Exception e)   {
            logger.info("Exception of  getOneExchangeRateParam" + e.getMessage());
            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE , " some issues in getOneExchangeRateParam please check with your provider !");

        }
    }

    public ResponseApiJson<?> addExchangeRateParam(ExchangeRateParam exchangeRateParam){
        String idRequest = "AM_"  + new  Date().getTime() + (Math.random() * 9999);
        logger.info(exchangeRateParam.toString());
        try {
            Optional<ExchangeRateParam> exchangeRateParamtoCheck = exchangeRateRepository.findById(exchangeRateParam.getId());
            if ( exchangeRateParamtoCheck.isEmpty()) {
                exchangeRateRepository.save(exchangeRateParam);
                return new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS, "Insertde exchangeRateParam sucessfully  !");
            }else {
                return  new ResponseApiJson<>(idRequest , GlobalVars.ALREADYEXIST , "this ExchangeRateParam already exist !");

            }
        } catch (Exception e ){
            logger.info("Exception of getOneExchangeRateParam "  + e.getMessage());
            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE , "Some issues in addExchangeRateParam please check with your provider !");
        }
    }

    public  ResponseApiJson<?> updateExchangeRateParam(ExchangeRateParam exchangeRateParam) throws IllegalAccessException {
        String  idRequest = "UMS_"  + new Date().getTime() + (Math.random() * 9999);
        try{
            Optional<ExchangeRateParam> exchangeRateParamto = exchangeRateRepository.findById(exchangeRateParam.getId());
            GlobalVars globalVars = new GlobalVars();
            if (exchangeRateParamto.isPresent()){
                ExchangeRateParam exchangeRateParam1 = (ExchangeRateParam)  globalVars.construct(ExchangeRateParam.class, exchangeRateParam , exchangeRateParamto.get());
                logger.info("ExchangeRateParamto " + exchangeRateParam1.toString());
                exchangeRateRepository.save(exchangeRateParam1);
                return new ResponseApiJson<>(idRequest , GlobalVars.SUCCESS , "Update  exchangeRateParam  sucessfuly  !");

            } else {
                return new ResponseApiJson<>(idRequest , GlobalVars.NOTEXIST , "this exchangeRateParam  does not exit   !");

            }
        } catch (Exception e) {
            logger.info("Exception of getOneExchangeRateParam "  + e.getMessage());
            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE , "Some issues in updateExchangeRateService please check with your provider !");


        }
    }

    public  ResponseApiJson<?> deleteExchangeRateParam(ExchangeRateParamId id){
        String  idRequest = "DM_" + new Date().getTime() + (Math.random() * 9999);
        try {
            Optional<ExchangeRateParam> exchangeRateParam = exchangeRateRepository.findById(id);
            exchangeRateParam.ifPresent(exchangeRateRepository ::delete);
            return  new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS , "Delete exchangeRateParam succesfully !");
        }catch (Exception e){
            logger.info("Delete exchangeRateParam Exception: " +e.getMessage());
            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE ,"deleteExchangeRateParam terminated with issue");
        }
    }

}






