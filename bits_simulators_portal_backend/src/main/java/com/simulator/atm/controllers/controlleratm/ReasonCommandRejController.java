package com.simulator.atm.controllers.controlleratm;

import com.simulator.atm.services.atmm.ReasonCommandRejService;
import com.simulator.entities.ReasonCommandRej;
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
@RequestMapping("api/su/reasonCommandRej")
@SecurityRequirement(name = "Bearer Authentication")
public class ReasonCommandRejController {

    private static final Logger logger = LogManager.getLogger(ReasonCommandRejController.class);

    private final ReasonCommandRejService reasonCommandRejService;

    @Autowired
    public ReasonCommandRejController(ReasonCommandRejService reasonCommandRejService) {
        this.reasonCommandRejService = reasonCommandRejService;
    }

    // Endpoint to add a new Reason Command Rejection
    @PostMapping("/add")
    public ResponseApiJson<?> addReasonCommandRej(@RequestBody ReasonCommandRej reasonCommandRej) {
       // logger.info("Received request to add new Reason Command Rejection: {}", reasonCommandRej);
        ResponseApiJson<?> response = reasonCommandRejService.addReasonCommandRej(reasonCommandRej);
        logger.info("Completed processing addReasonCommandRej request.");
        return response;
    }

    // Endpoint to retrieve a Reason Command Rejection
    @PostMapping("/get")
    public ResponseApiJson<?> getReasonCommandRej(@RequestBody TerminalDefinitionId id) { // Assuming TerminalDefinitionId is used as a filter or identifier
        logger.info("Received request to get Reason Command Rejection with ID: {}", id);
        ResponseApiJson<?> response = reasonCommandRejService.getReasonCommandRej(id);
        logger.info("Completed processing getReasonCommandRej request.");
        return response;
    }

    // Additional endpoints for update or delete operations can be added here if they are supported by ReasonCommandRejService
}
