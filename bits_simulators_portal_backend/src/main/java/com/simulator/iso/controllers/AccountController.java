package com.simulator.iso.controllers;

import com.simulator.entities.AccountDef;
import com.simulator.entities.AccountDefId;
import com.simulator.globalModels.ResponseApiJson;
import com.simulator.iso.services.AccountService;
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
@RequestMapping("/api/su/accountDef")
@RequiredArgsConstructor
public class AccountController {
    private final Logger logger = LogManager.getLogger(AccountController.class);
    @Autowired
    AccountService accountService;


    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/getAllAccountdefs")
    public ResponseApiJson<List<AccountDef>> getAllAccountDef(@RequestBody AccountDefId id) {
        logger.info("#################### Star getAllAccountDefs ####################");
        ResponseApiJson<List<AccountDef>> responseApiJson = accountService.getAllAccountDefs(id);
        return responseApiJson;
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/getOneAccountDef")
    public ResponseApiJson<List<AccountDef>> getOneAccountDef(@RequestBody AccountDefId id) {
        logger.info("####################Star getOneAccountDef ####################");
        ResponseApiJson<List<AccountDef>> responseApiJson = accountService.getOneAccountDef(id);
        logger.info("#################### End getOneAccountDef####################");
        return responseApiJson;
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/addAccountDef")
    public ResponseApiJson<?> addAccountDef(@RequestBody AccountDef accountDef) {
        logger.info("####################Star addAccountDef ####################");
        ResponseApiJson<?> responseApiJson = accountService.addAccountDef(accountDef);
        logger.info("#################### End addAccountDef ####################");
        return responseApiJson;

    }

    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/updateAccountDef")
    public ResponseApiJson<?> updateAccountDef(@RequestBody AccountDef accountDef) throws IllegalAccessException {
        logger.info("#################### Star updateAccountDef ####################");
        ResponseApiJson<?> responseApiJson = accountService.updateAccountDef(accountDef);
        logger.info("#################### End updateAccountDef ####################");
        return responseApiJson;


    }

    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/deleteAccountDef")
    public ResponseApiJson<?> deleteAccountDef(@RequestBody AccountDefId id) {
        logger.info("#################### Star deleteAccountDef ####################");
        ResponseApiJson<?> responseApiJson = accountService.deleteAccountDef(id);
        logger.info("#################### End deleteAccountDef ####################");
        return responseApiJson;
    }
}



