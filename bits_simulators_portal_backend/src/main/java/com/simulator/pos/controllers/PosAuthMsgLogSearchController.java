package com.simulator.pos.controllers;


import com.simulator.entities.pos.PosAuthoMsgLog;
import com.simulator.globalModels.ResponseApiJson;
import com.simulator.pos.model.PosAuthoMsgLogSearchParams;
import com.simulator.pos.services.PosAuthMsgLogService;
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
@RequestMapping("/api/su/pos/message")
@RequiredArgsConstructor
class PosAuthMsgLogSearchController {
    private final Logger logger = LogManager.getLogger(PosAuthMsgLogSearchController.class);
    @Autowired
    PosAuthMsgLogService posAuthMsgLogService;



    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/getAllMessages")
    public ResponseApiJson<List<PosAuthoMsgLog>> getAllMessage (@RequestBody PosAuthoMsgLogSearchParams posAuthoMsgLogSearchParams) {
        logger.info("#################### Star getAllMessage ####################");
        ResponseApiJson<List<PosAuthoMsgLog>> responseApiJson = posAuthMsgLogService.getAllMessage(posAuthoMsgLogSearchParams);
        logger.info("#################### end getAllMessage ####################");
        return responseApiJson;
    }


}



