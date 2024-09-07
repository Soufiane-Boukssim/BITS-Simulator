package com.simulator.smartICC.controllers;


import com.simulator.atm.controllers.controlleratm.LogNDCController;
import com.simulator.globalModels.ResponseApiJson;
import com.simulator.smartICC.models.GetTagsDefinition;
//import com.simulator.smartICC.services.GetTagsDefinitionService;
import com.simulator.smartICC.services.GetTagsDefinitionService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api/get-tags-definition")
@SecurityRequirement(name = "Bearer Authentication")
public class GetTagsDefinitionController {

    private final Logger logger = LogManager.getLogger(LogNDCController.class);
    @Autowired
    private GetTagsDefinitionService getTagsDefinitionService;



    @PostMapping("/getTagDefinitionByCodeProfile")
    public ResponseApiJson<List<GetTagsDefinition>> getTagDefinitionByCodeProfile(@RequestBody Map<String, String> requestBody) {
        try {
            String codeProfile = requestBody.get("code_profil");
            logger.info("---->codeProfile api :"+codeProfile);
            ResponseApiJson<List<GetTagsDefinition>> tagDefinitionResponse = getTagsDefinitionService.getTagDefinitionByCodeProfile(codeProfile);
            logger.info("---->tagDefinitionResponse api :"+tagDefinitionResponse);
            return tagDefinitionResponse;
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseApiJson<>("error", "An error occurred while retrieving tag definition", null);
        }
    }


    @PostMapping("/insert")
    public ResponseApiJson<List<GetTagsDefinition>> saveTagDefinition(@RequestBody List<GetTagsDefinition> requestBody) {
        try {
            ResponseApiJson<List<GetTagsDefinition>> tagDefinitionResponse = getTagsDefinitionService.saveTagDefinition(requestBody);
            return tagDefinitionResponse;
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseApiJson<>("error", "An error occurred while saving tag definition", null);
        }
    }


    @PostMapping("/delete")
    public ResponseApiJson<List<GetTagsDefinition>> deleteTagDefinition(@RequestBody List<GetTagsDefinition> requestBody) {
        try {
            ResponseApiJson<List<GetTagsDefinition>> response = getTagsDefinitionService.deleteTagDefinition(requestBody);
            return response;
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseApiJson<>("error", "An error occurred while deleting Tag Definition service", null);
        }
    }




}
