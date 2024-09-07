package com.simulator.iso.controllers;

import com.simulator.entities.InstParam;
import com.simulator.entities.InstParamId;
import com.simulator.globalModels.ResponseApiJson;
import com.simulator.iso.services.InstParamService;
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
@RequestMapping("/api/su/instParam")
@RequiredArgsConstructor
public class InstParamController {
    private final Logger logger = LogManager.getLogger(InstParamController.class);
    @Autowired
    InstParamService instParamService;

    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/getAllInstParams")
    public ResponseApiJson<List<InstParam>> getAllInstParams(@RequestBody InstParamId id) {
        logger.info("#################### Star getAllInstParams ####################");
        ResponseApiJson<List<InstParam>> responseApiJson = instParamService.getAllInstParams(id);
        return responseApiJson;
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/getOneInstParam")
    public ResponseApiJson<List<InstParam>> getOneInstParam(@RequestBody InstParamId id) {
        logger.info("#################### Star getOneInstParam ####################");
        ResponseApiJson<List<InstParam>> responseApiJson = instParamService.getOneInstParam(id);
        logger.info("#################### End getOneInstParam ####################");
        return responseApiJson;
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/addInstParam")
    public ResponseApiJson<?> addInstParam(@RequestBody InstParam instParam) {
        logger.info("#################### Star addInstParam ####################");
        ResponseApiJson<?> responseApiJson = instParamService.addInstParam(instParam);
        logger.info("#################### End addInstParam ####################");
        return responseApiJson;
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/updateInstParam")
    public ResponseApiJson<?> updateInstParam(@RequestBody InstParam instParam) throws IllegalAccessException {
        logger.info("#################### Star updateInstParam ####################");
        ResponseApiJson<?> responseApiJson = instParamService.updateInstParam(instParam);
        logger.info("#################### End updateInstParam ####################");
        return responseApiJson;
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/deleteInstParam")
    public ResponseApiJson<?> deleteInstParam(@RequestBody InstParamId id) {
        logger.info("#################### Star deleteInstParam ####################");
        ResponseApiJson<?> responseApiJson = instParamService.deleteInstParam(id);
        logger.info("#################### End deleteInstParam ####################");
        return responseApiJson;
    }
}
