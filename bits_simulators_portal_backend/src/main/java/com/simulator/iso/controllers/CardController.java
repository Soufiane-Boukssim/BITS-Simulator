package com.simulator.iso.controllers;

import com.simulator.config.GlobalVars;
import com.simulator.entities.CardProfile;
import com.simulator.entities.CardProfileId;
import com.simulator.globalModels.ResponseApiJson;
import com.simulator.iso.model.CardProfileWithIccModel;
import com.simulator.iso.services.CardService;
import com.simulator.iso.services.IccService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/su/CardProfile")
@RequiredArgsConstructor
public class CardController {
    private final Logger logger = LogManager.getLogger(CardController.class);
    @Autowired
    CardService cardService;
    GlobalVars globalVars=new GlobalVars();
    @Autowired
    IccService iccService;

    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/getAllCardProfiles")
        public ResponseApiJson<List<CardProfile>> getAllCardProfiles (@RequestBody CardProfileId id){
            logger.info("#################### Star getAllCardProfiles ####################");
            ResponseApiJson<List<CardProfile>> responseApiJson = cardService.getAllCardProfiles(id);
            return responseApiJson;
    }



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
    public ResponseApiJson<?> addCardProfile(@RequestBody CardProfileWithIccModel cardProfileWithIccModel) {
        logger.info("####################Star addCardProfile ####################");
        logger.info(cardProfileWithIccModel.toString());
        ResponseApiJson<?> responseApiJson = cardService.addCardProfile(new CardProfile(cardProfileWithIccModel),cardProfileWithIccModel.getTags());
        ResponseApiJson<?> responseApiJson1 = iccService.addIccProfiles(cardProfileWithIccModel.getTags());
        logger.info("#################### End addCardProfile ####################");
        return responseApiJson;

    }

    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/updateCardProfile")
    public ResponseApiJson<?> updateCardProfile(@RequestBody CardProfileWithIccModel cardProfileWithIccModel) throws IllegalAccessException {
        logger.info("#################### Star updateCardProfile ####################");
        ResponseApiJson<?> responseApiJson = cardService.updateCardProfile(new CardProfile(cardProfileWithIccModel),cardProfileWithIccModel.getTags());
       // logger.info("cardProfileWithIccModel.getTags()"+cardProfileWithIccModel.getTags().toString());
      //  ResponseApiJson<?> responseApiJson1 = iccService.addIccProfiles(cardProfileWithIccModel.getTags());
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



