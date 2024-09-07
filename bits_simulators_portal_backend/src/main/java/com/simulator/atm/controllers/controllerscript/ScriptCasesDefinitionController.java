package com.simulator.atm.controllers.controllerscript;

import com.simulator.atm.dto.ScriptCasesDefinitionDto;
import com.simulator.atm.services.script.ScriptCasesDefinitionService;
import com.simulator.globalModels.ResponseApiJson;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/scriptCasesDefinition")
public class ScriptCasesDefinitionController {

    private final Logger logger = LogManager.getLogger(ScriptCasesDefinitionController.class);

    @Autowired
    private ScriptCasesDefinitionService scriptCasesDefinitionService;

    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/add")
    public ResponseApiJson<ScriptCasesDefinitionDto> createScriptCasesDefinition(@RequestBody ScriptCasesDefinitionDto scriptCasesDefinitionDto) {
        logger.info("#################### Star createScriptCasesDefinition ####################");
        ResponseApiJson<ScriptCasesDefinitionDto> responseApiJson = scriptCasesDefinitionService.createScriptCasesDefinition(scriptCasesDefinitionDto);
        logger.info("#################### End createScriptCasesDefinition ####################");
        return responseApiJson;
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @GetMapping("/getById/{id}")
    public ResponseApiJson<ScriptCasesDefinitionDto> getScriptCasesDefinitionById(@PathVariable Long id) {
        logger.info("#################### Star getScriptCasesDefinitionById ####################");
        ResponseApiJson<ScriptCasesDefinitionDto> responseApiJson = scriptCasesDefinitionService.getScriptCasesDefinitionById(id);
        logger.info("#################### End getScriptCasesDefinitionById ####################");
        return responseApiJson;
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @GetMapping("/all/{bankCode}")
    public ResponseApiJson<List<ScriptCasesDefinitionDto>> getAllScriptCasesDefinition(@PathVariable String bankCode) {
        logger.info("#################### Star getAllScriptCasesDefinition ####################");
        ResponseApiJson<List<ScriptCasesDefinitionDto>> responseApiJson = scriptCasesDefinitionService.getAllScriptCasesDefinitions(bankCode);
        logger.info("#################### End getAllScriptCasesDefinition ####################");
        return responseApiJson;
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @PutMapping("/update/{id}")
    public ResponseApiJson<ScriptCasesDefinitionDto> updateScriptCasesDefinition(@PathVariable Long id, @RequestBody ScriptCasesDefinitionDto scriptCasesDefinitionDto) {
        logger.info("#################### Star updateScriptCasesDefinition ####################");
        ResponseApiJson<ScriptCasesDefinitionDto> responseApiJson = scriptCasesDefinitionService.updateScriptCasesDefinition(id, scriptCasesDefinitionDto);
        logger.info("#################### End updateScriptCasesDefinition ####################");
        return responseApiJson;
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteScriptCasesDefinition(@PathVariable Long id) {
        try {
            scriptCasesDefinitionService.deleteScriptCasesDefinition(id);
            return new ResponseEntity<>("000", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("001", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
