package com.simulator.iso.controllers;

import com.simulator.entities.CasesDefinition;
import com.simulator.entities.CasesDefinitionId;
import com.simulator.globalModels.ResponseApiJson;
import com.simulator.iso.services.CasesService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/su/casesDefinition")
@RequiredArgsConstructor
public class CasesController {
    private final Logger logger = LogManager.getLogger(CasesController.class);
    @Autowired
    CasesService casesService;


    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/get")
    public ResponseEntity<CasesDefinition> getCasesDefinition(@RequestBody CasesDefinitionId id) {
        try {
            logger.info("test 1");
            CasesDefinition casesDefinition = casesService.getCasesDefinition(id);
            if (casesDefinition != null) {
                logger.info("test 2");
                return new ResponseEntity<>(casesDefinition , HttpStatus.OK);
            } else {
                logger.info("test 3");
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            logger.info("test 4");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


  /*  @SecurityRequirement(name = "Bearer Authentication")
    @GetMapping("/cases/filter")
    public List<CasesDefinition> getFilteredCasesList(
            @RequestParam String bankCode,
            @RequestParam Character caseProtocole) {
        return casesService.getFilteredCasesList(bankCode, caseProtocole);
    }*/



    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/getAllCasesDefinitions/filter")
    public   List<CasesDefinition> getFilteredCasesList(
            @RequestBody CasesDefinitionId id) {
        return casesService.getFilteredCasesList(id.getBankCode(), id.getCaseProtocole());
    }

   /* @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/getAllCasesDefinitions")
    public ResponseApiJson<List<CasesDefinition>> getAllCasesDefinition() {
        logger.info("#################### Star getAllCasesDefinitions ####################");
        ResponseApiJson<List<CasesDefinition>> responseApiJson = casesService.getAllCasesDefinitions();
        return responseApiJson;
    }*/

    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/getOneCasesDefinition")
    public ResponseApiJson<List<CasesDefinition>> getOneCasesDefinition(@RequestBody CasesDefinitionId id) {
        logger.info("####################Star getOneCasesDefinition ####################");
        ResponseApiJson<List<CasesDefinition>> responseApiJson = casesService.getOneCasesDefinition(id);
        logger.info("#################### End getOneCasesDefinition####################");
        return responseApiJson;
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/addCasesDefinition")
    public ResponseApiJson<?> addCasesDefinition(@RequestBody CasesDefinition casesDefinition) {
        logger.info("####################Star addCasesDefinition ####################");
        ResponseApiJson<?> responseApiJson = casesService.addCasesDefinition(casesDefinition);
        logger.info("#################### End addCasesDefinition ####################");
        return responseApiJson;

    }

    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/updateCasesDefinition")
    public ResponseApiJson<?> updateCasesDefinition(@RequestBody CasesDefinition casesDefinition) throws IllegalAccessException {
        logger.info("#################### Star updateCasesDefinition ####################");
        ResponseApiJson<?> responseApiJson = casesService.updateCasesDefinition(casesDefinition);
        logger.info("#################### End updateCasesDefinition####################");
        return responseApiJson;


    }

    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/deleteCasesDefinition")
    public ResponseApiJson<?> deleteMtiDef(@RequestBody CasesDefinitionId id) {
        logger.info("#################### Star deleteCasesDefinition ####################");
        ResponseApiJson<?> responseApiJson = casesService.deleteCasesDefinition(id);
        logger.info("#################### End deleteCasesDefinition ####################");
        return responseApiJson;
    }
}



