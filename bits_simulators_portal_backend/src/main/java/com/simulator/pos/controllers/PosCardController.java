package com.simulator.pos.controllers;

import com.simulator.config.GlobalVars;
import com.simulator.entities.pos.PosCardProfile;
import com.simulator.entities.pos.PosCardProfileId;
import com.simulator.globalModels.ResponseApiJson;
import com.simulator.iso.controllers.CardController;
import com.simulator.iso.services.IccService;
import com.simulator.pos.model.PosCardProfileWithIccModel;
import com.simulator.pos.services.PosCardService;
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
@RequestMapping("/api/su/pos/CardProfile")
@RequiredArgsConstructor
public class PosCardController {
    private final Logger logger = LogManager.getLogger(CardController.class);
    @Autowired
    PosCardService cardService;
    GlobalVars globalVars=new GlobalVars();
    @Autowired
    IccService iccService;


    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/getAllCardProfiles")
    public ResponseApiJson<List<PosCardProfile>> getAllCardProfiles (@RequestBody PosCardProfileId id){
        logger.info("#################### Star getAllCardProfiles ####################");
        ResponseApiJson<List<PosCardProfile>> responseApiJson = cardService.getAllCardProfiles(id);
        return responseApiJson;
    }



    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/getOneCardProfile")
    public ResponseApiJson<List<PosCardProfile>> getOneCardProfile(@RequestBody PosCardProfileId id) {
        logger.info("####################Star getOneCardProfile ####################");
        ResponseApiJson<List<PosCardProfile>> responseApiJson = cardService.getOneCardProfile(id);
        logger.info("#################### End getOneCardProfile####################");
        return responseApiJson;
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/addCardProfile")
    public ResponseApiJson<?> addCardProfile(@RequestBody PosCardProfileWithIccModel cardProfileWithIccModel) {
        logger.info("####################Star addCardProfile ####################");
        logger.info(cardProfileWithIccModel.toString());
        ResponseApiJson<?> responseApiJson = cardService.addCardProfile(new PosCardProfile(cardProfileWithIccModel),cardProfileWithIccModel.getTags());
//        ResponseApiJson<?> responseApiJson1 = iccService.addIccProfiles(cardProfileWithIccModel.getTags());
        logger.info("#################### End addCardProfile ####################");
        return responseApiJson;

    }

    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/updateCardProfile")
    public ResponseApiJson<?> updateCardProfile(@RequestBody PosCardProfileWithIccModel cardProfileWithIccModel) throws IllegalAccessException {
        logger.info("#################### Star updateCardProfile ####################");
        ResponseApiJson<?> responseApiJson = cardService.updateCardProfile(new PosCardProfile(cardProfileWithIccModel),cardProfileWithIccModel.getTags());
//        ResponseApiJson<?> responseApiJson1 = iccService.addIccProfiles(cardProfileWithIccModel.getTags());
        logger.info("#################### End updateCardProfile ####################");
        return responseApiJson;


    }

    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/deleteCardProfile")
    public ResponseApiJson<?> deleteCardProfile(@RequestBody PosCardProfileId id) {
        logger.info("#################### Star deleteCardProfile ####################");
        ResponseApiJson<?> responseApiJson = cardService.deleteCardProfile(id);
        logger.info("#################### End deleteMtiDef ####################");
        return responseApiJson;
    }
}



