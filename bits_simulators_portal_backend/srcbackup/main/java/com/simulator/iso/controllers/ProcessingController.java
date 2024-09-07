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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    @PostMapping("/get")
    public ResponseEntity<ProcessingDef> getProcessingDef(@RequestBody ProcessingDefId id) {
        try {
            logger.info("test 1");
            ProcessingDef processingDef = processingService.getProcessingDef(id);
            if (processingDef != null) {
                logger.info("test 2");
                return new ResponseEntity<>(processingDef , HttpStatus.OK);
            } else {
                logger.info("test 3");
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            logger.info("test 4");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

   /* @SecurityRequirement(name = "Bearer Authentication")
    @GetMapping("/mti/filter")
    public List<MtiDef> getFilteredMtiList(
            @RequestParam String bankCode,
            @RequestParam String mtiProtocol) {
        return mtiService.getFilteredMtiList(bankCode, mtiProtocol);
    }*/



    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/getAllProcessingDefs/filter")
    public   List<ProcessingDef> getFilteredProcessingList(
            @RequestBody ProcessingDefId id) {
        return processingService.getFilteredProcessingList(id.getBankCode(), id.getProcProtocol());
    }

   /* @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/getAllProcessingDefs")
    public ResponseApiJson<List<ProcessingDef>> getAllProcessingDef() {
        logger.info("#################### Star getAllProcessingDefs ####################");
        ResponseApiJson<List<ProcessingDef>> responseApiJson = processingService.getAllProcessingDefs();
        return responseApiJson;
    }*/

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



