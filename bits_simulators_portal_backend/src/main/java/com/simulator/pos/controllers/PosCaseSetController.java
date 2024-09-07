package com.simulator.pos.controllers;


import com.simulator.dtos.pos.PosCaseSetInfoDTO;
import com.simulator.entities.pos.PosCaseSetInfo;
import com.simulator.entities.pos.PosCasesSetInfoId;
import com.simulator.globalModels.ResponseApiJson;
import com.simulator.pos.services.PosCaseSetService;
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
@RequestMapping("/api/su/pos/caseSetInfo")
@RequiredArgsConstructor
public class PosCaseSetController {
    private final Logger logger = LogManager.getLogger(PosCaseSetController.class);
    @Autowired
    PosCaseSetService caseSetService;

    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/getAllCaseSetInfos")
    public ResponseApiJson<List<PosCaseSetInfo>> getAllCaseSetInfos (@RequestBody PosCasesSetInfoId id) {
        logger.info("#################### Star getAllCaseSetInfos ####################");
        ResponseApiJson<List<PosCaseSetInfo>> responseApiJson = caseSetService.getAllCaseSetInfos(id);
        return responseApiJson;
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/getOneCaseSetInfo")
    public ResponseApiJson<PosCaseSetInfoDTO> getOneCaseSetInfo(@RequestBody PosCasesSetInfoId id) {
        logger.info("####################Star getOneCaseSetInfo ####################");
        ResponseApiJson<PosCaseSetInfoDTO> responseApiJson = caseSetService.getOneCaseSetInfo(id);
        logger.info("#################### End getOneCaseSetInfo ####################");
        return responseApiJson;
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/addCaseSetInfo")
    public ResponseApiJson<?> addCaseSetInfo(@RequestBody PosCaseSetInfoDTO caseSetInfo) {
        logger.info("####################Star addCaseSetInfo ####################");
        ResponseApiJson<?> responseApiJson = caseSetService.addCaseSetInfo(caseSetInfo);
        logger.info("#################### End addCaseSetInfo ####################");
        return responseApiJson;

    }

    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/updateCaseSetInfo")
    public ResponseApiJson<?> updateCaseSetInfo(@RequestBody PosCaseSetInfoDTO caseSetInfo) {
        logger.info("#################### Star updateCaseSetInfo ####################");
        ResponseApiJson<?> responseApiJson = caseSetService.updateCaseSetInfo(caseSetInfo);
        logger.info("#################### End updateCaseSetInfo ####################");
        return responseApiJson;


    }

    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/deleteCaseSetInfo")
    public ResponseApiJson<?> deleteCaseSetInfo(@RequestBody PosCasesSetInfoId id) {
        logger.info("#################### Star deleteCaseSetInfo ####################");
        ResponseApiJson<?> responseApiJson = caseSetService.deleteCaseSetInfo(id);
        logger.info("#################### End deleteCaseSetInfo ####################");
        return responseApiJson;
    }
}
