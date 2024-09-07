package com.simulator.pos.controllers;


import com.simulator.entities.pos.PosFieldsDefinition;
import com.simulator.entities.pos.PosFieldsDefinitionId;
import com.simulator.globalModels.ResponseApiJson;
import com.simulator.pos.services.PosFieldsService;
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
@RequestMapping("/api/su/pos/fieldsDefinition")
@RequiredArgsConstructor
public class PosFieldsController {
    private final Logger logger = LogManager.getLogger(PosFieldsController.class);
    @Autowired
    PosFieldsService fieldsService;



   /* @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/get")
    public ResponseEntity<PosFieldsDefinition> getFieldsDefinition(@RequestBody PosFieldsDefinitionId id) {
        try {
            logger.info("test 1");
            PosFieldsDefinition fieldsDefinition = fieldsService.getFieldsDefinition(id);
            if (fieldsDefinition != null) {
                logger.info("test 2");
                return new ResponseEntity<>(fieldsDefinition , HttpStatus.OK);
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
    @GetMapping("/fields/filter")
    public List<PosFieldsDefinition> getFilteredFieldsList(
            @RequestParam String bankCode,
            @RequestParam Character fieldProtocole) {
        return fieldsService.getFilteredFieldsList(bankCode, fieldProtocole);
    }

*/


  /*  @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/getAllFieldsDefinitions/filter")
    public   List<PosFieldsDefinition> getFilteredFieldsList(
            @RequestBody PosFieldsDefinitionId id) {
        return fieldsService.getFilteredFieldsList(id.getBankCode(), id.getFieldProtocole());
    }
*/
    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/getAllFieldsDefinitions")
    public ResponseApiJson<List<PosFieldsDefinition>> getAllFieldsDefinition() {
        logger.info("#################### Star getAllFieldsDefinitions ####################");
        ResponseApiJson<List<PosFieldsDefinition>> responseApiJson = fieldsService.getAllFieldsDefinitions();
        return responseApiJson;
    }
    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/getOneFieldsDefinition")
    public ResponseApiJson<List<PosFieldsDefinition>> getOneFieldsDefinition(@RequestBody PosFieldsDefinitionId id) {
        logger.info("####################Star getOneFieldsDefinition ####################");
        ResponseApiJson<List<PosFieldsDefinition>> responseApiJson = fieldsService.getOneFieldsDefinition(id);
        logger.info("#################### End getOneFieldsDefinition####################");
        return responseApiJson;
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/addFieldsDefinition")
    public ResponseApiJson<?> addMtiDef(@RequestBody PosFieldsDefinition fieldsDefinition) {
        logger.info("####################Star addFieldsDefinition ####################");
        ResponseApiJson<?> responseApiJson = fieldsService.addFieldsDefinition(fieldsDefinition);
        logger.info("#################### End addFieldsDefinition ####################");
        return responseApiJson;

    }

    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/updateFieldsDefinition")
    public ResponseApiJson<?> updateFieldsDefinition(@RequestBody PosFieldsDefinition fieldsDefinition) throws IllegalAccessException {
        logger.info("#################### Star updateFieldsDefinition ####################");
        ResponseApiJson<?> responseApiJson = fieldsService.updateFieldsDefinition(fieldsDefinition);
        logger.info("#################### End updateFieldsDefinition ####################");
        return responseApiJson;


    }

    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/deleteFieldsDefinition")
    public ResponseApiJson<?> deleteFieldsDefinition(@RequestBody PosFieldsDefinitionId id) {
        logger.info("#################### Star deleteFieldsDefinition ####################");
        ResponseApiJson<?> responseApiJson = fieldsService.deleteFieldsDefinition(id);
        logger.info("#################### End deleteFieldsDefinition ####################");
        return responseApiJson;
    }
}



