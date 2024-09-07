package com.simulator.atm.controllers.controlleratm;

import com.simulator.atm.services.atmm.TerminalMessNonsoliciteService;
import com.simulator.entities.TerminalMessNonSoliciteId;
import com.simulator.entities.TerminalMessNonsolicite;
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
@RequestMapping("api/su/terminalMessNonsolicite") // Adjust the request mapping
@SecurityRequirement(name = "Bearer Authentication")
public class TerminalMessNonsoliciteController {

    private static final Logger logger = LogManager.getLogger(TerminalMessNonsoliciteController.class);

    private final TerminalMessNonsoliciteService terminalMessNonsoliciteService; // Use the new service

    @Autowired
    public TerminalMessNonsoliciteController(TerminalMessNonsoliciteService terminalMessNonsoliciteService) {
        this.terminalMessNonsoliciteService = terminalMessNonsoliciteService;
    }

    // Endpoint to retrieve a Terminal Mess Nonsolicite
    @PostMapping("/get")
    public ResponseApiJson<?> getTerminalMessNonsolicite(@RequestBody TerminalMessNonSoliciteId id) {
        logger.info("Received request to get Terminal Mess Nonsolicite with ID: {}", id);
        ResponseApiJson<?> response = terminalMessNonsoliciteService.getTerminalMessNonsolicite(id);
        logger.info("Completed processing getTerminalMessNonsolicite request.");
        return response;
    }





    // Endpoint to add a new Terminal Mess Nonsolicite
    @PostMapping("/add")
    public ResponseApiJson<?> addTerminalMessNonsolicite(@RequestBody TerminalMessNonsolicite terminalMessNonsolicite) {
        logger.info("Received request to add new Terminal Mess Nonsolicite: {}", terminalMessNonsolicite);
        ResponseApiJson<?> response = terminalMessNonsoliciteService.addTerminalMessNonsolicite(terminalMessNonsolicite);
        logger.info("Completed processing addTerminalMessNonsolicite request.");
        return response;
    }

    // Endpoint to update a Terminal Mess Nonsolicite
    @PostMapping("/update")
    public ResponseApiJson<?> updateTerminalMessNonsolicite(@RequestBody List<TerminalMessNonsolicite> terminalMessNonsolicite) {
        logger.info("Received request to update Terminal Mess Nonsolicite: {}", terminalMessNonsolicite);
        ResponseApiJson<?> response = terminalMessNonsoliciteService.updateTerminalMessNonsolicite(terminalMessNonsolicite);
        logger.info("Completed processing updateTerminalMessNonsolicite request.");
        return response;
    }




    // Endpoint to delete a Terminal Mess Nonsolicite
    @PostMapping("/delete")
    public ResponseApiJson<?> deleteTerminalMessNonsolicite(@RequestBody TerminalMessNonSoliciteId id) {
        logger.info("Received request to delete Terminal Mess Nonsolicite with ID: {}", id);
        ResponseApiJson<?> response = terminalMessNonsoliciteService.deleteTerminalMessNonsolicite(id);
        logger.info("Completed processing deleteTerminalMessNonsolicite request.");
        return response;
    }
}
