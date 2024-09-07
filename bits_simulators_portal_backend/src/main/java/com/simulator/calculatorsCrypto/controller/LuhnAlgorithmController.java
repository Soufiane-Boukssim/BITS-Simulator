package com.simulator.calculatorsCrypto.controller;


import com.simulator.calculatorsCrypto.service.LuhnAlgorithmService;
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
@RequestMapping("/api/su/tools/luhn_algorithm")
@RequiredArgsConstructor
public class LuhnAlgorithmController {


    private final Logger logger = LogManager.getLogger(LuhnAlgorithmController.class);


    @Autowired
    private LuhnAlgorithmService algorithmService;


    @PostMapping("/luhnAlgorithm_generated")
    public ResponseApiJson<?> luhnAlgorithmGenerate(@RequestBody Map<String, String> requestData) {
        logger.info("#################### Start luhnAlgorithmGenerate ####################");

        String pan = requestData.get("pan");


        ResponseApiJson<?> responseApiJson = algorithmService.luhnAlgorithmGenerate(pan);
        return responseApiJson;
    }


    @PostMapping("/luhnAlgorithm_validate")
    public ResponseApiJson<?> luhnAlgorithmValidate(@RequestBody Map<String, String> requestData) {
        logger.info("#################### Start luhnAlgorithmGenerate ####################");

        String pan = requestData.get("pan");


        ResponseApiJson<?> responseApiJson = algorithmService.luhnAlgorithmValidate(pan);
        return responseApiJson;
    }

}
