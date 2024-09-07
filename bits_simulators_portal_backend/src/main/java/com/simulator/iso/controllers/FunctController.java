package com.simulator.iso.controllers;

import com.simulator.entities.FunctDef;
import com.simulator.entities.FunctDefId;
import com.simulator.globalModels.ResponseApiJson;
import com.simulator.iso.services.FunctService;
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
@RequestMapping("/api/su/functDef")
@RequiredArgsConstructor
public class FunctController {
    private final Logger logger = LogManager.getLogger(FunctController.class);
    @Autowired
    FunctService functService;



    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/getAllFunctDefs")
    public ResponseApiJson<List<FunctDef>> getAllFunctDef() {
        logger.info("#################### Star getAllFunctDefs ####################");
        ResponseApiJson<List<FunctDef>> responseApiJson = functService.getAllFunctDefs();
        return responseApiJson;
    }


    @PostMapping("/getFunctDefsByProtocol")
    public ResponseApiJson<List<FunctDef>> getFunctDefsByProtocol(@RequestBody FunctDefId id) {
        logger.info("#################### Star getFunctDefsByProtocol ####################");
        ResponseApiJson<List<FunctDef>> responseApiJson = functService.getFunctDefsByProtocol(id);
        return responseApiJson;
    }


    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/getOneFunctDef")
    public ResponseApiJson<List<FunctDef>> getOneFunctDef(@RequestBody FunctDefId id) {
        logger.info("####################Star getOneMtiDef ####################");
        ResponseApiJson<List<FunctDef>> responseApiJson = functService.getOneFunctDef(id);
        logger.info("#################### End getOneFunctDef####################");
        return responseApiJson;
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/addFunctDef")
    public ResponseApiJson<?> addFunctDef(@RequestBody FunctDef functDef) {
        logger.info("####################Star addFunctDef ####################");
        ResponseApiJson<?> responseApiJson = functService.addFunctDef(functDef);
        logger.info("#################### End addFunctDef ####################");
        return responseApiJson;

    }

    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/updateFunctDef")
    public ResponseApiJson<?> updateFunctDef(@RequestBody FunctDef functDef) throws IllegalAccessException {
        logger.info("#################### Star updateFunctDef ####################");
        ResponseApiJson<?> responseApiJson = functService.updateFunctDef(functDef);
        logger.info("#################### End updateFunctDef ####################");
        return responseApiJson;


    }

    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/deleteFunctDef")
    public ResponseApiJson<?> deleteFunctDef(@RequestBody FunctDefId id) {
        logger.info("#################### Star deleteFunctDef ####################");
        ResponseApiJson<?> responseApiJson = functService.deleteFunctDef(id);
        logger.info("#################### End deleteFunctDef ####################");
        return responseApiJson;
    }
}




