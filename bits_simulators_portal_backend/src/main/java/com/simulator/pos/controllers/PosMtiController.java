package com.simulator.pos.controllers;


import com.simulator.entities.pos.PosMtiDef;
import com.simulator.entities.pos.PosMtiDefId;
import com.simulator.globalModels.ResponseApiJson;
import com.simulator.pos.services.PosMtiService;
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
@RequestMapping("/api/su/pos/mtiDef")
@RequiredArgsConstructor
class PosMtiController {
    private final Logger logger = LogManager.getLogger(PosMtiController.class);
    @Autowired
    PosMtiService mtiService;




    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/getAllMtidefs")
    public ResponseApiJson<List<PosMtiDef>> getAllMtiDefs(@RequestBody PosMtiDefId id) {
        logger.info("#################### Star getAllMtiDefs ####################");
        ResponseApiJson<List<PosMtiDef>> responseApiJson = mtiService.getAllMtiDefs(id);
        return responseApiJson;
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/getOneMtiDef")
    public ResponseApiJson<List<PosMtiDef>> getOneMtiDef(@RequestBody PosMtiDefId id) {
        logger.info("####################Star getOneMtiDef ####################");
        ResponseApiJson<List<PosMtiDef>> responseApiJson = mtiService.getOneMtiDef(id);
        logger.info("#################### End getOneMtiDef####################");
        return responseApiJson;
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/addMtiDef")
    public ResponseApiJson<?> addMtiDef(@RequestBody PosMtiDef mtiDef) {
        logger.info("####################Star addMtiDef ####################");
        ResponseApiJson<?> responseApiJson = mtiService.addMtiDef(mtiDef);
        logger.info("#################### End addMtiDef ####################");
        return responseApiJson;

    }

    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/updateMtiDef")
    public ResponseApiJson<?> updateMtiDef(@RequestBody PosMtiDef mtiDef) throws IllegalAccessException {
        logger.info("#################### Star updateMtiDef ####################");
        ResponseApiJson<?> responseApiJson = mtiService.updateMtiDef(mtiDef);
        logger.info("#################### End updateMtiDef ####################");
        return responseApiJson;


    }

    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/deleteMtiDef")
    public ResponseApiJson<?> deleteMtiDef(@RequestBody PosMtiDefId id) {
        logger.info("#################### Star deleteMtiDef ####################");
        ResponseApiJson<?> responseApiJson = mtiService.deleteMtiDef(id);
        logger.info("#################### End deleteMtiDef ####################");
        return responseApiJson;
    }
}



