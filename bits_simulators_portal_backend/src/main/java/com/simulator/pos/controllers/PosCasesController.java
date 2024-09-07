package com.simulator.pos.controllers;


import com.simulator.dtos.pos.PosCasesDefDTO;
import com.simulator.dtos.pos.PosCasesDefinitionDTO;
import com.simulator.entities.pos.PosCasesDefinition;
import com.simulator.entities.pos.PosCasesDefinitionId;
import com.simulator.globalModels.ResponseApiJson;
import com.simulator.pos.services.PosCasesService;
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
@RequestMapping("/api/su/pos/casesDefinition")
public class PosCasesController {
    private final Logger logger = LogManager.getLogger(PosCasesController.class);
    private final PosCasesService posCasesService;

    @Autowired
    public PosCasesController(PosCasesService posCasesService) {
        this.posCasesService = posCasesService;
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/getAllCasesDefinitions")
    public ResponseApiJson<List<PosCasesDefDTO>> getAllCasesDefinitions(@RequestBody PosCasesDefinitionId id ) {
        logger.info("#################### Start getAllCasesDefinitions ####################");
        ResponseApiJson<List<PosCasesDefDTO>> responseApiJson = posCasesService.getAllCasesDefinitions(id);
        logger.info("list size : " + responseApiJson.getResult().size());
        logger.info("#################### end getAllCasesDefinitions ####################");
        return responseApiJson ;
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/getCaseDefinitions")
    public ResponseApiJson<Map<String, PosCasesDefinitionDTO>> getCaseDefinitions(@RequestBody PosCasesDefinitionId id ) {
        logger.info("#################### Start getCaseDefinitions ####################");
        ResponseApiJson<Map<String, PosCasesDefinitionDTO>> responseApiJson = posCasesService.getCaseDefinition(id);
        logger.info("list size : " + responseApiJson.getResult().size());
        logger.info("#################### end getCaseDefinitions ####################");
        return responseApiJson;
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/addCasesDefinition")
    public ResponseApiJson<?> addCasesDefinition(@RequestBody List<PosCasesDefinition> casesDefinition) {
        logger.info("####################Star addCasesDefinition ####################");
        ResponseApiJson<?> responseApiJson = posCasesService.addCasesDefinition(casesDefinition);
        logger.info("#################### End addCasesDefinition ####################");
        return responseApiJson;
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/updateCasesDefinition")
    public ResponseApiJson<?> updateCasesDefinition(@RequestBody List<PosCasesDefinition> casesDefinition) {
        logger.info("#################### Star updateCasesDefinition ####################");
        ResponseApiJson<?> responseApiJson = posCasesService.updateCasesDefinition(casesDefinition);
        logger.info("#################### End updateCasesDefinition####################");
        return responseApiJson;
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/deleteCasesDefinition")
    public ResponseApiJson<?> deleteCasesDefinition(@RequestBody PosCasesDefinitionId id) {
        logger.info("#################### Star deleteCasesDefinition ####################");
        ResponseApiJson<?> responseApiJson = posCasesService.deleteCasesDefinition(id);
        logger.info("#################### End deleteCasesDefinition ####################");
        return responseApiJson;
    }
}
