package com.simulator.iso.controllers;

import com.simulator.entities.MccDef;
import com.simulator.entities.MccDefId;
import com.simulator.globalModels.ResponseApiJson;
import com.simulator.iso.services.MccService;
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
@RequestMapping("/api/su/mccDef")
@RequiredArgsConstructor
public class MccController {
    private final Logger logger = LogManager.getLogger(MccController.class);
    @Autowired
    MccService mccService;





    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/getAllMccDefs")
    public ResponseApiJson<List<MccDef>> getAllMccDefs(@RequestBody MccDefId id) {
        logger.info("#################### Star getAllMccDefs ####################");
        ResponseApiJson<List<MccDef>> responseApiJson = mccService.getAllMccDefs(id);
        return responseApiJson;
    }



    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/getOneMccDef")
    public ResponseApiJson<List<MccDef>> getOneMccDef(@RequestBody MccDefId id) {
        logger.info("####################Star getOneMccDef ####################");
        ResponseApiJson<List<MccDef>> responseApiJson = mccService.getOneMccDef(id);
        logger.info("#################### End getOneMccDef####################");
        return responseApiJson;
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/addMccDef")
    public ResponseApiJson<?> addMccDef(@RequestBody MccDef mccDef) {
        logger.info("####################Star addMccDef ####################");
        ResponseApiJson<?> responseApiJson = mccService.addMccDef(mccDef);
        logger.info("#################### End addMccDef ####################");
        return responseApiJson;

    }
    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/updateMccDef")
    public ResponseApiJson<?> updateMtiDef(@RequestBody MccDef mccDef) throws IllegalAccessException {
        logger.info("#################### Star updateMccDef ####################");
        ResponseApiJson<?> responseApiJson = mccService.updateMccDef(mccDef);
        logger.info("#################### End updateMccDef ####################");
        return responseApiJson;


    }

    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/deleteMccDef")
    public ResponseApiJson<?> deleteMccDef(@RequestBody MccDefId id) {
        logger.info("#################### Star deleteMccDef ####################");
        ResponseApiJson<?> responseApiJson = mccService.deleteMccDef(id);
        logger.info("#################### End deleteMccDef ####################");
        return responseApiJson;
    }
}





