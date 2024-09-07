package com.simulator.atm.controllers.controlleratm;

import com.simulator.atm.dto.OperationAtmDto;
import com.simulator.atm.services.webService.ScriptConversionService;
import com.simulator.globalModels.ResponseApiJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/script-conversion")
public class ScriptConversioController {

    @Autowired
    private ScriptConversionService scriptConversionService;
    @PostMapping("/add")
    public ResponseEntity<ResponseApiJson<String>> processSenarioScript(@RequestBody OperationAtmDto jsonInput) {
        ResponseApiJson<String> result = scriptConversionService.processSenarioScript(jsonInput);
        return ResponseEntity.ok(result);
    }

}
