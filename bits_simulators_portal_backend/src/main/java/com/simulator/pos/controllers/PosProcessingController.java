package com.simulator.pos.controllers;


import com.simulator.entities.pos.PosProcessingDef;
import com.simulator.entities.pos.PosProcessingDefId;
import com.simulator.globalModels.ResponseApiJson;
import com.simulator.pos.services.PosProcessingService;
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
@RequestMapping("/api/su/pos/processingDef")
@RequiredArgsConstructor
class PosProcessingController {
    private final Logger logger = LogManager.getLogger(PosProcessingController.class);
    @Autowired
    PosProcessingService processingService;



    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/getAllProcessingDefs")
    public ResponseApiJson<List<PosProcessingDef>> getAllProcessingDef() {
        logger.info("#################### Star getAllProcessingDefs ####################");
        ResponseApiJson<List<PosProcessingDef>> responseApiJson = processingService.getAllProcessingDefs();
        return responseApiJson;
    }



    @PostMapping("/getProcessingDefsByProtocol")
    public ResponseApiJson<List<PosProcessingDef>> getProcessingDefByProtocol(@RequestBody PosProcessingDefId id) {
        logger.info("#################### Star getProcessingDefByProtocol ####################");
        logger.info("test id :::>"+id);
        ResponseApiJson<List<PosProcessingDef>> responseApiJson = processingService.getProcessingDefByProtocol(id);
        return responseApiJson;
    }


    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/getOneProcessingDef")
    public ResponseApiJson<List<PosProcessingDef>> getOneProcessingDef(@RequestBody PosProcessingDefId id) {
        logger.info("####################Star getOneProcessingDef ####################");
        ResponseApiJson<List<PosProcessingDef>> responseApiJson = processingService.getOneProcessingDef(id);
        logger.info("#################### End getOneProcessingDef####################");
        return responseApiJson;
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/addProcessingDef")
    public ResponseApiJson<?> addProcessingDef(@RequestBody PosProcessingDef processingDef) {
        logger.info("####################Star addProcessingDef ####################");
        ResponseApiJson<?> responseApiJson = processingService.addProcessingDef(processingDef);
        logger.info("#################### End addProcessingDef ####################");
        return responseApiJson;

    }

    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/updateProcessingDef")
    public ResponseApiJson<?> updateProcessingDef(@RequestBody PosProcessingDef processingDef) throws IllegalAccessException {
        logger.info("#################### Star updateProcessingDef ####################");
        ResponseApiJson<?> responseApiJson = processingService.updateProcessingDef(processingDef);
        logger.info("#################### End updateProcessingDef ####################");
        return responseApiJson;


    }

    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/deleteProcessingDef")
    public ResponseApiJson<?> deleteProcessingDef(@RequestBody PosProcessingDefId id) {
        logger.info("#################### Star deleteProcessingDef ####################");
        ResponseApiJson<?> responseApiJson = processingService.deleteProcessingDef(id);
        logger.info("#################### End deleteProcessingDef ####################");
        return responseApiJson;
    }
}



