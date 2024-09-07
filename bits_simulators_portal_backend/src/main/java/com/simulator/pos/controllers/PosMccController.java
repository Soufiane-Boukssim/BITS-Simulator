package com.simulator.pos.controllers;


import com.simulator.entities.pos.PosMccDef;
import com.simulator.entities.pos.PosMccDefId;
import com.simulator.globalModels.ResponseApiJson;
import com.simulator.pos.services.PosMccService;
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
@RequestMapping("/api/su/pos/mccDef")
@RequiredArgsConstructor
public class PosMccController {
    private final Logger logger = LogManager.getLogger(PosMccController.class);
    @Autowired
    PosMccService mccService;



    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/getAllMccDefs")
    public ResponseApiJson<List<PosMccDef>> getAllMccDefs(@RequestBody PosMccDefId id) {
        logger.info("#################### Star getAllMccDefs ####################");
        ResponseApiJson<List<PosMccDef>> responseApiJson = mccService.getAllMccDefs(id);
        return responseApiJson;
    }



    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/getOneMccDef")
    public ResponseApiJson<List<PosMccDef>> getOneMccDef(@RequestBody PosMccDefId id) {
        logger.info("####################Star getOneMccDef ####################");
        ResponseApiJson<List<PosMccDef>> responseApiJson = mccService.getOneMccDef(id);
        logger.info("#################### End getOneMccDef####################");
        return responseApiJson;
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/addMccDef")
    public ResponseApiJson<?> addMccDef(@RequestBody PosMccDef mccDef) {
        logger.info("####################Star addMccDef ####################");
        ResponseApiJson<?> responseApiJson = mccService.addMccDef(mccDef);
        logger.info("#################### End addMccDef ####################");
        return responseApiJson;

    }
    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/updateMccDef")
    public ResponseApiJson<?> updateMtiDef(@RequestBody PosMccDef mccDef) throws IllegalAccessException {
        logger.info("#################### Star updateMccDef ####################");
        ResponseApiJson<?> responseApiJson = mccService.updateMccDef(mccDef);
        logger.info("#################### End updateMccDef ####################");
        return responseApiJson;


    }

    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/deleteMccDef")
    public ResponseApiJson<?> deleteMccDef(@RequestBody PosMccDefId id) {
        logger.info("#################### Star deleteMccDef ####################");
        ResponseApiJson<?> responseApiJson = mccService.deleteMccDef(id);
        logger.info("#################### End deleteMccDef ####################");
        return responseApiJson;
    }
}





