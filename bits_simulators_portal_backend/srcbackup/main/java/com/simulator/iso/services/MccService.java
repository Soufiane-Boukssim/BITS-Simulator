package com.simulator.iso.services;

import com.simulator.config.GlobalVars;
import com.simulator.entities.MccDef;
import com.simulator.entities.*;
import com.simulator.globalModels.ResponseApiJson;
import com.simulator.repository.MccRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@Service
public class MccService {
    private final Logger logger = LogManager.getLogger(MccService.class);

    @Autowired
    MccRepository mccRepository;



    public MccDef getMccDef(MccDefId id) {
        return mccRepository.findById(id).orElse(null);
    }


    public List<MccDef> getFilteredMccList(String bankCode, Character mccProtocol) {
        return mccRepository.findByBankCodeAndMccProtocol(bankCode, mccProtocol);
    }

    public ResponseApiJson<List<MccDef>> getAllMccDefs() {
        String idRequest = "MS_" + new Date().getTime() + (Math.random() * 9999);
        try {
            List<MccDef> mccDefs = mccRepository.findAll();
            return new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS, " get All mcc succesfully  !", mccDefs);

        } catch (Exception e) {
            logger.info("Exceception of this :: getAllMccDefs " + e.getMessage());
            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE, "some issues in getAllMccDefs please check with your providers !");
        }
    }

    public ResponseApiJson<List<MccDef>> getOneMccDef(MccDefId id) {
        String idRequest = "MCCS_" + new Date().getTime() + (Math.random() * 9999);
        try {
            Optional<MccDef> mccDef = mccRepository.findById(id);
            List<MccDef> mccDefs = new ArrayList<>();
            mccDef.ifPresent(mccDefs::add);
            return new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS, " Get one mccDef successfully !", mccDefs);
        } catch (Exception e) {
            logger.info(("Exception of getOneMccDef" + e.getMessage()));
            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE, "some issues in getOneMccDef sucessfully !");
        }
    }

    public ResponseApiJson<?> addMccDef(MccDef mccDef) {
        String idRequest = "AMCS_" + new Date().getTime() + (Math.random() * 9999);
        logger.info(mccDef.toString());
        try {
            Optional<MccDef> mccDefOptional = mccRepository.findById(mccDef.getId());
            if (mccDefOptional.isEmpty()) {
                mccRepository.save(mccDef);
                return new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS, " Insert de MccDEf succesfully");
            } else {
                return new ResponseApiJson<>(idRequest, GlobalVars.ALREADYEXIST, "this MccDef already exist !");
            }
        } catch (Exception e) {
            logger.info("Exception of getOneMccDef " + e.getMessage());
            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE, " Some issues in addMccDef please check provider !");
        }
    }
   /*public MccDef addMccDef(MccDef mccDef){
       String idRequest = "AICC_"  + new  Date().getTime() + (Math.random() * 9999);
       logger.info(mccDef.toString());
       Optional<MccDef> mccDefOptional = mccRepository.findById(mccDef.getId());
       if ( mccDefOptional.isEmpty()) {
           return mccRepository.save(mccDef);

       } else {
           return null;

       }*/




    public ResponseApiJson<?> updateMccDef(MccDef mccDef) throws IllegalAccessException {
        String idRequest = "UMCC_" + new Date().getTime() + (Math.random() * 9999);
        try {
            Optional<MccDef> mccDefOptional1 = mccRepository.findById(mccDef.getId());
            GlobalVars globalVars = new GlobalVars();
            if (mccDefOptional1.isPresent()) {
                MccDef mccDef1 = (MccDef) globalVars.construct(MccDef.class, mccDef, mccDefOptional1.get());
                logger.info("mccDefOptional1 " + mccDef1.toString());
                mccRepository.save(mccDef1);
                return new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS, "Update  mccDef  sucessfuly  !");
            } else {
                return new ResponseApiJson<>(idRequest, GlobalVars.NOTEXIST, "this MccDef  does not exit !");
            }

        } catch (Exception e) {
            logger.info("Exception of getOneMccDef " + e.getMessage());
            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE, "Some issues in updateMccDEfService please check with your provider !");
        }
    }
    public ResponseApiJson<?> deleteMccDef(MccDefId id){
        String idRequest = "DMCC_" + new  Date().getTime() + (Math.random() * 9999);
        try {
            Optional<MccDef> mccDef = mccRepository.findById(id);
            mccDef.ifPresent(mccRepository :: delete);
            return new ResponseApiJson<>(idRequest , GlobalVars.SUCCESS ,"Delete mccDef successfully");
        }catch ( Exception e ) {
            logger.info("Delete mccDef Exception  " + e.getMessage());
            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE,"Delete MccDef terminated with issue");
        }
    }
}


