package com.simulator.smartICC.controllers;


import com.simulator.atm.controllers.controlleratm.LogNDCController;
import com.simulator.globalModels.ResponseApiJson;
import com.simulator.smartICC.models.TestCases;
import com.simulator.smartICC.services.TestCaseService;
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
@RequestMapping("/api/test-cases")
@SecurityRequirement(name = "Bearer Authentication")
public class TestCaseController {


    @Autowired
    private TestCaseService testCaseService;
    private final Logger logger = LogManager.getLogger(LogNDCController.class);


    @PostMapping("/getTestCasesByCodeProfile")
    public ResponseApiJson<List<TestCases>> getTestCasesByCodeProfile(@RequestBody Map<String, String> requestBody) {
        try {
            String codeProfile = requestBody.get("code_profil");
//            logger.info("---->codeProfile api :"+codeProfile);
            ResponseApiJson<List<TestCases>> tagDefinitionResponse = testCaseService.getTestCasesByCodeProfile(codeProfile);
//            logger.info("---->tagDefinitionResponse api :"+tagDefinitionResponse);
            return tagDefinitionResponse;
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseApiJson<>("error", "An error occurred while retrieving tag definition", null);
        }
    }


    @PostMapping("/insert")
    public ResponseApiJson<List<TestCases>> saveTestCases(@RequestBody List<TestCases> requestBody) {
        try {
            ResponseApiJson<List<TestCases>> reponse = testCaseService.saveTestCases(requestBody);
            return reponse;
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseApiJson<>("error", "An error occurred while saving tag definition", null);
        }
    }


    @PostMapping("/delete")
    public ResponseApiJson<String> deleteCPAProfile(@RequestBody Map<String, String> requestBody) {
        try {
            String code_profil = requestBody.get("code_profil");
            ResponseApiJson<String> response = testCaseService.deleteCasesProfile(code_profil);
            return response;
        } catch (Exception e) {
            e.printStackTrace(); // Ajoutez des logs pour voir l'erreur
            return new ResponseApiJson<>("error", "An error occurred while deleting case profile", null);
        }
    }


    @PostMapping("/getGroupedTestCasesByCodeProfile")
    public ResponseApiJson<List<Map<String, Object>>>  getGroupedTestCasesByCodeProfile(@RequestBody Map<String, String> requestBody) {
        try {
            String codeProfile = requestBody.get("code_profil");
//            logger.info("---->codeProfile api :"+codeProfile);
            ResponseApiJson<List<Map<String, Object>>>  tagDefinitionResponse = testCaseService.getGroupedTestCasesByCodeProfile(codeProfile);
//            logger.info("---->tagDefinitionResponse api :"+tagDefinitionResponse);
            return tagDefinitionResponse;
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseApiJson<>("error", "An error occurred while retrieving tag definition", null);
        }
    }


}
