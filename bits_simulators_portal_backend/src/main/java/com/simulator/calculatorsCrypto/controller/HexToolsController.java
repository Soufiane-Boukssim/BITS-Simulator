package com.simulator.calculatorsCrypto.controller;


import com.simulator.calculatorsCrypto.service.HexToolsService;
import com.simulator.globalModels.ResponseApiJson;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/su/tools/Calculators")
@RequiredArgsConstructor
public class HexToolsController {

    private final Logger logger = LogManager.getLogger(HexToolsController.class);

    @Autowired
    private HexToolsService hexToolsService;


    @PostMapping("/generateEncyptedPINBlock")
    public ResponseApiJson<?> generateEncyptedPINBlock(@RequestBody Map<String, String> requestData) {
        logger.info("#################### Start generateEncyptedPINBlock ####################");

        String pin = requestData.get("pin");
        String pinBlockFormat = requestData.get("pinBlockFormat");
        String accountNumber = requestData.get("accountNumber");
        String PINKey = requestData.get("PINKey");

        ResponseApiJson<?> responseApiJson = hexToolsService.generateEncyptedPINBlock(pin, pinBlockFormat,accountNumber, PINKey);
        return responseApiJson;
    }




    @PostMapping("/des_mp")
    public ResponseApiJson<?> desMB(@RequestBody Map<String, String> requestData) {
        logger.info("#################### Start generateEncyptedPINBlock ####################");

        String key = requestData.get("key");
        String data = requestData.get("data");

        ResponseApiJson<?> responseApiJson = hexToolsService.desMB(data,key);
        return responseApiJson;
    }





}
