package com.simulator.atm.services;

import com.simulator.atm.controllers.AtmCardProfileController;
import com.simulator.config.GlobalVars;
import com.simulator.entities.CardProfile;
import com.simulator.entities.CardProfileId;
import com.simulator.globalModels.ResponseApiJson;
import com.simulator.repository.CardRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@Service
public  class AtmCardProfileService {


    private final CardRepository cardRepository;

    private final Logger logger = LogManager.getLogger(AtmCardProfileController.class);


    public AtmCardProfileService(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }


/*
    public <CardProfile> List<CardProfile> getAllCardProfiles() {
        return (List<CardProfile>) cardRepository.findAll();
    }
*/


    public Optional<CardProfile> getCardProfileById(CardProfileId cardProfileId) {
        return cardRepository.findById(cardProfileId);
    }


   /* public ResponseApiJson<?> addCardProfile(CardProfile cardProfile) {
        cardRepository.save(cardProfile);
        return null;
    }*/


    public ResponseApiJson<List<CardProfile>> getAllCardProfiles() {
        String idRequest = "CPS_" + new Date().getTime() + (Math.random() * 9999);
        try {
            List<CardProfile> cardProfiles = cardRepository.findAll();
            return new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS, "Get all atmcard succesfully  !", cardProfiles);

        } catch (Exception e) {
            logger.info("Exception of this::getAllCardProfiles " + e.getMessage());
            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE, "some issues in getAllCardProfiles please check with your provider !");

        }
    }

    public ResponseApiJson<?> addCardProfile(CardProfile cardProfile) {
        String idRequest = "AM_" + new Date().getTime() + (Math.random() * 9999);
        logger.info(cardProfile.toString());
        try {
            Optional<CardProfile> cardProfiletoCheck = cardRepository.findById(cardProfile.getId());
            if (cardProfiletoCheck.isEmpty()) {
                cardRepository.save(cardProfile);
                return new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS, "Insertde atmcardProfile sucessfully  !");
            } else {
                return new ResponseApiJson<>(idRequest, GlobalVars.ALREADYEXIST, "this atmCardProfile already exist !");

            }
        } catch (Exception e) {
            logger.info("Exception of getOneAtmCardProfile " + e.getMessage());
            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE, "Some issues in addAtmCardProfile please check with your provider !");
        }

    }


        public ResponseApiJson<List<CardProfile>> getOneCardProfile(CardProfileId id) {
            String idRequest = "CPSS_" + new Date().getTime() + (Math.random() * 9999);
            try {
                Optional<CardProfile> cardProfile = cardRepository.findById(id);
                List<CardProfile> cardProfiles = new ArrayList<>();
                cardProfile.ifPresent(cardProfiles::add);
                return new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS , "Get one AtmCardProfile succesfully  !" , cardProfiles);
            } catch (Exception e)   {
                logger.info("Exception of  getOneAtmCardProfile " + e.getMessage());
                return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE , " some issues in getOneAtmCardProfile please check with your provider !");

            }
        }


    public  ResponseApiJson<?> updateCardProfile(CardProfile cardProfile) throws IllegalAccessException {
        String  idRequest = "UMS_"  + new Date().getTime() + (Math.random() * 9999);
        try{
            Optional<CardProfile> cardProfileto = cardRepository.findById(cardProfile.getId());
            GlobalVars globalVars = new GlobalVars();
            if (cardProfileto.isPresent()){
                CardProfile cardProfile1 = (CardProfile)  globalVars.construct(CardProfile.class, cardProfile , cardProfileto.get());
                logger.info("cardProfileto " + cardProfile1.toString());
                cardRepository.save(cardProfile1);
                return new ResponseApiJson<>(idRequest , GlobalVars.SUCCESS , "Update  AtmCardProfile  sucessfuly  !");

            } else {
                return new ResponseApiJson<>(idRequest , GlobalVars.NOTEXIST , "this AtmCardProfile  does not exit   !");

            }
        } catch (Exception e) {
            logger.info("Exception of getOneAtmCardProfile "  + e.getMessage());
            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE , "Some issues in updateAtmCardProfile please check with your provider !");


        }
    }

    public  ResponseApiJson<?> deleteCardProfile(CardProfileId id){
        String  idRequest = "DM_" + new Date().getTime() + (Math.random() * 9999);
        try {
            Optional<CardProfile> cardProfile = cardRepository.findById(id);
            cardProfile.ifPresent(cardRepository ::delete);
            return  new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS , "Delete AtmCardProfile succesfully !");
        }catch (Exception e){
            logger.info("Delete AtmCardProfile Exception: " +e.getMessage());
            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE ,"deleteAtmCardProfile terminated with issue");
        }
    }


 /*   public void updateCardProfile(CardProfileId cardProfileId, CardProfile updatedCardProfile) {
        if (cardRepository.existsById(cardProfileId)) {
            updatedCardProfile.setId(cardProfileId);
            cardRepository.save(updatedCardProfile);
        }
    }*/



   /* public void deleteCardProfile(CardProfileId cardProfileId) {
        cardRepository.deleteById(cardProfileId);
    }*/
}
