package com.simulator.iso.controllers;

import com.simulator.entities.ResponseDef;
import com.simulator.entities.ResponseDefId;
import com.simulator.globalModels.ResponseApiJson;
import com.simulator.iso.services.ResponseService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/su/responseDef")
@RequiredArgsConstructor
class ResponseController {
    private final Logger logger = LogManager.getLogger(ResponseController.class);
    @Autowired
    ResponseService responseService;


    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/get")
    public ResponseEntity<ResponseDef> getResponseDef(@RequestBody ResponseDefId id) {
        try {
            logger.info("test 1");
            ResponseDef responseDef = responseService.getResponseDef(id);
            if (responseDef != null) {
                logger.info("test 2");
                return new ResponseEntity<>(responseDef , HttpStatus.OK);
            } else {
                logger.info("test 3");
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            logger.info("test 4");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


   /* @SecurityRequirement(name = "Bearer Authentication")
    @GetMapping("/mti/filter")
    public List<MtiDef> getFilteredMtiList(
            @RequestParam String bankCode,
            @RequestParam String mtiProtocol) {
        return mtiService.getFilteredMtiList(bankCode, mtiProtocol);
    }*/



    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/getAllResponseDefs/filter")
    public   List<ResponseDef> getFilteredResponseList(
            @RequestBody ResponseDefId id) {
        return responseService.getFilteredResponseList(id.getBankCode(), id.getRespProtocol());
    }

   /* @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/getAllResponseDefs")
    public ResponseApiJson<List<PosResponseDef>> getAllResponseDef() {
        logger.info("#################### Star getAllResponseDefs ####################");
        ResponseApiJson<List<PosResponseDef>> responseApiJson = responseService.getAllResponseDefs();
        return responseApiJson;
    }*/

    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/getOneResponseDef")
    public ResponseApiJson<List<ResponseDef>> getOneResponseDef(@RequestBody ResponseDefId id) {
        logger.info("####################Star getOneResponseDef ####################");
        ResponseApiJson<List<ResponseDef>> responseApiJson = responseService.getOneResponseDef(id);
        logger.info("#################### End getOneResponseDef####################");
        return responseApiJson;
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/addResponseDef")
    public ResponseApiJson<?> addResponseDef(@RequestBody ResponseDef responseDef) {
        logger.info("####################Star addResponseDef ####################");
        ResponseApiJson<?> responseApiJson = responseService.addResponseDef(responseDef);
        logger.info("#################### End addResponseDef ####################");
        return responseApiJson;

    }

    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/updateResponseDef")
    public ResponseApiJson<?> updateResponseDef(@RequestBody ResponseDef responseDef) throws IllegalAccessException {
        logger.info("#################### Star updateResponseDef ####################");
        ResponseApiJson<?> responseApiJson = responseService.updateResponseDef(responseDef);
        logger.info("#################### End updateResponseDef ####################");
        return responseApiJson;


    }

    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/deleteResponseDef")
    public ResponseApiJson<?> deleteResponseDef(@RequestBody ResponseDefId id) {
        logger.info("#################### Star deleteResponseDef ####################");
        ResponseApiJson<?> responseApiJson = responseService.deleteResponseDef(id);
        logger.info("#################### End deleteResponseDef ####################");
        return responseApiJson;
    }
}



