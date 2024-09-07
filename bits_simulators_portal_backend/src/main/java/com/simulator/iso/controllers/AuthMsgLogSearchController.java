package com.simulator.iso.controllers;

import com.simulator.entities.AuthoMsgLog;;
import com.simulator.globalModels.ResponseApiJson;
import com.simulator.iso.model.AuthoMsgLogSearchParams;
import com.simulator.iso.services.AuthMsgLogService;
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
@RequestMapping("/api/su/message")
@RequiredArgsConstructor
class AuthMsgLogSearchController {
    private final Logger logger = LogManager.getLogger(AuthMsgLogSearchController.class);
    @Autowired
    AuthMsgLogService authMsgLogService;




    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/getAllMessages")
    public ResponseApiJson<List<AuthoMsgLog>> getAllMessage (@RequestBody AuthoMsgLogSearchParams authoMsgLogSearchParams) {
        logger.info("#################### Star getAllMessage ####################");
        ResponseApiJson<List<AuthoMsgLog>> responseApiJson = authMsgLogService.getAllMessage(authoMsgLogSearchParams);
        logger.info("#################### end getAllMessage ####################");
        return responseApiJson;
    }


}



