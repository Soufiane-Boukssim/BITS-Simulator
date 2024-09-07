package com.simulator.iso.controllers;

import com.simulator.entities.MccDef;
import com.simulator.entities.MccDefId;
import com.simulator.globalModels.ResponseApiJson;
import com.simulator.iso.services.MccService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/su/mccDef")
@RequiredArgsConstructor
public class MccController {
    private final Logger logger = LogManager.getLogger(MccController.class);
    @Autowired
    MccService mccService;



    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/get")
    public ResponseEntity<MccDef> getMccDef(@RequestBody MccDefId id) {
        try {
            logger.info("test 1");
            MccDef mccDef = mccService.getMccDef(id);
            if (mccDef != null) {
                logger.info("test 2");
                return new ResponseEntity<>(mccDef , HttpStatus.OK);
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
    @GetMapping("/mcc/filter")
    public List<MccDef> getFilteredMccList(
            @RequestParam String bankCode,
            @RequestParam Character mccProtocol) {
        return mccService.getFilteredMccList(bankCode, mccProtocol);
    }*/

    /*@SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/getAllMccDefs")
    public ResponseApiJson<List<MccDef>> getAllMccDefs() {
        logger.info("#################### Star getAllMccDefs ####################");
        ResponseApiJson<List<MccDef>> responseApiJson = mccService.getAllMccDefs();
        return responseApiJson;
    }*/


   /* @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/addMccDef")
    public ResponseEntity<String> addMccDef(@RequestBody MccDef mccDef) {
        try {
            logger.info("####################Star addIccProfile ####################");
            MccDef i = mccService.addMccDef(mccDef);
            logger.info("####################End addIccProfile ####################");

            if (i == null) {
                return new ResponseEntity<>(HttpStatus.CONFLICT);
            } else {
                return new ResponseEntity<>(HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }*/



    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/getAllMccdefs/filter")
    public   List<MccDef> getFilteredMtiList(
            @RequestBody MccDefId id) {
        return mccService.getFilteredMccList(id.getBankCode(), id.getMccProtocol());
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/getOneMccDef")
    public ResponseApiJson<List<MccDef>> getOneMccDef(@RequestBody MccDefId id) {
        logger.info("####################Star getOneMccDef ####################");
        ResponseApiJson<List<MccDef>> responseApiJson = mccService.getOneMccDef(id);
        logger.info("#################### End getOneMccDef####################");
        return responseApiJson;
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/addMccDef")
    public ResponseApiJson<?> addMccDef(@RequestBody MccDef mccDef) {
        logger.info("####################Star addMccDef ####################");
        ResponseApiJson<?> responseApiJson = mccService.addMccDef(mccDef);
        logger.info("#################### End addMccDef ####################");
        return responseApiJson;

    }
    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/updateMccDef")
    public ResponseApiJson<?> updateMtiDef(@RequestBody MccDef mccDef) throws IllegalAccessException {
        logger.info("#################### Star updateMccDef ####################");
        ResponseApiJson<?> responseApiJson = mccService.updateMccDef(mccDef);
        logger.info("#################### End updateMccDef ####################");
        return responseApiJson;


    }

    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/deleteMccDef")
    public ResponseApiJson<?> deleteMccDef(@RequestBody MccDefId id) {
        logger.info("#################### Star deleteMccDef ####################");
        ResponseApiJson<?> responseApiJson = mccService.deleteMccDef(id);
        logger.info("#################### End deleteMccDef ####################");
        return responseApiJson;
    }
}





