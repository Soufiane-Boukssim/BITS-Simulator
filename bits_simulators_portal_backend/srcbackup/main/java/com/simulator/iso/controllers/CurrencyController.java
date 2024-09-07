/*
package com.simulator.iso.controllers;

import com.simulator.entities.AccountDef;
import com.simulator.entities.CurrencyParam;
import com.simulator.globalModels.ResponseApiJson;
import com.simulator.iso.services.CurrencyService;
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
@RequestMapping("/api/su/currencyParam")
@RequiredArgsConstructor
public class CurrencyController {
    private final Logger logger = LogManager.getLogger(CurrencyController.class);
    @Autowired
    CurrencyService currencyService;


@SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/get")
    public ResponseEntity<AccountDef> getAccountDef(@RequestBody AccountDefId id) {
        try {
            logger.info("test 1");
            AccountDef accountDef = accountService.getAccountDef(id);
            if (accountDef != null) {
                logger.info("test 2");
                return new ResponseEntity<>(accountDef , HttpStatus.OK);
            } else {
                logger.info("test 3");
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            logger.info("test 4");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



 @SecurityRequirement(name = "Bearer Authentication")
    @GetMapping("/mti/filter")
    public List<MtiDef> getFilteredMtiList(
            @RequestParam String bankCode,
            @RequestParam String mtiProtocol) {
        return mtiService.getFilteredMtiList(bankCode, mtiProtocol);
    }


    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/getAllCurrencyParams")
    public ResponseApiJson<List<CurrencyParam>> getAllCurrencyParam() {
        logger.info("#################### Star getAllCurrencyParams ####################");
        ResponseApiJson<List<CurrencyParam>> responseApiJson = currencyService.getAllCurrencyParams();
        return responseApiJson;
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/getOneCurrencyParam")
    public ResponseApiJson<List<CurrencyParam>> getOneCurrencyParam(@RequestBody String id) {
        logger.info("####################Star getOneCurrencyParam ####################");
        ResponseApiJson<List<CurrencyParam>> responseApiJson = currencyService.getOneCurrencyParam(id);
        logger.info("#################### End getOneCurrencyParam ####################");
        return responseApiJson;
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/addCurrencyParam")
    public ResponseApiJson<?> addCurrencyParam(@RequestBody CurrencyParam currencyParam) {
        logger.info("####################Star addCurrencyParam ####################");
        ResponseApiJson<?> responseApiJson = currencyService.addCurrencyParam(currencyParam);
        logger.info("#################### End addCurrencyParam ####################");
        return responseApiJson;

    }

    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/updateCurrencyParam")
    public ResponseApiJson<?> updateCurrencyParam(@RequestBody CurrencyParam currencyParam) throws IllegalAccessException {
        logger.info("#################### Star updateCurrencyParam ####################");
        ResponseApiJson<?> responseApiJson = currencyService.updateCurrencyParam(currencyParam);
        logger.info("#################### End updateCurrencyParam ####################");
        return responseApiJson;


    }

    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/deleteCurrencyParam")
    public ResponseApiJson<?> deleteCurrencyParam(@RequestBody String id) {
        logger.info("#################### Star deleteCurrencyParam ####################");
        ResponseApiJson<?> responseApiJson = currencyService.deleteCurrencyParam(id);
        logger.info("#################### End deleteCurrencyParam ####################");
        return responseApiJson;
    }
}



*/
