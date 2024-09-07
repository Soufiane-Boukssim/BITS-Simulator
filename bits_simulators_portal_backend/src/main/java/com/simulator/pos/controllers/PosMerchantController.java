package com.simulator.pos.controllers;

import com.simulator.entities.pos.PosMerchantParam;
import com.simulator.entities.pos.PosMerchantParamId;
import com.simulator.globalModels.ResponseApiJson;
import com.simulator.pos.services.PosMerchantService;
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
@RequestMapping("/api/su/pos/merchantParam")
@RequiredArgsConstructor
class PosMerchantController {
    private final Logger logger = LogManager.getLogger(PosMerchantController.class);
    @Autowired
    PosMerchantService merchantService;


    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/getAllMerchantParams")
    public ResponseApiJson<List<PosMerchantParam>> getAllMerchantParam(@RequestBody PosMerchantParamId id) {
        logger.info("#################### Star getAllMerchantParams ####################");
        ResponseApiJson<List<PosMerchantParam>> responseApiJson = merchantService.getAllMerchantParams(id);
        return responseApiJson;
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/getOneMerchantParam")
    public ResponseApiJson<List<PosMerchantParam>> getOneMerchantParam(@RequestBody PosMerchantParamId id) {
        logger.info("####################Star getOneMerchantParam ####################");
        ResponseApiJson<List<PosMerchantParam>> responseApiJson = merchantService.getOneMerchantParam(id);
        logger.info("#################### End getOneMerchantParam####################");
        return responseApiJson;
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/addMerchantParam")
    public ResponseApiJson<?> addMerchantParam(@RequestBody PosMerchantParam merchantParam) {
        logger.info("####################Star addMerchantParam ####################");
        ResponseApiJson<?> responseApiJson = merchantService.addMerchantParam(merchantParam);
        logger.info("#################### End addMerchantParam ####################");
        return responseApiJson;

    }

    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/updateMerchantParam")
    public ResponseApiJson<?> updateMerchantParam(@RequestBody PosMerchantParam merchantParam) throws IllegalAccessException {
        logger.info("#################### Star updateMerchantParam ####################");
        ResponseApiJson<?> responseApiJson = merchantService.updateMerchantParam(merchantParam);
        logger.info("#################### End updateMerchantParam ####################");
        return responseApiJson;


    }

    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/deleteMerchantParam")
    public ResponseApiJson<?> deleteMerchantParam(@RequestBody PosMerchantParamId id) {
        logger.info("#################### Star deleteMerchantParam ####################");
        ResponseApiJson<?> responseApiJson = merchantService.deleteMerchantParam(id);
        logger.info("#################### End deleteMerchantParam ####################");
        return responseApiJson;
    }
}



