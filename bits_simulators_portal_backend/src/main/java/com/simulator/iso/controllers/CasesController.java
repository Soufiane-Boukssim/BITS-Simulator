package com.simulator.iso.controllers;

import com.simulator.dtos.CasesDefDTO;
import com.simulator.dtos.CasesDefinitionDTO;
import com.simulator.entities.CasesDefinition;
import com.simulator.entities.CasesDefinitionId;
import com.simulator.globalModels.ResponseApiJson;
import com.simulator.iso.services.CasesService;
import com.simulator.pos.controllers.PosCasesController;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
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
@RequestMapping("/api/su/casesDefinition")
public class CasesController {
    private final Logger logger = LogManager.getLogger(PosCasesController.class);
    private final CasesService casesService;

    @Autowired
    public CasesController(CasesService casesService) {
        this.casesService = casesService;
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/getAllCasesDefinitions")
    public ResponseApiJson<List<CasesDefDTO>> getAllCasesDefinitions(@RequestBody CasesDefinitionId id ) {
        logger.info("#################### Start getAllCasesDefinitions ####################");
        ResponseApiJson<List<CasesDefDTO>> responseApiJson = casesService.getAllCasesDefinitions(id);
        logger.info("list size : " + responseApiJson.getResult().size());
        logger.info("#################### end getAllCasesDefinitions ####################");
        return responseApiJson ;
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/getCaseDefinitions")
    public ResponseApiJson<Map<String, CasesDefinitionDTO>> getCaseDefinitions(@RequestBody CasesDefinitionId id ) {
        logger.info("#################### Start getCaseDefinitions ####################");
        ResponseApiJson<Map<String, CasesDefinitionDTO>> responseApiJson = casesService.getCaseDefinition(id);
        //logger.info("list size : " + responseApiJson.getResult().size());
        logger.info("#################### end getCaseDefinitions ####################");
        return responseApiJson;
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/addCasesDefinition")
    public ResponseApiJson<?> addCasesDefinition(@RequestBody List<CasesDefinition> casesDefinition) {
        logger.info("####################Star addCasesDefinition ####################");
        ResponseApiJson<?> responseApiJson = casesService.addCasesDefinition(casesDefinition);
        logger.info("#################### End addCasesDefinition ####################");
        return responseApiJson;
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/updateCasesDefinition")
    public ResponseApiJson<?> updateCasesDefinition(@RequestBody List<CasesDefinition> casesDefinition) {
        logger.info("#################### Star updateCasesDefinition ####################");
        ResponseApiJson<?> responseApiJson = casesService.updateCasesDefinition(casesDefinition);
        logger.info("#################### End updateCasesDefinition####################");
        return responseApiJson;
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/deleteCasesDefinition")
    public ResponseApiJson<?> deleteCasesDefinition(@RequestBody CasesDefinitionId id) {
        logger.info("#################### Star deleteCasesDefinition ####################");
        ResponseApiJson<?> responseApiJson = casesService.deleteCasesDefinition(id);
        logger.info("#################### End deleteCasesDefinition ####################");
        return responseApiJson;
    }
}
