package com.simulator.iso.controllers;

import com.simulator.entities.IccProfile;
import com.simulator.entities.IccProfileId;
import com.simulator.globalModels.ResponseApiJson;
import com.simulator.iso.services.IccService;
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
@RequestMapping("/api/su/iccProfile")
@RequiredArgsConstructor
public class IccController {
    private final Logger logger = LogManager.getLogger(IccController.class);
    @Autowired
    IccService iccService;




    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/get")
    public ResponseEntity<IccProfile> getIccProfile(@RequestBody IccProfileId id) {
        try {
            logger.info("test 1");
            IccProfile iccProfile = iccService.getIccProfile(id);
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
    }



  /*  @SecurityRequirement(name = "Bearer Authentication")
    @GetMapping("/icc/filter")
    public List<IccProfile> getFilteredIccList(
            @RequestParam String bankCode,
            @RequestParam String iccProtocol) {
        return iccService.getFilteredIccList(bankCode, iccProtocol);
    }*/



    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/getAllIccdefs/filter")
    public   List<IccProfile> getFilteredIccList(
            @RequestBody IccProfileId id) {
        return iccService.getFilteredIccList(id.getBankCode(), id.getIccProtocol());
    }

  /*  @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/getAllIccProfiles")
    public ResponseApiJson<List<IccProfile>> getAllIccProfile() {
        logger.info("#################### Star getAllIccProfiles ####################");
        ResponseApiJson<List<IccProfile>> responseApiJson = iccService.getAllIccProfiles();
        return responseApiJson;
    }
*/
    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/getOneIccProfile")
    public ResponseApiJson<List<IccProfile>> getOneIccProfile(@RequestBody IccProfileId id) {
        logger.info("####################Star getOneIccProfile ####################");
        ResponseApiJson<List<IccProfile>> responseApiJson = iccService.getOneIccProfile(id);
        logger.info("#################### End getOneIccProfile ####################");
        return responseApiJson;
    }

   /* @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/addIccProfile")
    public ResponseEntity<String> addIccProfile(@RequestBody IccProfile iccProfile) {
       try {
           logger.info("####################Star addIccProfile ####################");
           IccProfile i = iccService.addIccProfile(iccProfile);
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
    public ResponseApiJson<?> addIccProfile(@RequestBody IccProfile iccProfile) {
        logger.info("####################Star addIccProfile ####################");
        ResponseApiJson<?> responseApiJson = iccService.addIccProfile(iccProfile);
        logger.info("#################### End addIccProfile ####################");
        return responseApiJson;

    }

    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/updateIccProfile")
    public ResponseApiJson<?> updateIccProfile(@RequestBody IccProfile iccProfile) throws IllegalAccessException {
        logger.info("#################### Star updateIccProfile ####################");
        ResponseApiJson<?> responseApiJson = iccService.updateIccProfile(iccProfile);
        logger.info("#################### End updateIccProfile ####################");
        return responseApiJson;


    }

    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/deleteIccProfile")
    public ResponseApiJson<?> deleteIccProfile(@RequestBody IccProfileId id) {
        logger.info("#################### Star deleteIccProfile ####################");
        ResponseApiJson<?> responseApiJson = iccService.deleteIccProfile(id);
        logger.info("#################### End deleteIccProfile ####################");
        return responseApiJson;
    }



   /* @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/deleteIccProfile")
    public ResponseEntity<String> deleteIccProfile(@RequestBody IccProfileId id) {
        try {
        logger.info("#################### Star deleteIccProfile ####################");
        IccProfile i = iccService.deleteIccProfile(id);
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




