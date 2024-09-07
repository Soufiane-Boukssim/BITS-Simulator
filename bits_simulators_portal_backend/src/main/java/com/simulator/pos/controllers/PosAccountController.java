package com.simulator.pos.controllers;


import com.simulator.entities.pos.PosAccountDef;
import com.simulator.entities.pos.PosAccountDefId;
import com.simulator.globalModels.ResponseApiJson;
import com.simulator.pos.services.PosAccountService;
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
@RequestMapping("/api/su/pos/accountDef")
@RequiredArgsConstructor
public class PosAccountController {
    private final Logger logger = LogManager.getLogger(PosAccountController.class);
    @Autowired
    PosAccountService accountService;


    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/getAllAccountdefs")
    public ResponseApiJson<List<PosAccountDef>> getAllAccountDef(@RequestBody PosAccountDefId id) {
        logger.info("#################### Star getAllAccountDefs ####################");
        ResponseApiJson<List<PosAccountDef>> responseApiJson = accountService.getAllAccountDefs(id);
        return responseApiJson;
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/getOneAccountDef")
    public ResponseApiJson<List<PosAccountDef>> getOneAccountDef(@RequestBody PosAccountDefId id) {
        logger.info("####################Star getOneAccountDef ####################");
        ResponseApiJson<List<PosAccountDef>> responseApiJson = accountService.getOneAccountDef(id);
        logger.info("#################### End getOneAccountDef####################");
        return responseApiJson;
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/addAccountDef")
    public ResponseApiJson<?> addAccountDef(@RequestBody PosAccountDef accountDef) {
        logger.info("####################Star addAccountDef ####################");
        ResponseApiJson<?> responseApiJson = accountService.addAccountDef(accountDef);
        logger.info("#################### End addAccountDef ####################");
        return responseApiJson;

    }

    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/updateAccountDef")
    public ResponseApiJson<?> updateAccountDef(@RequestBody PosAccountDef accountDef) throws IllegalAccessException {
        logger.info("#################### Star updateAccountDef ####################");
        ResponseApiJson<?> responseApiJson = accountService.updateAccountDef(accountDef);
        logger.info("#################### End updateAccountDef ####################");
        return responseApiJson;


    }

    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/deleteAccountDef")
    public ResponseApiJson<?> deleteAccountDef(@RequestBody PosAccountDefId id) {
        logger.info("#################### Star deleteAccountDef ####################");
        ResponseApiJson<?> responseApiJson = accountService.deleteAccountDef(id);
        logger.info("#################### End deleteAccountDef ####################");
        return responseApiJson;
    }
}



