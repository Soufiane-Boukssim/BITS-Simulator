package com.simulator.atm.controllers.controllerscript;

import com.simulator.atm.dto.SenarioAtmDto;
import com.simulator.atm.services.script.SenarioAtmService;
import com.simulator.globalModels.ResponseApiJson;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/senario")
public class SenarioAtmController {
    private final Logger logger = LogManager.getLogger(ScriptCasesDefinitionController.class);

    @Autowired
    private SenarioAtmService senarioService;

    @PostMapping("/add")
    public ResponseApiJson<SenarioAtmDto> addSenario(@RequestBody SenarioAtmDto senarioRequestDto) {
       // logger.info("#################### Start addSenario ####################");
        ResponseApiJson<SenarioAtmDto> responseApiJson = senarioService.addSenario(senarioRequestDto);
        logger.info("#################### End addSenario ####################");
        return responseApiJson;
    }

    @GetMapping("/all/{bankCode}")
    public ResponseApiJson<List<SenarioAtmDto>> getAllSenarios(@PathVariable String bankCode) {
        logger.info("#################### Start getAllSenarios ####################");
        ResponseApiJson<List<SenarioAtmDto>> responseApiJson = senarioService.getAllSenarios(bankCode);
        logger.info("#################### End getAllSenarios ####################");
        return responseApiJson;
    }

    @GetMapping("/get/{id}")
    public ResponseApiJson<SenarioAtmDto> getSenario(@PathVariable Long id) {
        logger.info("#################### Start getSenario ####################");
        ResponseApiJson<SenarioAtmDto> responseApiJson = senarioService.getSenarioById(id);
        logger.info("#################### End getSenario ####################");
        return responseApiJson;
    }
    @PutMapping("/update/{id}")
    public ResponseApiJson<SenarioAtmDto> updateSenario(@PathVariable Long id, @RequestBody SenarioAtmDto senarioRequestDto) {
        logger.info("#################### Start updateSenario ####################");
        ResponseApiJson<SenarioAtmDto> responseApiJson = senarioService.updateSenario(id, senarioRequestDto);
        logger.info("#################### End updateSenario ####################");
        return responseApiJson;
    }
    @DeleteMapping("/delete/{id}")
    public ResponseApiJson<Void> deleteSenario(@PathVariable Long id) {
        logger.info("#################### Start deleteSenario ####################");
        ResponseApiJson<Void> responseApiJson = senarioService.deleteSenario(id);
        logger.info("#################### End deleteSenario ####################");
        return responseApiJson;
    }
}
