package com.simulator.atm.controllers.controlleratm;

import com.simulator.atm.services.atmm.CommsDefinitionService;
import com.simulator.entities.CommsDefinition;
import com.simulator.entities.CommsDefinitionId;
import com.simulator.globalModels.ResponseApiJson;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/su/communication")
@SecurityRequirement(name = "Bearer Authentication")
public class CommsDefinitionController {

    private static final Logger logger = LogManager.getLogger(CommsDefinitionController.class);

    private final CommsDefinitionService commsDefinitionService;

    @Autowired
    public CommsDefinitionController(CommsDefinitionService commsDefinitionService) {
        this.commsDefinitionService = commsDefinitionService;
    }

    // Endpoint to retrieve a communication definition
    @PostMapping("/communications")
    public ResponseApiJson<?> getComms(@RequestBody CommsDefinitionId commsDefinitionId) {
       // logger.info("Received request to get communication definition with ID: {}", commsDefinitionId);
        ResponseApiJson<?> response = commsDefinitionService.getAllCommsParams(commsDefinitionId);
        logger.info("Completed processing getComms request.");
        return response;
    }
    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/getOneCommsParam")
    public ResponseApiJson<List<CommsDefinition>> getOneCommsParam(@RequestBody CommsDefinitionId id) {
        logger.info("####################Star getOneCommsParam ####################");
        ResponseApiJson<List<CommsDefinition>> responseApiJson = commsDefinitionService.getOneCommsParam(id);
        logger.info("#################### End getOneCommsParam####################");
        return responseApiJson;
    }

    // Endpoint to add a new communication definition
    @PostMapping("/add")
    public ResponseApiJson<?> addComms(@RequestBody CommsDefinition commsDefinition) {
        logger.info("Received request to add new communication definition: {}", commsDefinition);
        ResponseApiJson<?> response = commsDefinitionService.addCommsParam(commsDefinition);
        logger.info("Completed processing addComms request.");
        return response;
    }

    // Endpoint to update an existing communication definition
    @PostMapping("/update")
    public ResponseApiJson<?> updateComms(@RequestBody CommsDefinition commsDefinition) throws IllegalAccessException {
        logger.info("Received request to update communication definition: {}", commsDefinition);
        ResponseApiJson<?> response = commsDefinitionService.updateCommsParam(commsDefinition);
        logger.info("Completed processing updateComms request.");
        return response;
    }

    // Endpoint to delete a communication definition
    @PostMapping("/delete")
    public ResponseApiJson<?> deleteComms(@RequestBody CommsDefinitionId commsDefinitionId) {
        logger.info("Received request to delete communication definition with ID: {}", commsDefinitionId);
        ResponseApiJson<?> response = commsDefinitionService.deleteCommsParam(commsDefinitionId);
        logger.info("Completed processing deleteComms request.");
        return response;
    }
}
