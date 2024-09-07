package com.simulator.calculatorsCrypto.controller;

import com.simulator.calculatorsCrypto.service.DESDE055Service;
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
public class DESDE055Controller {

    private final Logger logger = LogManager.getLogger(DESDE055Controller.class);

    @Autowired
    private DESDE055Service desde055Service;

    @PostMapping("/parceData")
    public ResponseApiJson<?> parceData(@RequestBody Map<String, String> requestData) {
        logger.info("#################### Start parceData ####################");

        String data = requestData.get("data");
        ResponseApiJson<?> responseApiJson = desde055Service.parseData(data);
        return responseApiJson;
    }


}
