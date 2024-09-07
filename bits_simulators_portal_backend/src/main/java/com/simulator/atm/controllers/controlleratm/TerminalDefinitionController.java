package com.simulator.atm.controllers.controlleratm;

import com.simulator.atm.model.DefinitionModel;
import com.simulator.atm.services.atmm.TerminalDefinitionService;
import com.simulator.entities.TerminalDefinitionId;
import com.simulator.globalModels.ResponseApiJson;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/su/terminalDefinition")
@SecurityRequirement(name = "Bearer Authentication")
public class TerminalDefinitionController {

    private static final Logger logger = LogManager.getLogger(TerminalDefinitionController.class);

    private final TerminalDefinitionService terminalDefinitionService;

    @Autowired
    public TerminalDefinitionController(TerminalDefinitionService terminalDefinitionService) {
        this.terminalDefinitionService = terminalDefinitionService;
    }

    // Endpoint to retrieve a communication definition
    @PostMapping("/get")
    public ResponseApiJson<?> getComms(@RequestBody TerminalDefinitionId terminalDefinitionId) {
       // logger.info("Received request to get communication definition with ID: {}", terminalDefinitionId);
        ResponseApiJson<?> response = terminalDefinitionService.getTerminalDefinition(terminalDefinitionId);
        logger.info("Completed processing getComms request.");
        return response;
    }

    // Endpoint to add a new communication definition
    @PostMapping("/add")
    public ResponseApiJson<?> addComms(@RequestBody DefinitionModel definitionModel) {
        logger.info("Received request to add new communication definition: {}", definitionModel);
        ResponseApiJson<?> response = terminalDefinitionService.addTerminalDefinition(definitionModel);
        logger.info("Completed processing addComms request.");
        return response;
    }


    // Endpoint to delete a communication definition
    /*@PostMapping("/delete")
    public ResponseApiJson<?> deleteComms(@RequestBody CommsDefinitionId commsDefinitionId) {
        logger.info("Received request to delete communication definition with ID: {}", commsDefinitionId);
        ResponseApiJson<?> response = commsDefinitionService.deleteComms(commsDefinitionId);
        logger.info("Completed processing deleteComms request.");
        return response;
    }*/
}
