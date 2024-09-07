package com.simulator.atm.controllers.controllerscript;

import com.simulator.atm.model.AtmCardProfil;
import com.simulator.atm.model.AtmCardProfilId;
import com.simulator.atm.model.AtmCardProfilWithIccModel;
import com.simulator.atm.services.script.AtmCardProfileService;
import com.simulator.atm.services.script.AtmIccService;
import com.simulator.config.GlobalVars;
import com.simulator.globalModels.ResponseApiJson;
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
@RequestMapping("/api/su/pos/AtmCardProfile")
@RequiredArgsConstructor
public class AtmCardProfilController {

    private final Logger logger = LogManager.getLogger(AtmCardProfilController.class);
    @Autowired
    AtmCardProfileService cardService;
    GlobalVars globalVars=new GlobalVars();
    @Autowired
    AtmIccService iccService;

    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/getAllCardProfiles")
    public ResponseApiJson<List<AtmCardProfil>> getAllCardProfiles (@RequestBody AtmCardProfilId id){
        logger.info("#################### Star getAllCardProfiles ####################");
        ResponseApiJson<List<AtmCardProfil>> responseApiJson = cardService.getAllCardProfiles(id);
        return responseApiJson;
    }



    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/getOneCardProfile")
    public ResponseApiJson<List<AtmCardProfil>> getOneCardProfile(@RequestBody AtmCardProfilId id) {
        logger.info("####################Star getOneCardProfile ####################");
        ResponseApiJson<List<AtmCardProfil>> responseApiJson = cardService.getOneCardProfile(id);
        logger.info("#################### End getOneCardProfile####################");
        return responseApiJson;
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/addCardProfile")
    public ResponseApiJson<?> addCardProfile(@RequestBody AtmCardProfilWithIccModel posCardProfileWithIccModel) {
        logger.info("####################Star addCardProfile ####################");
        logger.info(posCardProfileWithIccModel.toString());
        ResponseApiJson<?> responseApiJson = cardService.addCardProfile(new AtmCardProfil(posCardProfileWithIccModel));
        ResponseApiJson<?> responseApiJson1 = iccService.addIccProfiles(posCardProfileWithIccModel.getTags());
        logger.info("#################### End addCardProfile ####################");
        return responseApiJson;

    }

    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/updateCardProfile")
    public ResponseApiJson<?> updateCardProfile(@RequestBody AtmCardProfilWithIccModel posCardProfileWithIccModel) throws IllegalAccessException {
        logger.info("#################### Star updateCardProfile ####################");
        ResponseApiJson<?> responseApiJson = cardService.updateCardProfile(new AtmCardProfil(posCardProfileWithIccModel));
        ResponseApiJson<?> responseApiJson1 = iccService.addIccProfiles(posCardProfileWithIccModel.getTags());
        logger.info("#################### End updateCardProfile ####################");
        return responseApiJson;


    }

    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/deleteCardProfile")
    public ResponseApiJson<?> deleteCardProfile(@RequestBody AtmCardProfilId id) {
        logger.info("#################### Star deleteCardProfile ####################");
        ResponseApiJson<?> responseApiJson = cardService.deleteCardProfile(id);
        logger.info("#################### End deleteMtiDef ####################");
        return responseApiJson;
    }
}
