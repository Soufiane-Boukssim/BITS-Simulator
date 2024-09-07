package com.simulator.iso.controllers;

import com.simulator.entities.FieldsDefinition;
import com.simulator.entities.FieldsDefinitionId;
import com.simulator.globalModels.ResponseApiJson;
import com.simulator.iso.services.FieldsService;
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
@RequestMapping("/api/su/fieldsDefinition")
@RequiredArgsConstructor
public class FieldsController {
    private final Logger logger = LogManager.getLogger(FieldsController.class);
    @Autowired
    FieldsService fieldsService;



   /* @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/get")
    public ResponseEntity<FieldsDefinition> getFieldsDefinition(@RequestBody FieldsDefinitionId id) {
        try {
            logger.info("test 1");
            FieldsDefinition fieldsDefinition = posFieldsService.getFieldsDefinition(id);
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
    public List<FieldsDefinition> getFilteredFieldsList(
            @RequestParam String bankCode,
            @RequestParam Character fieldProtocole) {
        return posFieldsService.getFilteredFieldsList(bankCode, fieldProtocole);
    }

*/


  /*  @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/getAllFieldsDefinitions/filter")
    public   List<FieldsDefinition> getFilteredFieldsList(
            @RequestBody FieldsDefinitionId id) {
        return posFieldsService.getFilteredFieldsList(id.getBankCode(), id.getFieldProtocole());
    }
*/
    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/getAllFieldsDefinitions")
    public ResponseApiJson<List<FieldsDefinition>> getAllFieldsDefinition(@RequestBody FieldsDefinitionId id) {
        logger.info("#################### Star getAllFieldsDefinitions ####################");
        ResponseApiJson<List<FieldsDefinition>> responseApiJson = fieldsService.getAllFieldsDefinitions(id);
        return responseApiJson;
    }
    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/getOneFieldsDefinition")
    public ResponseApiJson<List<FieldsDefinition>> getOneFieldsDefinition(@RequestBody FieldsDefinitionId id) {
        logger.info("####################Star getOneFieldsDefinition ####################");
        ResponseApiJson<List<FieldsDefinition>> responseApiJson = fieldsService.getOneFieldsDefinition(id);
        logger.info("#################### End getOneFieldsDefinition####################");
        return responseApiJson;
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/addFieldsDefinition")
    public ResponseApiJson<?> addMtiDef(@RequestBody FieldsDefinition fieldsDefinition) {
        logger.info("####################Star addFieldsDefinition ####################");
        ResponseApiJson<?> responseApiJson = fieldsService.addFieldsDefinition(fieldsDefinition);
        logger.info("#################### End addFieldsDefinition ####################");
        return responseApiJson;

    }

    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/updateFieldsDefinition")
    public ResponseApiJson<?> updateFieldsDefinition(@RequestBody FieldsDefinition fieldsDefinition) throws IllegalAccessException {
        logger.info("#################### Star updateFieldsDefinition ####################");
        ResponseApiJson<?> responseApiJson = fieldsService.updateFieldsDefinition(fieldsDefinition);
        logger.info("#################### End updateFieldsDefinition ####################");
        return responseApiJson;


    }

    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/deleteFieldsDefinition")
    public ResponseApiJson<?> deleteFieldsDefinition(@RequestBody FieldsDefinitionId id) {
        logger.info("#################### Star deleteFieldsDefinition ####################");
        ResponseApiJson<?> responseApiJson = fieldsService.deleteFieldsDefinition(id);
        logger.info("#################### End deleteFieldsDefinition ####################");
        return responseApiJson;
    }
}



