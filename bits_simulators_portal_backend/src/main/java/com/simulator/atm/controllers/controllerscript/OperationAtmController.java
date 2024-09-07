package com.simulator.atm.controllers.controllerscript;

import com.simulator.atm.dto.OperationAtmDto;
import com.simulator.atm.services.script.OperationAtmService;
import com.simulator.globalModels.ResponseApiJson;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/senarioScripts")
public class OperationAtmController {

    private final Logger logger = LogManager.getLogger(ScriptCasesDefinitionController.class);
    @Autowired
    private OperationAtmService operationAtmService;

    @PostMapping("/add")
    public ResponseApiJson<OperationAtmDto> addSenarioScript(@RequestBody OperationAtmDto operationAtmDTO) {
       // logger.info("#################### Start addSenarioScript ####################");
       // logger.info("operationAtmDTO==>"+operationAtmDTO);
        ResponseApiJson<OperationAtmDto> responseApiJson = operationAtmService.addSenarioScript(operationAtmDTO);
       // logger.info("#################### End addSenarioScript ####################");
        return responseApiJson;
    }

    @GetMapping("/all/{bankCode}")
    public ResponseApiJson<List<OperationAtmDto>> getAllSenarioScriptsByBankCode(@PathVariable String bankCode) {
       // logger.info("#################### Start getAllSenarioScriptsByBankCode ####################");
        ResponseApiJson<List<OperationAtmDto>> responseApiJson = operationAtmService.getAllSenarioScripts(bankCode);
       // logger.info("#################### End getAllSenarioScriptsByBankCode ####################");
        return responseApiJson;
    }

    @PutMapping("/update/{id}")
    public ResponseApiJson<OperationAtmDto> updateSenarioScript(@PathVariable Long id, @RequestBody OperationAtmDto operationAtmDTO) {
       // logger.info("#################### Start updateSenarioScript ####################");
       // logger.info("id ===>"+id);
       // logger.info("operationAtmDTO ===>"+operationAtmDTO);
        ResponseApiJson<OperationAtmDto> responseApiJson = operationAtmService.updateSenarioScript(id, operationAtmDTO);
       // logger.info("#################### End updateSenarioScript ####################");
        return responseApiJson;
    }

    @GetMapping("/get/{id}")
    public ResponseApiJson<OperationAtmDto> getSenarioScript(@PathVariable Long id) {
       // logger.info("#################### Start getSenarioScript ####################");
        ResponseApiJson<OperationAtmDto> responseApiJson = operationAtmService.getSenarioScriptById(id);
       // logger.info("#################### End getSenarioScript ####################");
        return responseApiJson;
    }

    @DeleteMapping("/delete/{id}")
    public ResponseApiJson<Void> deleteSenarioScript(@PathVariable Long id) {
       // logger.info("#################### Start deleteSenarioScript ####################");
        ResponseApiJson<Void> responseApiJson = operationAtmService.deleteSenarioScript(id);
       // logger.info("#################### End deleteSenarioScript ####################");
        return responseApiJson;
    }
}
