package com.simulator.iso.controllers;

import com.simulator.entities.TerminalParam;
import com.simulator.entities.TerminalParamId;
import com.simulator.globalModels.ResponseApiJson;
import com.simulator.iso.services.TerminalService;
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
@RequestMapping("/api/su/terminalParam")
@RequiredArgsConstructor
public class TerminalController {
    private final Logger logger = LogManager.getLogger(TerminalController.class);
    @Autowired
    TerminalService terminalService;




  /*  @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/get")
    public ResponseEntity<TerminalParam> getTerminalParam(@RequestBody TerminalParamId id) {
        try {
            logger.info("test 1");
            TerminalParam terminalParam = posTerminalService.getTerminalParam(id);
            if (terminalParam != null) {
                logger.info("test 2");
                return new ResponseEntity<>(terminalParam , HttpStatus.OK);
            } else {
                logger.info("test 3");
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            logger.info("test 4");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }*/


   /* @SecurityRequirement(name = "Bearer Authentication")
    @GetMapping("/mti/filter")
    public List<MtiDef> getFilteredMtiList(
            @RequestParam String bankCode,
            @RequestParam String mtiProtocol) {
        return posMtiService.getFilteredMtiList(bankCode, mtiProtocol);
    }*/



  /*  @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/getAllTerminalParams/filter")
    public   List<TerminalParam> getFilteredTerminalList(
            @RequestBody TerminalParamId id) {
        return posTerminalService.getFilteredTerminalList(id.getBankCode(), id.getTerProtocol());
    }*/

    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/getAllTerminalParams")
    public ResponseApiJson<List<TerminalParam>> getAllTerminalParams( @RequestBody TerminalParamId id) {
        logger.info("#################### Star getAllTerminalParams ####################");
        ResponseApiJson<List<TerminalParam>> responseApiJson = terminalService.getAllTerminalParams(id);
        return responseApiJson;
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/getOneTerminalParam")
    public ResponseApiJson<List<TerminalParam>> getOneTerminalParam(@RequestBody TerminalParamId id) {
        logger.info("####################Star getOneTerminalParam ####################");
        ResponseApiJson<List<TerminalParam>> responseApiJson = terminalService.getOneTerminalParam(id);
        logger.info("#################### End getOneTerminalParam####################");
        return responseApiJson;
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/addTerminalParam")
    public ResponseApiJson<?> addTerminalParam(@RequestBody TerminalParam terminalParam) {
        logger.info("####################Star addTerminalParam ####################");
        ResponseApiJson<?> responseApiJson = terminalService.addTerminalParam(terminalParam);
        logger.info("#################### End addTerminalParam ####################");
        return responseApiJson;
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/updateTerminalParam")
    public ResponseApiJson<?> updateTerminalParam(@RequestBody TerminalParam terminalParam) throws IllegalAccessException {
        logger.info("#################### Star updateTerminalParam ####################");
        ResponseApiJson<?> responseApiJson = terminalService.updateTerminalParam(terminalParam);
        logger.info("#################### End updateTerminalParam ####################");
        return responseApiJson;


    }

    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/deleteTerminalParam")
    public ResponseApiJson<?> deleteTerminalParam(@RequestBody TerminalParamId id) {
        logger.info("#################### Star deleteTerminalParam ####################");
        logger.info("id====>+"+id);
        ResponseApiJson<?> responseApiJson = terminalService.deleteTerminalParam(id);
        logger.info("#################### End deleteTerminalParam ####################");
        return responseApiJson;
    }
}



