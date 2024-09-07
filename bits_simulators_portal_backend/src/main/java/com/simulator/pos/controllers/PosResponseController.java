package com.simulator.pos.controllers;

import com.simulator.entities.pos.PosResponseDef;
import com.simulator.entities.pos.PosResponseDefId;
import com.simulator.globalModels.ResponseApiJson;
import com.simulator.pos.services.PosResponseService;
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
@RequestMapping("/api/su/pos/responseDef")
@RequiredArgsConstructor
class PosResponseController {
    private final Logger logger = LogManager.getLogger(PosResponseController.class);
    @Autowired
    PosResponseService responseService;


    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/getAllResponseDefs")
    public ResponseApiJson<List<PosResponseDef>> getAllResponseDefs(@RequestBody PosResponseDefId id) {
        logger.info("#################### Star getAllResponseDefs ####################");
        ResponseApiJson<List<PosResponseDef>> responseApiJson = responseService.getAllResponseDefs(id);
        return responseApiJson;
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/getOneResponseDef")
    public ResponseApiJson<List<PosResponseDef>> getOneResponseDef(@RequestBody PosResponseDefId id) {
        logger.info("####################Star getOneResponseDef ####################");
        ResponseApiJson<List<PosResponseDef>> responseApiJson = responseService.getOneResponseDef(id);
        logger.info("#################### End getOneResponseDef####################");
        return responseApiJson;
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/addResponseDef")
    public ResponseApiJson<?> addResponseDef(@RequestBody PosResponseDef responseDef) {
        logger.info("####################Star addResponseDef ####################");
        ResponseApiJson<?> responseApiJson = responseService.addResponseDef(responseDef);
        logger.info("#################### End addResponseDef ####################");
        return responseApiJson;

    }

    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/updateResponseDef")
    public ResponseApiJson<?> updateResponseDef(@RequestBody PosResponseDef responseDef) throws IllegalAccessException {
        logger.info("#################### Star updateResponseDef ####################");
        ResponseApiJson<?> responseApiJson = responseService.updateResponseDef(responseDef);
        logger.info("#################### End updateResponseDef ####################");
        return responseApiJson;


    }

    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/deleteResponseDef")
    public ResponseApiJson<?> deleteResponseDef(@RequestBody PosResponseDefId id) {
        logger.info("#################### Star deleteResponseDef ####################");
        ResponseApiJson<?> responseApiJson = responseService.deleteResponseDef(id);
        logger.info("#################### End deleteResponseDef ####################");
        return responseApiJson;
    }
}


