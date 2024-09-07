package com.simulator.pos.controllers;

import com.simulator.entities.SubfldDefinition;
import com.simulator.entities.SubfldDefinitionId;
import com.simulator.entities.pos.PosSubfldDefinition;
import com.simulator.entities.pos.PosSubfldDefinitionId;
import com.simulator.globalModels.ResponseApiJson;
import com.simulator.pos.services.PosSubFldDefService;
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
@RequestMapping("/api/su/pos/subFldDef")
@RequiredArgsConstructor
public class PosSubFldDefController {
    private final Logger logger = LogManager.getLogger(PosSubFldDefController.class);
    @Autowired
    PosSubFldDefService subFldDefService;

    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/getAllSubfldDefinitions")
    public ResponseApiJson<List<PosSubfldDefinition>> getAllSubfldDefinitions() {
        logger.info("#################### Star getAllSubfldDefinitions ####################");
        ResponseApiJson<List<PosSubfldDefinition>> responseApiJson = subFldDefService.getAllSubfldDefinitions();
        return responseApiJson;
    }


    @PostMapping("/getSubfldDefinitionsByProtocols")
    public ResponseApiJson<List<PosSubfldDefinition>> getSubfldDefinitionsByProtocols(@RequestBody PosSubfldDefinitionId id) {
        logger.info("#################### Star getSubfldDefinitionsByProtocols ####################");
        ResponseApiJson<List<PosSubfldDefinition>> responseApiJson = subFldDefService.getSubfldDefinitionsByProtocols(id);
        return responseApiJson;
    }



    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/getOneSubfldDefinition")
    public ResponseApiJson<List<PosSubfldDefinition>> getOneSubfldDefinition(@RequestBody PosSubfldDefinitionId id) {
        logger.info("####################Star getOneSubfldDefinition ####################");
        ResponseApiJson<List<PosSubfldDefinition>> responseApiJson = subFldDefService.getOneSubfldDefinition(id);
        logger.info("#################### End getOneSubfldDefinition####################");
        return responseApiJson;
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/addSubfldDefinition")
    public ResponseApiJson<?> addSubfldDefinition(@RequestBody PosSubfldDefinition mccDef) {
        logger.info("####################Star addSubfldDefinition ####################");
        ResponseApiJson<?> responseApiJson = subFldDefService.addSubfldDefinition(mccDef);
        logger.info("#################### End addSubfldDefinition ####################");
        return responseApiJson;

    }
    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/updateSubfldDefinition")
    public ResponseApiJson<?> updateMtiDef(@RequestBody PosSubfldDefinition mccDef) throws IllegalAccessException {
        logger.info("#################### Star updateSubfldDefinition ####################");
        ResponseApiJson<?> responseApiJson = subFldDefService.updateSubfldDefinition(mccDef);
        logger.info("#################### End updateSubfldDefinition ####################");
        return responseApiJson;


    }

    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/deleteSubfldDefinition")
    public ResponseApiJson<?> deleteSubfldDefinition(@RequestBody PosSubfldDefinitionId id) {
        logger.info("#################### Star deleteSubfldDefinition ####################");
        ResponseApiJson<?> responseApiJson = subFldDefService.deleteSubfldDefinition(id);
        logger.info("#################### End deleteSubfldDefinition ####################");
        return responseApiJson;
    }
}





