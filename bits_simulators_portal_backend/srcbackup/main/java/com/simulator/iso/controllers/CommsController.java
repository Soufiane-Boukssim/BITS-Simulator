package com.simulator.iso.controllers;

import com.simulator.entities.CommsParam;
import com.simulator.entities.CommsParamId;
import com.simulator.globalModels.ResponseApiJson;
import com.simulator.iso.services.CommsService;
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
@RequestMapping("/api/su/commsParam")
@RequiredArgsConstructor
public class CommsController {
    private final Logger logger = LogManager.getLogger(CommsController.class);
    @Autowired
    CommsService commsService;


    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/get")
    public ResponseEntity<CommsParam> getCommsParam(@RequestBody CommsParamId id) {
        try {
            logger.info("test 1");
            CommsParam commsParam = commsService.getCommsParam(id);
            if (commsParam != null) {
                logger.info("test 2");
                return new ResponseEntity<>(commsParam , HttpStatus.OK);
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
    @PostMapping("/getAllCommsParams/filter")
    public   List<CommsParam> getFilteredCommsList(
            @RequestBody CommsParamId id) {
        return commsService.getFilteredCommsList(id.getBankCode(), id.getCommProtocol());
    }

  /*  @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/getAllCommsParams")
    public ResponseApiJson<List<CommsParam>> getAllCommsParam() {
        logger.info("#################### Star getAllCommsParams ####################");
        ResponseApiJson<List<CommsParam>> responseApiJson = commsService.getAllCommsParams();
        return responseApiJson;
    }*/

    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/getOneCommsParam")
    public ResponseApiJson<List<CommsParam>> getOneCommsParam(@RequestBody CommsParamId id) {
        logger.info("####################Star getOneCommsParam ####################");
        ResponseApiJson<List<CommsParam>> responseApiJson = commsService.getOneCommsParam(id);
        logger.info("#################### End getOneCommsParam####################");
        return responseApiJson;
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/addCommsParam")
    public ResponseApiJson<?> addCommsParam(@RequestBody CommsParam commsParam) {
        logger.info("####################Star addCommsParam ####################");
        ResponseApiJson<?> responseApiJson = commsService.addCommsParam(commsParam);
        logger.info("#################### End addCommsParam ####################");
        return responseApiJson;

    }

    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/updateCommsParam")
    public ResponseApiJson<?> updateCommsParam(@RequestBody CommsParam commsParam) throws IllegalAccessException {
        logger.info("#################### Star updateCommsParam ####################");
        ResponseApiJson<?> responseApiJson = commsService.updateCommsParam(commsParam);
        logger.info("#################### End updateCommsParam ####################");
        return responseApiJson;


    }

    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/deleteCommsParam")
    public ResponseApiJson<?> deleteCommsParam(@RequestBody CommsParamId id) {
        logger.info("#################### Star deleteCommsParam ####################");
        ResponseApiJson<?> responseApiJson = commsService.deleteCommsParam(id);
        logger.info("#################### End deleteCommsParam ####################");
        return responseApiJson;
    }
}



