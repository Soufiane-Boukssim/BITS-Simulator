package com.simulator.pos.services;

import com.simulator.config.GlobalVars;
import com.simulator.entities.CardProfile;
import com.simulator.entities.pos.PosCardProfile;
import com.simulator.entities.pos.PosCardProfileId;
import com.simulator.entities.pos.PosIccProfile;
import com.simulator.globalModels.ResponseApiJson;
import com.simulator.iso.services.CardService;
import com.simulator.repository.pos.PosCardRepository;
import com.simulator.repository.pos.PosIccRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@Service
public  class PosCardService {
    private final Logger logger = LogManager.getLogger(CardService.class);
    @Autowired
    PosCardRepository cardRepository;
    @Autowired
    PosIccRepository iccRepository;




    public ResponseApiJson<List<PosCardProfile>> getAllCardProfiles(PosCardProfileId cardProfileId) {
        String idRequest = "MS_" + new Date().getTime() + (Math.random() * 9999);
        try {
            List<PosCardProfile> cardProfiles = cardRepository.findByBankCodeAndCardProtocol(cardProfileId.getBankCode(), cardProfileId.getCardProtocol());

            return new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS, "Get all cardProfilesuccesfully  !", cardProfiles);

        } catch (Exception e) {
            logger.info("Exception of this::getAllCardProfiles " + e.getMessage());
            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE, "some issues in getAllCardProfiles please check with your provider !");

        }

    }

    public ResponseApiJson<List<PosCardProfile>> getOneCardProfile(PosCardProfileId id) {
        String idRequest = "CPSS_" + new Date().getTime() + (Math.random() * 9999);
        try {
            Optional<PosCardProfile> cardProfile = cardRepository.findById(id);
            List<PosCardProfile> cardProfiles = new ArrayList<>();
            cardProfile.ifPresent(cardProfiles::add);
            return new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS , "Get one cardProfile succesfully  !" , cardProfiles);
        } catch (Exception e)   {
            logger.info("Exception of  getOneCardProfile " + e.getMessage());
            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE , " some issues in getOneCardProfile please check with your provider !");

        }
    }

    public ResponseApiJson<?> addCardProfile(PosCardProfile cardProfile,List<PosIccProfile> iccProfiles){
        String idRequest = "AM_"  + new  Date().getTime() + (Math.random() * 9999);
        logger.info(cardProfile.toString());
        try {
            Optional<PosCardProfile> cardProfiletoCheck = cardRepository.findById(cardProfile.getId());
            if ( cardProfiletoCheck.isEmpty()) {
                cardRepository.save(cardProfile);

                for (PosIccProfile iccProfile :
                        iccProfiles) {

                    iccRepository.save(iccProfile);

                }
                return new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS, "Insertde cardProfile sucessfully  !");
            }else {
                return  new ResponseApiJson<>(idRequest , GlobalVars.ALREADYEXIST , "this CardProfile already exist !");

            }
        } catch (Exception e ){
            logger.info("Exception of getOneCardProfile "  + e.getMessage());
            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE , "Some issues in addCardProfile please check with your provider !");
        }
    }

    public  ResponseApiJson<?> updateCardProfile(PosCardProfile cardProfile ,List<PosIccProfile> iccProfiles) throws IllegalAccessException {
        String  idRequest = "UMS_"  + new Date().getTime() + (Math.random() * 9999);
        try{
            Optional<PosCardProfile> cardProfileto = cardRepository.findById(cardProfile.getId());
            GlobalVars globalVars = new GlobalVars();
            if (cardProfileto.isPresent()){
                PosCardProfile cardProfile1 = (PosCardProfile)  globalVars.construct(CardProfile.class, cardProfile , cardProfileto.get());
                logger.info("cardProfileto " + cardProfile1.toString());
                cardRepository.save(cardProfile1);
                for (PosIccProfile iccProfile :
                        iccProfiles) {

                    iccRepository.save(iccProfile);

                }
                return new ResponseApiJson<>(idRequest , GlobalVars.SUCCESS , "Update  cardProfile  sucessfuly  !");

            } else {
                return new ResponseApiJson<>(idRequest , GlobalVars.NOTEXIST , "this CardProfile  does not exit   !");

            }
        } catch (Exception e) {
            logger.info("Exception of getOneCardProfile "  + e.getMessage());
            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE , "Some issues in updateCardService please check with your provider !");


        }
    }

    public  ResponseApiJson<?> deleteCardProfile(PosCardProfileId id){
        String  idRequest = "DM_" + new Date().getTime() + (Math.random() * 9999);
        try {
            Optional<PosCardProfile> cardProfile = cardRepository.findById(id);
            cardProfile.ifPresent(cardRepository ::delete);
            return  new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS , "Delete cardProfile successfully !");
        }catch (Exception e){
            logger.info("Delete cardProfile Exception: " +e.getMessage());
            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE ,"deleteCardProfile terminated with issue");
        }
    }
}





