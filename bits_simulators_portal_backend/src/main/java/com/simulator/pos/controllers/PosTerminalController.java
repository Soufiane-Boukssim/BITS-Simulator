package com.simulator.pos.controllers;


import com.simulator.entities.pos.PosTerminalParam;
import com.simulator.entities.pos.PosTerminalParamId;
import com.simulator.globalModels.ResponseApiJson;
import com.simulator.pos.services.PosTerminalService;
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
@RequestMapping("/api/su/pos/terminalParam")
@RequiredArgsConstructor
public class PosTerminalController {
    private final Logger logger = LogManager.getLogger(PosTerminalController.class);
    @Autowired
    PosTerminalService terminalService;



    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/getAllTerminalParams")
    public ResponseApiJson<List<PosTerminalParam>> getAllTerminalParams(@RequestBody PosTerminalParamId id) {
        logger.info("#################### Star getAllTerminalParams ####################");
        ResponseApiJson<List<PosTerminalParam>> responseApiJson = terminalService.getAllTerminalParams(id);
        return responseApiJson;
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/getOneTerminalParam")
    public ResponseApiJson<List<PosTerminalParam>> getOneTerminalParam(@RequestBody PosTerminalParamId id) {
        logger.info("####################Star getOneTerminalParam ####################");
        ResponseApiJson<List<PosTerminalParam>> responseApiJson = terminalService.getOneTerminalParam(id);
        logger.info("#################### End getOneTerminalParam####################");
        return responseApiJson;
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/addTerminalParam")
    public ResponseApiJson<?> addTerminalParam(@RequestBody PosTerminalParam terminalParam) {
        logger.info("####################Star addTerminalParam ####################");
        ResponseApiJson<?> responseApiJson = terminalService.addTerminalParam(terminalParam);
        logger.info("#################### End addTerminalParam ####################");
        return responseApiJson;
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/updateTerminalParam")
    public ResponseApiJson<?> updateTerminalParam(@RequestBody PosTerminalParam terminalParam) throws IllegalAccessException {
        logger.info("#################### Star updateTerminalParam ####################");
        ResponseApiJson<?> responseApiJson = terminalService.updateTerminalParam(terminalParam);
        logger.info("#################### End updateTerminalParam ####################");
        return responseApiJson;


    }

    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/deleteTerminalParam")
    public ResponseApiJson<?> deleteTerminalParam(@RequestBody PosTerminalParamId id) {
        logger.info("#################### Star deleteTerminalParam ####################");
        logger.info("id====>+"+id);
        ResponseApiJson<?> responseApiJson = terminalService.deleteTerminalParam(id);
        logger.info("#################### End deleteTerminalParam ####################");
        return responseApiJson;
    }
}



