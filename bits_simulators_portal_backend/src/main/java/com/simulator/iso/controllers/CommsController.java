package com.simulator.iso.controllers;

import com.simulator.entities.CommsParam;
import com.simulator.entities.CommsParamId;
import com.simulator.globalModels.ResponseApiJson;
import com.simulator.iso.services.CommsService;
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
@RequestMapping("/api/su/commsParam")
@RequiredArgsConstructor
public class CommsController {
    private final Logger logger = LogManager.getLogger(CommsController.class);
    @Autowired
    CommsService commsService;


    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/getAllCommsParams")
    public ResponseApiJson<List<CommsParam>> getAllCommsParam(@RequestBody CommsParamId id) {
        logger.info("#################### Star getAllCommsParams ####################");
        ResponseApiJson<List<CommsParam>> responseApiJson = commsService.getAllCommsParams(id);
        return responseApiJson;
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/getOneCommsParam")
    public ResponseApiJson<List<CommsParam>> getOneCommsParam(@RequestBody CommsParamId id) {
        logger.info("####################Star getOneCommsParam ####################");
        ResponseApiJson<List<CommsParam>> responseApiJson = commsService.getOneCommsParam(id);
        logger.info("#################### End getOneCommsParam####################");
        return responseApiJson;
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/addCommsParam")
    public ResponseApiJson<?> addCommsParam(@RequestBody CommsParam commsParam) {
        logger.info("####################Star addCommsParam ####################");
        ResponseApiJson<?> responseApiJson = commsService.addCommsParam(commsParam);
        logger.info("#################### End addCommsParam ####################");
        return responseApiJson;

    }

    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/updateCommsParam")
    public ResponseApiJson<?> updateCommsParam(@RequestBody CommsParam commsParam) throws IllegalAccessException {
        logger.info("#################### Star updateCommsParam ####################");
        ResponseApiJson<?> responseApiJson = commsService.updateCommsParam(commsParam);
        logger.info("#################### End updateCommsParam ####################");
        return responseApiJson;


    }

    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/deleteCommsParam")
    public ResponseApiJson<?> deleteCommsParam(@RequestBody CommsParamId id) {
        logger.info("#################### Star deleteCommsParam ####################");
        ResponseApiJson<?> responseApiJson = commsService.deleteCommsParam(id);
        logger.info("#################### End deleteCommsParam ####################");
        return responseApiJson;
    }
}



