package com.simulator.atm.controllers.controllerscript;

import com.simulator.atm.dto.FieldCasesDto;
import com.simulator.atm.services.script.FieldCasesService;
import com.simulator.globalModels.ResponseApiJson;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/field-cases")
public class FieldCasesController {

    private final Logger logger = LogManager.getLogger(FieldCasesController.class);

    @Autowired
    private FieldCasesService fieldCasesService;

    @SecurityRequirement(name = "Bearer Authentication")
    @PutMapping("/update/{id}")
    public ResponseApiJson<FieldCasesDto> updateFieldCases(@PathVariable Long id, @RequestBody FieldCasesDto fieldCasesDto) {
        logger.info("#################### Star updateFieldCases ####################");
        ResponseApiJson<FieldCasesDto> responseApiJson = fieldCasesService.updateFieldCases(id, fieldCasesDto);
        logger.info("#################### End updateFieldCases ####################");
        return responseApiJson;
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @DeleteMapping("/delete/{id}")
    public ResponseApiJson<?> deleteFieldCases(@PathVariable Long id) {
        logger.info("#################### Star deleteFieldCases ####################");
        ResponseApiJson<?> responseApiJson = fieldCasesService.deleteFieldCases(id);
        logger.info("#################### End deleteFieldCases ####################");
        return responseApiJson;
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @GetMapping("/all/{bankCode}")
    public ResponseApiJson<List<FieldCasesDto>> getAllFieldCases(@PathVariable String bankCode) {
        logger.info("#################### Star getAllFieldCases ####################");
        ResponseApiJson<List<FieldCasesDto>> responseApiJson = fieldCasesService.getAllFieldCases(bankCode);
        logger.info("#################### End getAllFieldCases ####################");
        return responseApiJson;
    }
}
