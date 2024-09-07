package com.simulator.iso.controllers;

import com.simulator.entities.ProcessingDef;
import com.simulator.entities.ProcessingDefId;
import com.simulator.globalModels.ResponseApiJson;
import com.simulator.iso.services.ProcessingService;
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
@RequestMapping("/api/su/processingDef")
@RequiredArgsConstructor
class ProcessingController {
    private final Logger logger = LogManager.getLogger(ProcessingController.class);
    @Autowired
    ProcessingService processingService;


    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/getAllProcessingDefs")
    public ResponseApiJson<List<ProcessingDef>> getAllProcessingDef() {
        logger.info("#################### Star getAllProcessingDefs ####################");
        ResponseApiJson<List<ProcessingDef>> responseApiJson = processingService.getAllProcessingDefs();
        return responseApiJson;
    }



    @PostMapping("/getProcessingDefsByProtocol")
    public ResponseApiJson<List<ProcessingDef>> getProcessingDefByProtocol(@RequestBody ProcessingDefId id) {
        logger.info("#################### Star getProcessingDefByProtocol ####################");
        logger.info("test id :::>"+id);
        ResponseApiJson<List<ProcessingDef>> responseApiJson = processingService.getProcessingDefByProtocol(id);
        return responseApiJson;
    }


    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/getOneProcessingDef")
    public ResponseApiJson<List<ProcessingDef>> getOneProcessingDef(@RequestBody ProcessingDefId id) {
        logger.info("####################Star getOneProcessingDef ####################");
        ResponseApiJson<List<ProcessingDef>> responseApiJson = processingService.getOneProcessingDef(id);
        logger.info("#################### End getOneProcessingDef####################");
        return responseApiJson;
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/addProcessingDef")
    public ResponseApiJson<?> addProcessingDef(@RequestBody ProcessingDef processingDef) {
        logger.info("####################Star addProcessingDef ####################");
        ResponseApiJson<?> responseApiJson = processingService.addProcessingDef(processingDef);
        logger.info("#################### End addProcessingDef ####################");
        return responseApiJson;

    }

    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/updateProcessingDef")
    public ResponseApiJson<?> updateProcessingDef(@RequestBody ProcessingDef processingDef) throws IllegalAccessException {
        logger.info("#################### Star updateProcessingDef ####################");
        ResponseApiJson<?> responseApiJson = processingService.updateProcessingDef(processingDef);
        logger.info("#################### End updateProcessingDef ####################");
        return responseApiJson;


    }

    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/deleteProcessingDef")
    public ResponseApiJson<?> deleteProcessingDef(@RequestBody ProcessingDefId id) {
        logger.info("#################### Star deleteProcessingDef ####################");
        ResponseApiJson<?> responseApiJson = processingService.deleteProcessingDef(id);
        logger.info("#################### End deleteProcessingDef ####################");
        return responseApiJson;
    }
}



