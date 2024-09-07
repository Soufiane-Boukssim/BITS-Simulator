package com.simulator.iso.controllers;

import com.simulator.entities.CardProfile;
import com.simulator.entities.CardProfileId;
import com.simulator.globalModels.ResponseApiJson;
import com.simulator.iso.services.CardService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/su/CardProfile")
@RequiredArgsConstructor
public class CardController {
    private final Logger logger = LogManager.getLogger(CardController.class);
    @Autowired
    CardService cardService;


    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/get")
    public ResponseEntity<CardProfile> getCardProfile(@RequestBody CardProfileId id) {
        try {
            logger.info("test 1");
            CardProfile cardProfile = cardService.getCardProfile(id);
            if (cardProfile != null) {
                logger.info("test 2");
                return new ResponseEntity<>(cardProfile , HttpStatus.OK);
            } else {
                logger.info("test 3");
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            logger.info("test 4");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


   /* @SecurityRequirement(name = "Bearer Authentication")
    @GetMapping("/card/filter")
    public List<CardProfile> getFilteredCardList(
            @RequestParam String bankCode,
            @RequestParam Character cardProtocol) {
        return cardService.getFilteredCardList(bankCode, cardProtocol);
    }*/



    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/getAllCardProfiles/filter")
    public   List<CardProfile> getFilteredCardList(
            @RequestBody CardProfileId id) {
        return cardService.getFilteredCardList(id.getBankCode(), id.getCardProtocol());
    }

   /* @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/getAllCardProfiles")
    public ResponseApiJson<List<CardProfile>> getAllCardProfile() {
        logger.info("#################### Star getAllCardProfiles ####################");
        ResponseApiJson<List<CardProfile>> responseApiJson = cardService.getAllCardProfiles();
        return responseApiJson;
    }*/

    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/getOneCardProfile")
    public ResponseApiJson<List<CardProfile>> getOneCardProfile(@RequestBody CardProfileId id) {
        logger.info("####################Star getOneCardProfile ####################");
        ResponseApiJson<List<CardProfile>> responseApiJson = cardService.getOneCardProfile(id);
        logger.info("#################### End getOneCardProfile####################");
        return responseApiJson;
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/addCardProfile")
    public ResponseApiJson<?> addCardProfile(@RequestBody CardProfile cardProfile) {
        logger.info("####################Star addCardProfile ####################");
        ResponseApiJson<?> responseApiJson = cardService.addCardProfile(cardProfile);
        logger.info("#################### End addCardProfile ####################");
        return responseApiJson;

    }

    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/updateCardProfile")
    public ResponseApiJson<?> updateCardProfile(@RequestBody CardProfile cardProfile) throws IllegalAccessException {
        logger.info("#################### Star updateCardProfile ####################");
        ResponseApiJson<?> responseApiJson = cardService.updateCardProfile(cardProfile);
        logger.info("#################### End updateCardProfile ####################");
        return responseApiJson;


    }

    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/deleteCardProfile")
    public ResponseApiJson<?> deleteCardProfile(@RequestBody CardProfileId id) {
        logger.info("#################### Star deleteCardProfile ####################");
        ResponseApiJson<?> responseApiJson = cardService.deleteCardProfile(id);
        logger.info("#################### End deleteMtiDef ####################");
        return responseApiJson;
    }
}



