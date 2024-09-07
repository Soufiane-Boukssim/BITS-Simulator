package com.simulator.pos.services;

import com.simulator.config.GlobalVars;
import com.simulator.entities.pos.PosMtiDef;
import com.simulator.entities.pos.PosMtiDefId;
import com.simulator.globalModels.ResponseApiJson;
import com.simulator.repository.pos.PosMtiRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@Service
public  class PosMtiService {
    private final Logger logger = LogManager.getLogger(PosMtiService.class);
    @Autowired
    PosMtiRepository mtiRepository;






/*
    public PosMtiDef getMtiDef(PosMtiDefId id) {
        return mtiRepository.findById(id).orElse(null);
    }
*/

/*

    public List<PosMtiDef> getFilteredMtiList(String bankCode, String mtiProtocol) {
        return mtiRepository.findByBankCodeAndMtiProtocole(bankCode, mtiProtocol);
    }
*/




  /*  public ResponseApiJson<List<PosMtiDef>> getAllMtiDefs() {
        String idRequest = "MS_" + new Date().getTime() + (Math.random() * 9999);

        try {
            List<PosMtiDef> mtiDefs = mtiRepository.findAll();
            return new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS, "Get all mti succesfully  !", mtiDefs);

        } catch (Exception e) {
            logger.info("Exception of this::getAllMtiDefs " + e.getMessage());
            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE, "some issues in getAllMtiDefs please check with your provider !");

        }


    }
*/




    public ResponseApiJson<List<PosMtiDef>> getAllMtiDefs(PosMtiDefId mtiDefId) {
        String idRequest = "MS_" + new Date().getTime() + (Math.random() * 9999);
        try {
            List<PosMtiDef> mtiDefs = mtiRepository.findByBankCodeAndMtiProtocole(mtiDefId.getBankCode(),mtiDefId.getMtiProtocol());

                return new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS, "Get all PosMtiDef succesfully  !", mtiDefs);

        } catch (Exception e) {
            logger.info("Exception of this::getAllAccountDefs " + e.getMessage());
            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE, "some issues in getAllMtiDefs please check with your provider !");

        }



    }
    public ResponseApiJson<List<PosMtiDef>> getOneMtiDef(PosMtiDefId id) {
        String idRequest = "MSS_" + new Date().getTime() + (Math.random() * 9999);
        try {
            Optional<PosMtiDef> mtiDef = mtiRepository.findById(id);
            List<PosMtiDef> mtiDefs = new ArrayList<>();
                mtiDef.ifPresent(mtiDefs::add);
                return new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS , "Get one mtiDef succesfully  !" , mtiDefs);
            } catch (Exception e)   {
                logger.info("Exception of  getOneMtiDef " + e.getMessage());
                return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE , " some issues in getOneMtiDef please check with your provider !");

            }
        }

        public ResponseApiJson<?> addMtiDef(PosMtiDef mtiDef){
             String idRequest = "AM_"  + new  Date().getTime() + (Math.random() * 9999);
             logger.info(mtiDef.toString());
             try {
                 Optional<PosMtiDef> mtiDeftoCheck = mtiRepository.findById(mtiDef.getId());
                 if ( mtiDeftoCheck.isEmpty()) {
                     mtiRepository.save(mtiDef);
                     return new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS, "Insertde mtiDef sucessfully  !");
                 }else {
                     return  new ResponseApiJson<>(idRequest , GlobalVars.ALREADYEXIST , "this PosMtiDef already exist !");

                 }
             } catch (Exception e ){
                 logger.info("Exception of getOneMtiDef "  + e.getMessage());
                 return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE , "Some issues in addMtiDef please check with your provider !");
             }
        }

        public  ResponseApiJson<?> updateMtiDef(PosMtiDef mtiDef) throws IllegalAccessException {
              String  idRequest = "UMS_"  + new Date().getTime() + (Math.random() * 9999);
              try{
                  Optional<PosMtiDef> mtiDefto = mtiRepository.findById(mtiDef.getId());
                  GlobalVars globalVars = new GlobalVars();
                  if (mtiDefto.isPresent()){
                      PosMtiDef mtiDef1 = (PosMtiDef)  globalVars.construct(PosMtiDef.class, mtiDef , mtiDefto.get());
                      logger.info("mtiDefto " + mtiDef1.toString());
                      mtiRepository.save(mtiDef1);
                      return new ResponseApiJson<>(idRequest , GlobalVars.SUCCESS , "Update  mtiDef  sucessfuly  !");

                  } else {
                      return new ResponseApiJson<>(idRequest , GlobalVars.NOTEXIST , "this mtiDef  does not exit   !");

                  }
              } catch (Exception e) {
                  logger.info("Exception of getOneMtiDef "  + e.getMessage());
                  return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE , "Some issues in updateMtiService please check with your provider !");


              }
        }

        public  ResponseApiJson<?> deleteMtiDef(PosMtiDefId id){
        String  idRequest = "DM_" + new Date().getTime() + (Math.random() * 9999);
        try {
            Optional<PosMtiDef> mtiDef = mtiRepository.findById(id);
            mtiDef.ifPresent(mtiRepository ::delete);
            return  new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS , "Delete mtiDef succesfully !");
        }catch (Exception e){
            logger.info("Delete mtiDef Exception: " +e.getMessage());
            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE ,"deleteMtiDef terminated with issue");
        }
        }

}





