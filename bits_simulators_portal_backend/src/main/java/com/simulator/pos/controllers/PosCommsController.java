package com.simulator.pos.controllers;


import com.simulator.entities.pos.PosCommsParam;
import com.simulator.entities.pos.PosCommsParamId;
import com.simulator.globalModels.ResponseApiJson;
import com.simulator.pos.services.PosCommsService;
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
@RequestMapping("/api/su/pos/commsParam")
@RequiredArgsConstructor
public class PosCommsController {
    private final Logger logger = LogManager.getLogger(PosCommsController.class);
    @Autowired
    PosCommsService commsService;




    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/getAllCommsParams")
    public ResponseApiJson<List<PosCommsParam>> getAllCommsParam(@RequestBody PosCommsParamId id) {
        logger.info("#################### Star getAllCommsParams ####################");
        ResponseApiJson<List<PosCommsParam>> responseApiJson = commsService.getAllCommsParams(id);
        return responseApiJson;
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/getOneCommsParam")
    public ResponseApiJson<List<PosCommsParam>> getOneCommsParam(@RequestBody PosCommsParamId id) {
        logger.info("####################Star getOneCommsParam ####################");
        ResponseApiJson<List<PosCommsParam>> responseApiJson = commsService.getOneCommsParam(id);
        logger.info("#################### End getOneCommsParam####################");
        return responseApiJson;
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/addCommsParam")
    public ResponseApiJson<?> addCommsParam(@RequestBody PosCommsParam commsParam) {
        logger.info("####################Star addCommsParam ####################");
        ResponseApiJson<?> responseApiJson = commsService.addCommsParam(commsParam);
        logger.info("#################### End addCommsParam ####################");
        return responseApiJson;

    }

    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/updateCommsParam")
    public ResponseApiJson<?> updateCommsParam(@RequestBody PosCommsParam commsParam) throws IllegalAccessException {
        logger.info("#################### Star updateCommsParam ####################");
        ResponseApiJson<?> responseApiJson = commsService.updateCommsParam(commsParam);
        logger.info("#################### End updateCommsParam ####################");
        return responseApiJson;


    }

    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/deleteCommsParam")
    public ResponseApiJson<?> deleteCommsParam(@RequestBody PosCommsParamId id) {
        logger.info("#################### Star deleteCommsParam ####################");
        ResponseApiJson<?> responseApiJson = commsService.deleteCommsParam(id);
        logger.info("#################### End deleteCommsParam ####################");
        return responseApiJson;
    }
}



