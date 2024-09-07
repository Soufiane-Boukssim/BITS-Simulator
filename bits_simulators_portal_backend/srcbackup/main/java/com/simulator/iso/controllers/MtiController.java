package com.simulator.iso.controllers;

import com.simulator.entities.MtiDef;
import com.simulator.entities.MtiDefId;
import com.simulator.globalModels.ResponseApiJson;
import com.simulator.iso.services.MtiService;
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
@RequestMapping("/api/su/mtiDef")
@RequiredArgsConstructor
class MtiController {
    private final Logger logger = LogManager.getLogger(MtiController.class);
    @Autowired
    MtiService mtiService;


    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/get")
    public ResponseEntity<MtiDef> getAccountDef(@RequestBody MtiDefId id) {
        try {
            logger.info("test 1");
            MtiDef mtiDef = mtiService.getMtiDef(id);
            if (mtiDef != null) {
                logger.info("test 2");
                return new ResponseEntity<>(mtiDef , HttpStatus.OK);
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
    @PostMapping("/getAllMtidefs/filter")
    public   List<MtiDef> getFilteredMtiList(
            @RequestBody MtiDefId id) {
        return mtiService.getFilteredMtiList(id.getBankCode(), id.getMtiProtocol());
    }

   /* @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/getAllMtidefs")
    public ResponseApiJson<List<MtiDef>> getAllMtiDef() {
        logger.info("#################### Star getAllMtiDefs ####################");
        ResponseApiJson<List<MtiDef>> responseApiJson = mtiService.getAllMtiDefs();
        return responseApiJson;
    }*/

    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/getOneMtiDef")
    public ResponseApiJson<List<MtiDef>> getOneMtiDef(@RequestBody MtiDefId id) {
        logger.info("####################Star getOneMtiDef ####################");
        ResponseApiJson<List<MtiDef>> responseApiJson = mtiService.getOneMtiDef(id);
        logger.info("#################### End getOneMtiDef####################");
        return responseApiJson;
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/addMtiDef")
    public ResponseApiJson<?> addMtiDef(@RequestBody MtiDef mtiDef) {
        logger.info("####################Star addMtiDef ####################");
        ResponseApiJson<?> responseApiJson = mtiService.addMtiDef(mtiDef);
        logger.info("#################### End addMtiDef ####################");
        return responseApiJson;

    }

    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/updateMtiDef")
    public ResponseApiJson<?> updateMtiDef(@RequestBody MtiDef mtiDef) throws IllegalAccessException {
        logger.info("#################### Star updateMtiDef ####################");
        ResponseApiJson<?> responseApiJson = mtiService.updateMtiDef(mtiDef);
        logger.info("#################### End updateMtiDef ####################");
        return responseApiJson;


    }

    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/deleteMtiDef")
    public ResponseApiJson<?> deleteMtiDef(@RequestBody MtiDefId id) {
        logger.info("#################### Star deleteMtiDef ####################");
        ResponseApiJson<?> responseApiJson = mtiService.deleteMtiDef(id);
        logger.info("#################### End deleteMtiDef ####################");
        return responseApiJson;
    }
}



