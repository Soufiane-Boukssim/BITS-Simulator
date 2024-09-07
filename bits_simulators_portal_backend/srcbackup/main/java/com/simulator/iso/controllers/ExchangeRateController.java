package com.simulator.iso.controllers;

import com.simulator.entities.ExchangeRateParam;
import com.simulator.entities.ExchangeRateParamId;
import com.simulator.globalModels.ResponseApiJson;
import com.simulator.iso.services.ExchangeRateService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/su/exchangeRateParam")
@RequiredArgsConstructor
public class ExchangeRateController {
    private final Logger logger = LogManager.getLogger(ExchangeRateController.class);
    @Autowired
    ExchangeRateService exchangeRateService;




    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/get")
    public ResponseEntity<ExchangeRateParam> getExchangeRateParam(@RequestBody ExchangeRateParamId id) {
        try {
            logger.info("test 1");
            ExchangeRateParam exchangeRateParam = exchangeRateService.getExchangeRateParam(id);
            if (exchangeRateParam != null) {
                logger.info("test 2");
                return new ResponseEntity<>(exchangeRateParam , HttpStatus.OK);
            } else {
                logger.info("test 3");
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            logger.info("test 4");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


  /*  @SecurityRequirement(name = "Bearer Authentication")
    @GetMapping("/mti/filter")
    public List<MtiDef> getFilteredMtiList(
            @RequestParam String bankCode,
            @RequestParam String mtiProtocol) {
        return mtiService.getFilteredMtiList(bankCode, mtiProtocol);
    }*/



    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/getAllExchangeRateParams/filter")
    public   List<ExchangeRateParam> getFilteredExchangeRateList(
            @RequestBody ExchangeRateParamId id) {
        return exchangeRateService.getFilteredExchangeRateList(id.getBankCode(), id.getRateProtocol());
    }

   /* @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/getAllExchangeRateParams")
    public ResponseApiJson<List<ExchangeRateParam>> getAllExchangeRateParam() {
        logger.info("#################### Star getAllExchangeRateParams ####################");
        ResponseApiJson<List<ExchangeRateParam>> responseApiJson = exchangeRateService.getAllExchangeRateParams();
        return responseApiJson;
    }*/

    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/getOneExchangeRateParam")
    public ResponseApiJson<List<ExchangeRateParam>> getOneExchangeRateParam(@RequestBody ExchangeRateParamId id) {
        logger.info("####################Star getOneExchangeRateParam ####################");
        ResponseApiJson<List<ExchangeRateParam>> responseApiJson = exchangeRateService.getOneExchangeRateParam(id);
        logger.info("#################### End getOneExchangeRateParam####################");
        return responseApiJson;
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/addExchangeRateParam")
    public ResponseApiJson<?> addExchangeRateParam(@RequestBody ExchangeRateParam exchangeRateParam) {
        logger.info("####################Star addExchangeRateParam####################");
        ResponseApiJson<?> responseApiJson = exchangeRateService.addExchangeRateParam(exchangeRateParam);
        logger.info("#################### End addExchangeRateParam ####################");
        return responseApiJson;

    }

    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/updateExchangeRateParam")
    public ResponseApiJson<?> updateExchangeRateParam(@RequestBody ExchangeRateParam exchangeRateParam) throws IllegalAccessException {
        logger.info("#################### Star updateExchangeRateParam ####################");
        ResponseApiJson<?> responseApiJson = exchangeRateService.updateExchangeRateParam(exchangeRateParam);
        logger.info("#################### End updateExchangeRateParam ####################");
        return responseApiJson;


    }

    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/deleteExchangeRateParam")
    public ResponseApiJson<?> deleteMtiDef(@RequestBody ExchangeRateParamId id) {
        logger.info("#################### Star deleteExchangeRateParam ####################");
        ResponseApiJson<?> responseApiJson = exchangeRateService.deleteExchangeRateParam(id);
        logger.info("#################### End deleteMtiDef ####################");
        return responseApiJson;
    }
}



