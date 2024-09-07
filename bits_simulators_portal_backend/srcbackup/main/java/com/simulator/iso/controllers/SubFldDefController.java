package com.simulator.iso.controllers;

import com.simulator.entities.SubfldDefinition;
import com.simulator.entities.SubfldDefinitionId;
import com.simulator.globalModels.ResponseApiJson;
import com.simulator.iso.services.SubFldDefService;
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
@RequestMapping("/api/su/subFldDef")
@RequiredArgsConstructor
public class SubFldDefController {
    private final Logger logger = LogManager.getLogger(SubFldDefController.class);
    @Autowired
    SubFldDefService subFldDefService;
    
    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/getAllSubfldDefinitions")
    public ResponseApiJson<List<SubfldDefinition>> getAllSubfldDefinitions() {
        logger.info("#################### Star getAllSubfldDefinitions ####################");
        ResponseApiJson<List<SubfldDefinition>> responseApiJson = subFldDefService.getAllSubfldDefinitions();
        return responseApiJson;
    }
    


    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/getOneSubfldDefinition")
    public ResponseApiJson<List<SubfldDefinition>> getOneSubfldDefinition(@RequestBody SubfldDefinitionId id) {
        logger.info("####################Star getOneSubfldDefinition ####################");
        ResponseApiJson<List<SubfldDefinition>> responseApiJson = subFldDefService.getOneSubfldDefinition(id);
        logger.info("#################### End getOneSubfldDefinition####################");
        return responseApiJson;
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/addSubfldDefinition")
    public ResponseApiJson<?> addSubfldDefinition(@RequestBody SubfldDefinition mccDef) {
        logger.info("####################Star addSubfldDefinition ####################");
        ResponseApiJson<?> responseApiJson = subFldDefService.addSubfldDefinition(mccDef);
        logger.info("#################### End addSubfldDefinition ####################");
        return responseApiJson;

    }
    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/updateSubfldDefinition")
    public ResponseApiJson<?> updateMtiDef(@RequestBody SubfldDefinition mccDef) throws IllegalAccessException {
        logger.info("#################### Star updateSubfldDefinition ####################");
        ResponseApiJson<?> responseApiJson = subFldDefService.updateSubfldDefinition(mccDef);
        logger.info("#################### End updateSubfldDefinition ####################");
        return responseApiJson;


    }

    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/deleteSubfldDefinition")
    public ResponseApiJson<?> deleteSubfldDefinition(@RequestBody SubfldDefinitionId id) {
        logger.info("#################### Star deleteSubfldDefinition ####################");
        ResponseApiJson<?> responseApiJson = subFldDefService.deleteSubfldDefinition(id);
        logger.info("#################### End deleteSubfldDefinition ####################");
        return responseApiJson;
    }
}





