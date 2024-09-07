package com.simulator.pos.controllers;


import com.simulator.entities.pos.PosInstParam;
import com.simulator.entities.pos.PosInstParamId;
import com.simulator.globalModels.ResponseApiJson;
import com.simulator.pos.services.PosInstParamService;
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
@RequestMapping("/api/su/pos/instParam")
@RequiredArgsConstructor
public class PosInstParamController {
    private final Logger logger = LogManager.getLogger(PosInstParamController.class);
    @Autowired
    PosInstParamService instParamService;

    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/getAllInstParams")
    public ResponseApiJson<List<PosInstParam>> getAllInstParams(@RequestBody PosInstParamId id) {
        logger.info("#################### Star getAllInstParams ####################");
        ResponseApiJson<List<PosInstParam>> responseApiJson = instParamService.getAllInstParams(id);
        return responseApiJson;
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/getOneInstParam")
    public ResponseApiJson<List<PosInstParam>> getOneInstParam(@RequestBody PosInstParamId id) {
        logger.info("#################### Star getOneInstParam ####################");
        ResponseApiJson<List<PosInstParam>> responseApiJson = instParamService.getOneInstParam(id);
        logger.info("#################### End getOneInstParam ####################");
        return responseApiJson;
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/addInstParam")
    public ResponseApiJson<?> addInstParam(@RequestBody PosInstParam instParam) {
        logger.info("#################### Star addInstParam ####################");
        ResponseApiJson<?> responseApiJson = instParamService.addInstParam(instParam);
        logger.info("#################### End addInstParam ####################");
        return responseApiJson;
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/updateInstParam")
    public ResponseApiJson<?> updateInstParam(@RequestBody PosInstParam instParam) throws IllegalAccessException {
        logger.info("#################### Star updateInstParam ####################");
        ResponseApiJson<?> responseApiJson = instParamService.updateInstParam(instParam);
        logger.info("#################### End updateInstParam ####################");
        return responseApiJson;
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/deleteInstParam")
    public ResponseApiJson<?> deleteInstParam(@RequestBody PosInstParamId id) {
        logger.info("#################### Star deleteInstParam ####################");
        ResponseApiJson<?> responseApiJson = instParamService.deleteInstParam(id);
        logger.info("#################### End deleteInstParam ####################");
        return responseApiJson;
    }
}
