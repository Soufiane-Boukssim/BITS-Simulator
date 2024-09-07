package com.simulator.pos.controllers;


import com.simulator.entities.pos.PosIccProfile;
import com.simulator.entities.pos.PosIccProfileId;
import com.simulator.globalModels.ResponseApiJson;
import com.simulator.pos.services.PosIccService;
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
@RequestMapping("/api/su/pos/iccProfile")
@RequiredArgsConstructor
public class PosIccController {
    private final Logger logger = LogManager.getLogger(PosIccController.class);
    @Autowired
    PosIccService iccService;




   /* @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/get")
    public ResponseEntity<PosIccProfile> getIccProfile(@RequestBody PosIccProfileId id) {
        try {
            logger.info("test 1");
            PosIccProfile iccProfile = iccService.getIccProfile(id);
            if (iccProfile != null) {
                logger.info("test 2");
                return new ResponseEntity<>(iccProfile , HttpStatus.OK);
            } else {
                logger.info("test 3");
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            logger.info("test 4");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }*/



  /*  @SecurityRequirement(name = "Bearer Authentication")
    @GetMapping("/icc/filter")
    public List<PosIccProfile> getFilteredIccList(
            @RequestParam String bankCode,
            @RequestParam String iccProtocol) {
        return iccService.getFilteredIccList(bankCode, iccProtocol);
    }*/



  /*  @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/getAllIccdefs/filter")
    public   List<PosIccProfile> getFilteredIccList(
            @RequestBody PosIccProfileId id) {
        return iccService.getFilteredIccList(id.getBankCode(), id.getIccProtocol());
    }*/

    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/getAllIccProfiles")
    public ResponseApiJson<List<PosIccProfile>> getAllIccProfile(@RequestBody PosIccProfileId id) {
        logger.info("#################### Star getAllIccProfiles ####################");
        ResponseApiJson<List<PosIccProfile>> responseApiJson = iccService.getAllIccProfiles(id);
        return responseApiJson;
    }


    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/getOneIccProfile")
    public ResponseApiJson<List<PosIccProfile>> getOneIccProfile(@RequestBody PosIccProfileId id) {
        logger.info("####################Star getOneIccProfile ####################");
        ResponseApiJson<List<PosIccProfile>> responseApiJson = iccService.getOneIccProfile(id);
        logger.info("#################### End getOneIccProfile ####################");
        return responseApiJson;
    }

   /* @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/addIccProfile")
    public ResponseEntity<String> addIccProfile(@RequestBody PosIccProfile iccProfile) {
       try {
           logger.info("####################Star addIccProfile ####################");
           PosIccProfile i = iccService.addIccProfile(iccProfile);
           logger.info("####################End addIccProfile ####################");

           if (i == null) {
               return new ResponseEntity<>(HttpStatus.CONFLICT);
           } else {
               return new ResponseEntity<>(HttpStatus.OK);
           }
       } catch (Exception e) {
           return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
       }
    }
    */



    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/addIccProfile")
    public ResponseApiJson<?> addIccProfile(@RequestBody PosIccProfile iccProfile) {
        logger.info("####################Star addIccProfile ####################");
        ResponseApiJson<?> responseApiJson = iccService.addIccProfile(iccProfile);
        logger.info("#################### End addIccProfile ####################");
        return responseApiJson;

    }

    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/updateIccProfile")
    public ResponseApiJson<?> updateIccProfile(@RequestBody PosIccProfile iccProfile) throws IllegalAccessException {
        logger.info("#################### Star updateIccProfile ####################");
        ResponseApiJson<?> responseApiJson = iccService.updateIccProfile(iccProfile);
        logger.info("#################### End updateIccProfile ####################");
        return responseApiJson;


    }

    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/deleteIccProfile")
    public ResponseApiJson<?> deleteIccProfile(@RequestBody PosIccProfileId id) {
        logger.info("#################### Star deleteIccProfile ####################");
        ResponseApiJson<?> responseApiJson = iccService.deleteIccProfile(id);
        logger.info("#################### End deleteIccProfile ####################");
        return responseApiJson;
    }



   /* @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/deleteIccProfile")
    public ResponseEntity<String> deleteIccProfile(@RequestBody PosIccProfileId id) {
        try {
        logger.info("#################### Star deleteIccProfile ####################");
        PosIccProfile i = iccService.deleteIccProfile(id);
        logger.info("#################### End deleteIccProfile ####################");
            if (i == null) {
                return new ResponseEntity<>(HttpStatus.CONFLICT);
            } else {
                return new ResponseEntity<>(HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }*/
}




