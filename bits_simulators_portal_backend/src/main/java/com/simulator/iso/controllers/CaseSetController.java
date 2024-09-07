package com.simulator.iso.controllers;

import com.simulator.dtos.CaseSetInfoDTO;
import com.simulator.entities.CaseSetInfo;
import com.simulator.entities.CasesSetInfoId;
import com.simulator.globalModels.ResponseApiJson;
import com.simulator.iso.services.CaseSetService;
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
@RequestMapping("/api/su/caseSetInfo")
@RequiredArgsConstructor
public class CaseSetController {
    private final Logger logger = LogManager.getLogger(CaseSetController.class);
    @Autowired
    CaseSetService caseSetService;

    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/getAllCaseSetInfos")
    public ResponseApiJson<List<CaseSetInfo>> getAllCaseSetInfos (@RequestBody CasesSetInfoId id) {
        logger.info("#################### Star getAllCaseSetInfos ####################");
        ResponseApiJson<List<CaseSetInfo>> responseApiJson = caseSetService.getAllCaseSetInfos(id);
        return responseApiJson;
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/getOneCaseSetInfo")
    public ResponseApiJson<CaseSetInfoDTO> getOneCaseSetInfo(@RequestBody CasesSetInfoId id) {
        logger.info("####################Star getOneCaseSetInfo ####################");
        ResponseApiJson<CaseSetInfoDTO> responseApiJson = caseSetService.getOneCaseSetInfo(id);
        logger.info("#################### End getOneCaseSetInfo ####################");
        return responseApiJson;
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/addCaseSetInfo")
    public ResponseApiJson<?> addCaseSetInfo(@RequestBody CaseSetInfoDTO caseSetInfo) {
        logger.info("####################Star addCaseSetInfo ####################");
        ResponseApiJson<?> responseApiJson = caseSetService.addCaseSetInfo(caseSetInfo);
        logger.info("#################### End addCaseSetInfo ####################");
        return responseApiJson;

    }

    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/updateCaseSetInfo")
    public ResponseApiJson<?> updateCaseSetInfo(@RequestBody CaseSetInfoDTO caseSetInfo) {
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