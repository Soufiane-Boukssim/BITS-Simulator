package com.simulator.iso.controllers;

import com.simulator.entities.ResponseDef;
import com.simulator.entities.ResponseDefId;
import com.simulator.globalModels.ResponseApiJson;
import com.simulator.iso.services.ResponseService;
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
@RequestMapping("/api/su/responseDef")
@RequiredArgsConstructor
class ResponseController {
    private final Logger logger = LogManager.getLogger(ResponseController.class);
    @Autowired
    ResponseService responseService;


    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/getAllResponseDefs")
    public ResponseApiJson<List<ResponseDef>> getAllResponseDefs( @RequestBody ResponseDefId id) {
        logger.info("#################### Star getAllResponseDefs ####################");
        ResponseApiJson<List<ResponseDef>> responseApiJson = responseService.getAllResponseDefs(id);
        return responseApiJson;
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/getOneResponseDef")
    public ResponseApiJson<List<ResponseDef>> getOneResponseDef(@RequestBody ResponseDefId id) {
        logger.info("####################Star getOneResponseDef ####################");
        ResponseApiJson<List<ResponseDef>> responseApiJson = responseService.getOneResponseDef(id);
        logger.info("#################### End getOneResponseDef####################");
        return responseApiJson;
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/addResponseDef")
    public ResponseApiJson<?> addResponseDef(@RequestBody ResponseDef responseDef) {
        logger.info("####################Star addResponseDef ####################");
        ResponseApiJson<?> responseApiJson = responseService.addResponseDef(responseDef);
        logger.info("#################### End addResponseDef ####################");
        return responseApiJson;

    }

    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/updateResponseDef")
    public ResponseApiJson<?> updateResponseDef(@RequestBody ResponseDef responseDef) throws IllegalAccessException {
        logger.info("#################### Star updateResponseDef ####################");
        ResponseApiJson<?> responseApiJson = responseService.updateResponseDef(responseDef);
        logger.info("#################### End updateResponseDef ####################");
        return responseApiJson;


    }

    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/deleteResponseDef")
    public ResponseApiJson<?> deleteResponseDef(@RequestBody ResponseDefId id) {
        logger.info("#################### Star deleteResponseDef ####################");
        ResponseApiJson<?> responseApiJson = responseService.deleteResponseDef(id);
        logger.info("#################### End deleteResponseDef ####################");
        return responseApiJson;
    }
}



