package com.simulator.iso.controllers;

import com.simulator.entities.MerchantParam;
import com.simulator.entities.MerchantParamId;
import com.simulator.globalModels.ResponseApiJson;
import com.simulator.iso.services.MerchantService;
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
@RequestMapping("/api/su/merchantParam")
@RequiredArgsConstructor
class MerchantController {
    private final Logger logger = LogManager.getLogger(MerchantController.class);
    @Autowired
    MerchantService merchantService;



  /*  @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/get")
    public ResponseEntity<MerchantParam> getMerchantParam(@RequestBody MerchantParamId id) {
        try {
            logger.info("test 1");
            MerchantParam merchantParam = posMerchantService.getMerchantParam(id);
            if (merchantParam != null) {
                logger.info("test 2");
                return new ResponseEntity<>(merchantParam , HttpStatus.OK);
            } else {
                logger.info("test 3");
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            logger.info("test 4");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }*/


   /* @SecurityRequirement(name = "Bearer Authentication")
    @GetMapping("/mti/filter")
    public List<MtiDef> getFilteredMtiList(
            @RequestParam String bankCode,
            @RequestParam String mtiProtocol) {
        return posMtiService.getFilteredMtiList(bankCode, mtiProtocol);
    }*/



  /*  @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/getAllMerchantParams/filter")
    public   List<MerchantParam> getFilteredMerchantList(
            @RequestBody MerchantParamId id) {
        return posMerchantService.getFilteredMerchantList(id.getBankCode(), id.getMerProtocol());
    }*/

    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/getAllMerchantParams")
    public ResponseApiJson<List<MerchantParam>> getAllMerchantParam( @RequestBody MerchantParamId id) {
        logger.info("#################### Star getAllMerchantParams ####################");
        ResponseApiJson<List<MerchantParam>> responseApiJson = merchantService.getAllMerchantParams(id);
        return responseApiJson;
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/getOneMerchantParam")
    public ResponseApiJson<List<MerchantParam>> getOneMerchantParam(@RequestBody MerchantParamId id) {
        logger.info("####################Star getOneMerchantParam ####################");
        ResponseApiJson<List<MerchantParam>> responseApiJson = merchantService.getOneMerchantParam(id);
        logger.info("#################### End getOneMerchantParam####################");
        return responseApiJson;
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/addMerchantParam")
    public ResponseApiJson<?> addMerchantParam(@RequestBody MerchantParam merchantParam) {
        logger.info("####################Star addMerchantParam ####################");
        ResponseApiJson<?> responseApiJson = merchantService.addMerchantParam(merchantParam);
        logger.info("#################### End addMerchantParam ####################");
        return responseApiJson;

    }

    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/updateMerchantParam")
    public ResponseApiJson<?> updateMerchantParam(@RequestBody MerchantParam merchantParam) throws IllegalAccessException {
        logger.info("#################### Star updateMerchantParam ####################");
        ResponseApiJson<?> responseApiJson = merchantService.updateMerchantParam(merchantParam);
        logger.info("#################### End updateMerchantParam ####################");
        return responseApiJson;


    }

    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/deleteMerchantParam")
    public ResponseApiJson<?> deleteMerchantParam(@RequestBody MerchantParamId id) {
        logger.info("#################### Star deleteMerchantParam ####################");
        ResponseApiJson<?> responseApiJson = merchantService.deleteMerchantParam(id);
        logger.info("#################### End deleteMerchantParam ####################");
        return responseApiJson;
    }
}



