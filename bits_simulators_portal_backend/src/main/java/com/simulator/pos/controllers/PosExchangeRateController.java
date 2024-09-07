package com.simulator.pos.controllers;

import com.simulator.entities.pos.PosExchangeRateParam;
import com.simulator.entities.pos.PosExchangeRateParamId;
import com.simulator.globalModels.ResponseApiJson;
import com.simulator.iso.controllers.ExchangeRateController;
import com.simulator.pos.services.PosExchangeRateService;
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
import java.util.Map;

@RestController
@RequestMapping("/api/su/pos/exchangeRateParam")
@RequiredArgsConstructor
public class PosExchangeRateController {

    private final Logger logger = LogManager.getLogger(ExchangeRateController.class);
    @Autowired
    PosExchangeRateService exchangeRateService;


    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/getAllExchangeRateParams")
    public ResponseApiJson<?> getAllExchangeRateParams(@RequestBody Map<String, String> request) {
        logger.info("#################### Star getAllExchangeRateParams ####################");
        String protocol = request.get("protocol");
        String bankCode = request.get("bankCode");
        ResponseApiJson<?> responseApiJson = exchangeRateService.getAllExchangeRateParams(protocol, bankCode);
        return responseApiJson;
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/getOneExchangeRateParam")
    public ResponseApiJson<List<PosExchangeRateParam>> getOneExchangeRateParam(@RequestBody PosExchangeRateParamId id) {
        logger.info("####################Star getOneExchangeRateParam ####################");
        ResponseApiJson<List<PosExchangeRateParam>> responseApiJson = exchangeRateService.getOneExchangeRateParam(id);
        logger.info("#################### End getOneExchangeRateParam####################");
        return responseApiJson;
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/addExchangeRateParam")
    public ResponseApiJson<?> addExchangeRateParam(@RequestBody PosExchangeRateParam exchangeRateParam) {
        logger.info("####################Star addExchangeRateParam####################");
        ResponseApiJson<?> responseApiJson = exchangeRateService.addExchangeRateParam(exchangeRateParam);
        logger.info("#################### End addExchangeRateParam ####################");
        return responseApiJson;

    }

    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/updateExchangeRateParam")
    public ResponseApiJson<?> updateExchangeRateParam(@RequestBody PosExchangeRateParam exchangeRateParam) throws IllegalAccessException {
        logger.info("#################### Star updateExchangeRateParam ####################");
        ResponseApiJson<?> responseApiJson = exchangeRateService.updateExchangeRateParam(exchangeRateParam);
        logger.info("#################### End updateExchangeRateParam ####################");
        return responseApiJson;


    }

    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/deleteExchangeRateParam")
    public ResponseApiJson<?> deleteMtiDef(@RequestBody PosExchangeRateParamId id) {
        logger.info("#################### Star deleteExchangeRateParam ####################");
        ResponseApiJson<?> responseApiJson = exchangeRateService.deleteExchangeRateParam(id);
        logger.info("#################### End deleteMtiDef ####################");
        return responseApiJson;
    }
}
