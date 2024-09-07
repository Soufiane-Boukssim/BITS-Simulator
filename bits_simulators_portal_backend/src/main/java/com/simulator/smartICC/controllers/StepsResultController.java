package com.simulator.smartICC.controllers;

import com.simulator.globalModels.ResponseApiJson;
import com.simulator.smartICC.services.StepsResultService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api/cpa-result")
@SecurityRequirement(name = "Bearer Authentication")
public class StepsResultController {

    @Autowired
    private StepsResultService stepsResultService;


    @PostMapping("/getResults")
    public ResponseApiJson<List<Map<String, Object>>> getGroupedTestCasesByCodeProfile(@RequestBody Map<String, String> requestBody) {
        try {
            String codeProfile = requestBody.get("code_profil");
//            logger.info("---->codeProfile api :"+codeProfile);
            ResponseApiJson<List<Map<String, Object>>>  tagDefinitionResponse = stepsResultService.getTestResultByCodeProfile(codeProfile);
//            logger.info("---->tagDefinitionResponse api :"+tagDefinitionResponse);
            return tagDefinitionResponse;
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseApiJson<>("error", "An error occurred while retrieving results", null);
        }
    }

}
