package com.simulator.atm.controllers.controllerscript;

import com.simulator.atm.model.AtmIccProfil;
import com.simulator.atm.model.AtmIccProfilId;
import com.simulator.atm.services.script.AtmIccService;
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
@RequestMapping("/api/su/pos/AtmIccProfile")
@RequiredArgsConstructor
public class AtmIccController {

    private final Logger logger = LogManager.getLogger(AtmIccController.class);
    @Autowired
    AtmIccService iccService;

    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/getAllIccProfiles")
    public ResponseApiJson<List<AtmIccProfil>> getAllIccProfile(@RequestBody AtmIccProfilId id) {
        logger.info("#################### Star getAllIccProfiles ####################");
        ResponseApiJson<List<AtmIccProfil>> responseApiJson = iccService.getAllIccProfiles(id);
        return responseApiJson;
    }


    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/getOneIccProfile")
    public ResponseApiJson<List<AtmIccProfil>> getOneIccProfile(@RequestBody AtmIccProfilId id) {
        logger.info("####################Star getOneIccProfile ####################");
        ResponseApiJson<List<AtmIccProfil>> responseApiJson = iccService.getOneIccProfile(id);
        logger.info("#################### End getOneIccProfile ####################");
        return responseApiJson;
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/addIccProfile")
    public ResponseApiJson<?> addIccProfile(@RequestBody AtmIccProfil iccProfile) {
        logger.info("####################Star addIccProfile ####################");
        ResponseApiJson<?> responseApiJson = iccService.addIccProfile(iccProfile);
        logger.info("#################### End addIccProfile ####################");
        return responseApiJson;

    }

    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/updateIccProfile")
    public ResponseApiJson<?> updateIccProfile(@RequestBody AtmIccProfil iccProfile) throws IllegalAccessException {
        logger.info("#################### Star updateIccProfile ####################");
        ResponseApiJson<?> responseApiJson = iccService.updateIccProfile(iccProfile);
        logger.info("#################### End updateIccProfile ####################");
        return responseApiJson;


    }

    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/deleteIccProfile")
    public ResponseApiJson<?> deleteIccProfile(@RequestBody AtmIccProfilId id) {
        logger.info("#################### Star deleteIccProfile ####################");
        ResponseApiJson<?> responseApiJson = iccService.deleteIccProfile(id);
        logger.info("#################### End deleteIccProfile ####################");
        return responseApiJson;
    }
}
