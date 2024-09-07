package com.simulator.iso.services;

import com.simulator.config.GlobalVars;
import com.simulator.entities.CardProfile;
import com.simulator.entities.CardProfileId;
import com.simulator.globalModels.ResponseApiJson;
import com.simulator.repository.CardRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@Service
public  class CardService {
    private final Logger logger = LogManager.getLogger(CardService.class);
    @Autowired
    CardRepository cardRepository;



    public CardProfile getCardProfile(CardProfileId id) {
        return cardRepository.findById(id).orElse(null);
    }



    public List<CardProfile> getFilteredCardList(String bankCode, Character cardProtocol) {
        return cardRepository.findByBankCodeAndCardProtocol(bankCode, cardProtocol);
    }

    public ResponseApiJson<List<CardProfile>> getAllCardProfiles() {
        String idRequest = "CPS_" + new Date().getTime() + (Math.random() * 9999);
        try {
            List<CardProfile> cardProfiles = cardRepository.findAll();
            return new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS, "Get all card succesfully  !", cardProfiles);

        } catch (Exception e) {
            logger.info("Exception of this::getAllCardProfiles " + e.getMessage());
            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE, "some issues in getAllCardProfiles please check with your provider !");

        }


    }

    public ResponseApiJson<List<CardProfile>> getOneCardProfile(CardProfileId id) {
        String idRequest = "CPSS_" + new Date().getTime() + (Math.random() * 9999);
        try {
            Optional<CardProfile> cardProfile = cardRepository.findById(id);
            List<CardProfile> cardProfiles = new ArrayList<>();
            cardProfile.ifPresent(cardProfiles::add);
            return new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS , "Get one cardProfile succesfully  !" , cardProfiles);
        } catch (Exception e)   {
            logger.info("Exception of  getOneCardProfile " + e.getMessage());
            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE , " some issues in getOneCardProfile please check with your provider !");

        }
    }

    public ResponseApiJson<?> addCardProfile(CardProfile cardProfile){
        String idRequest = "AM_"  + new  Date().getTime() + (Math.random() * 9999);
        logger.info(cardProfile.toString());
        try {
            Optional<CardProfile> cardProfiletoCheck = cardRepository.findById(cardProfile.getId());
            if ( cardProfiletoCheck.isEmpty()) {
                cardRepository.save(cardProfile);
                return new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS, "Insertde cardProfile sucessfully  !");
            }else {
                return  new ResponseApiJson<>(idRequest , GlobalVars.ALREADYEXIST , "this CardProfile already exist !");

            }
        } catch (Exception e ){
            logger.info("Exception of getOneCardProfile "  + e.getMessage());
            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE , "Some issues in addCardProfile please check with your provider !");
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
                return new ResponseApiJson<>(idRequest , GlobalVars.SUCCESS , "Update  cardProfile  sucessfuly  !");

            } else {
                return new ResponseApiJson<>(idRequest , GlobalVars.NOTEXIST , "this CardProfile  does not exit   !");

            }
        } catch (Exception e) {
            logger.info("Exception of getOneCardProfile "  + e.getMessage());
            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE , "Some issues in updateCardService please check with your provider !");


        }
    }

    public  ResponseApiJson<?> deleteCardProfile(CardProfileId id){
        String  idRequest = "DM_" + new Date().getTime() + (Math.random() * 9999);
        try {
            Optional<CardProfile> cardProfile = cardRepository.findById(id);
            cardProfile.ifPresent(cardRepository ::delete);
            return  new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS , "Delete cardProfile successfully !");
        }catch (Exception e){
            logger.info("Delete cardProfile Exception: " +e.getMessage());
            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE ,"deleteCardProfile terminated with issue");
        }
    }
}





