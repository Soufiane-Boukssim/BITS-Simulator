package com.simulator.iso.controllers;

import com.simulator.entities.CaseSetInfo;
import com.simulator.entities.CasesSetInfoId;
import com.simulator.globalModels.ResponseApiJson;
import com.simulator.iso.services.CaseSetService;
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
@RequestMapping("/api/su/caseSetInfo")
@RequiredArgsConstructor
public class CaseSetController {
    private final Logger logger = LogManager.getLogger(CaseSetController.class);
    @Autowired
    CaseSetService caseSetService;


    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/get")
    public ResponseEntity<CaseSetInfo> getCaseSetInfo(@RequestBody CasesSetInfoId id) {
        try {
            logger.info("test 1");
            CaseSetInfo caseSetInfo = caseSetService.getCaseSetInfo(id);
            if (caseSetInfo != null) {
                logger.info("test 2");
                return new ResponseEntity<>(caseSetInfo , HttpStatus.OK);
            } else {
                logger.info("test 3");
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            logger.info("test 4");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    /*@SecurityRequirement(name = "Bearer Authentication")
    @GetMapping("/mti/filter")
    public List<MtiDef> getFilteredMtiList(
            @RequestParam String bankCode,
            @RequestParam String mtiProtocol) {
        return mtiService.getFilteredMtiList(bankCode, mtiProtocol);
    }*/


    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/getAllCaseSetInfos/filter")
    public   List<CaseSetInfo> getFilteredCaseSetList(
            @RequestBody CasesSetInfoId id) {
        return caseSetService.getFilteredCaseSetList(id.getBankCode(), id.getCaseSetProtocole());
    }

   /* @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/getAllCaseSetInfos")
    public ResponseApiJson<List<CaseSetInfo>> getAllCaseSetInfo() {
        logger.info("#################### Star getAllCaseSetInfos ####################");
        ResponseApiJson<List<CaseSetInfo>> responseApiJson = caseSetService.getAllCaseSetInfos();
        return responseApiJson;
    }*/

    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/getOneCaseSetInfo")
    public ResponseApiJson<List<CaseSetInfo>> getOneCaseSetInfo(@RequestBody CasesSetInfoId id) {
        logger.info("####################Star getOneCaseSetInfo ####################");
        ResponseApiJson<List<CaseSetInfo>> responseApiJson = caseSetService.getOneCaseSetInfo(id);
        logger.info("#################### End getOneCaseSetInfo ####################");
        return responseApiJson;
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/addCaseSetInfo")
    public ResponseApiJson<?> addCaseSetInfo(@RequestBody CaseSetInfo caseSetInfo) {
        logger.info("####################Star addCaseSetInfo ####################");
        ResponseApiJson<?> responseApiJson = caseSetService.addCaseSetInfo(caseSetInfo);
        logger.info("#################### End addCaseSetInfo ####################");
        return responseApiJson;

    }

    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/updateCaseSetInfo")
    public ResponseApiJson<?> updateCaseSetInfo(@RequestBody CaseSetInfo caseSetInfo) throws IllegalAccessException {
        logger.info("#################### Star updateCaseSetInfo ####################");
        ResponseApiJson<?> responseApiJson = caseSetService.updateCaseSetInfo(caseSetInfo);
        logger.info("#################### End updateCaseSetInfo ####################");
        return responseApiJson;


    }

    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/deleteCaseSetInfo")
    public ResponseApiJson<?> deleteCaseSetInfo(@RequestBody CasesSetInfoId id) {
        logger.info("#################### Star deleteCaseSetInfo ####################");
        ResponseApiJson<?> responseApiJson = caseSetService.deleteCaseSetInfo(id);
        logger.info("#################### End deleteCaseSetInfo ####################");
        return responseApiJson;
    }
}




