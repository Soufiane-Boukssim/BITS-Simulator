package com.simulator.iso.services;

import com.simulator.config.GlobalVars;
import com.simulator.entities.MerchantParam;
import com.simulator.entities.MerchantParamId;
import com.simulator.globalModels.ResponseApiJson;
import com.simulator.repository.MerchantRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@Service
public  class MerchantService {
    private final Logger logger = LogManager.getLogger(MerchantService.class);
    @Autowired
    MerchantRepository merchantRepository;



    public MerchantParam getMerchantParam(MerchantParamId id) {
        return merchantRepository.findById(id).orElse(null);
    }


public List<MerchantParam> getFilteredMerchantList(String bankCode, Character merProtocol) {
        return merchantRepository.findByBankCodeAndMerProtocole(bankCode, merProtocol);
    }





    public ResponseApiJson<List<MerchantParam>> getAllMerchantParams() {
        String idRequest = "MS_" + new Date().getTime() + (Math.random() * 9999);

        try {
            List<MerchantParam> merchantParams = merchantRepository.findAll();
            return new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS, "Get all merchant succesfully  !", merchantParams);

        } catch (Exception e) {
            logger.info("Exception of this::getAllMerchantParams " + e.getMessage());
            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE, "some issues in getAllMerchantParams please check with your provider !");

        }


    }

    public ResponseApiJson<List<MerchantParam>> getOneMerchantParam(MerchantParamId id) {
        String idRequest = "MSS_" + new Date().getTime() + (Math.random() * 9999);
        try {
            Optional<MerchantParam> merchantParam = merchantRepository.findById(id);
            List<MerchantParam> merchantParams = new ArrayList<>();
            merchantParam.ifPresent(merchantParams::add);
            return new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS , "Get one merchantParam succesfully  !" , merchantParams);
        } catch (Exception e)   {
            logger.info("Exception of  getOneMerchantParam " + e.getMessage());
            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE , " some issues in getOneMerchantParam please check with your provider !");

        }
    }

    public ResponseApiJson<?> addMerchantParam(MerchantParam merchantParam){
        String idRequest = "AM_"  + new  Date().getTime() + (Math.random() * 9999);
        logger.info(merchantParam.toString());
        try {
            Optional<MerchantParam> merchantParamtoCheck = merchantRepository.findById(merchantParam.getId());
            if ( merchantParamtoCheck.isEmpty()) {
                merchantRepository.save(merchantParam);
                return new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS, "Insertde merchantParam sucessfully  !");
            }else {
                return  new ResponseApiJson<>(idRequest , GlobalVars.ALREADYEXIST , "this MerchantParam already exist !");

            }
        } catch (Exception e ){
            logger.info("Exception of getOneMerchantParam "  + e.getMessage());
            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE , "Some issues in addMerchantParam please check with your provider !");
        }
    }

    public  ResponseApiJson<?> updateMerchantParam(MerchantParam merchantParam) throws IllegalAccessException {
        String  idRequest = "UMS_"  + new Date().getTime() + (Math.random() * 9999);
        try{
            Optional<MerchantParam> merchantParamto = merchantRepository.findById(merchantParam.getId());
            GlobalVars globalVars = new GlobalVars();
            if (merchantParamto.isPresent()){
                MerchantParam merchantParam1 = (MerchantParam)  globalVars.construct(MerchantParam.class, merchantParam , merchantParamto.get());
                logger.info("merchantParamto " + merchantParam1.toString());
                merchantRepository.save(merchantParam1);
                return new ResponseApiJson<>(idRequest , GlobalVars.SUCCESS , "Update  merchantParam  sucessfuly  !");

            } else {
                return new ResponseApiJson<>(idRequest , GlobalVars.NOTEXIST , "this merchantParam  does not exit   !");

            }
        } catch (Exception e) {
            logger.info("Exception of getOneMerchantParam "  + e.getMessage());
            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE , "Some issues in updateMerchantService please check with your provider !");


        }
    }

    public  ResponseApiJson<?> deleteMerchantParam(MerchantParamId id){
        String  idRequest = "DM_" + new Date().getTime() + (Math.random() * 9999);
        try {
            Optional<MerchantParam> merchantParam = merchantRepository.findById(id);
            merchantParam.ifPresent(merchantRepository ::delete);
            return  new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS , "Delete merchantParam succesfully !");
        }catch (Exception e){
            logger.info("Delete merchantParam Exception: " +e.getMessage());
            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE ,"deleteMerchantParam terminated with issue");
        }
    }

}





