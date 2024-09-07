package com.simulator.pos.controllers;


import com.simulator.entities.FunctDef;
import com.simulator.entities.FunctDefId;
import com.simulator.entities.pos.PosFunctDef;
import com.simulator.entities.pos.PosFunctDefId;
import com.simulator.globalModels.ResponseApiJson;
import com.simulator.pos.services.PosFunctService;
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
@RequestMapping("/api/su/pos/functDef")
@RequiredArgsConstructor
public class PosFunctController {
    private final Logger logger = LogManager.getLogger(PosFunctController.class);
    @Autowired
    PosFunctService functService;





    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/getAllFunctDefs")
    public ResponseApiJson<List<PosFunctDef>> getAllFunctDef() {
        logger.info("#################### Star getAllFunctDefs ####################");
        ResponseApiJson<List<PosFunctDef>> responseApiJson = functService.getAllFunctDefs();
        return responseApiJson;
    }


    @PostMapping("/getFunctDefsByProtocol")
    public ResponseApiJson<List<PosFunctDef>> getFunctDefsByProtocol(@RequestBody PosFunctDefId id) {
        logger.info("#################### Star getFunctDefsByProtocol ####################");
        ResponseApiJson<List<PosFunctDef>> responseApiJson = functService.getFunctDefsByProtocol(id);
        return responseApiJson;
    }


    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/getOneFunctDef")
    public ResponseApiJson<List<PosFunctDef>> getOneFunctDef(@RequestBody PosFunctDefId id) {
        logger.info("####################Star getOneMtiDef ####################");
        ResponseApiJson<List<PosFunctDef>> responseApiJson = functService.getOneFunctDef(id);
        logger.info("#################### End getOneFunctDef####################");
        return responseApiJson;
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/addFunctDef")
    public ResponseApiJson<?> addFunctDef(@RequestBody PosFunctDef functDef) {
        logger.info("####################Star addFunctDef ####################");
        ResponseApiJson<?> responseApiJson = functService.addFunctDef(functDef);
        logger.info("#################### End addFunctDef ####################");
        return responseApiJson;

    }

    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/updateFunctDef")
    public ResponseApiJson<?> updateFunctDef(@RequestBody PosFunctDef functDef) throws IllegalAccessException {
        logger.info("#################### Star updateFunctDef ####################");
        ResponseApiJson<?> responseApiJson = functService.updateFunctDef(functDef);
        logger.info("#################### End updateFunctDef ####################");
        return responseApiJson;


    }

    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/deleteFunctDef")
    public ResponseApiJson<?> deleteFunctDef(@RequestBody PosFunctDefId id) {
        logger.info("#################### Star deleteFunctDef ####################");
        ResponseApiJson<?> responseApiJson = functService.deleteFunctDef(id);
        logger.info("#################### End deleteFunctDef ####################");
        return responseApiJson;
    }
}




