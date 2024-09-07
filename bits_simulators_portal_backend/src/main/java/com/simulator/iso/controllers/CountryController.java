package com.simulator.iso.controllers;

import com.simulator.entities.CountryParam;
import com.simulator.globalModels.ResponseApiJson;
import com.simulator.iso.services.CountryService;
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
@RequestMapping("/api/su/countryParam")
@RequiredArgsConstructor
public class CountryController {
    private final Logger logger = LogManager.getLogger(CountryController.class);
    @Autowired
    CountryService countryService;



   /* @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/get")
    public ResponseEntity<CountryParam> getCountryParam(@RequestBody String id) {
        try {
            logger.info("test 1");
            CountryParam countryParam = countryService.getCountryParam(id);
            if (countryParam != null) {
                logger.info("test 2");
                return new ResponseEntity<>(countryParam , HttpStatus.OK);
            } else {
                logger.info("test 3");
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            logger.info("test 4");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
*/

/*
 @SecurityRequirement(name = "Bearer Authentication")
    @GetMapping("/mti/filter")
    public List<MtiDef> getFilteredMtiList(
            @RequestParam String bankCode,
            @RequestParam String mtiProtocol) {
        return posMtiService.getFilteredMtiList(bankCode, mtiProtocol);
    }
*/



    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/getAllCountryParams")
    public ResponseApiJson<List<CountryParam>> getAllCountryParam() {
        logger.info("#################### Star getAllCountryParams ####################");
        ResponseApiJson<List<CountryParam>> responseApiJson = countryService.getAllCountryParams();
        return responseApiJson;
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/getOneCountryParam")
    public ResponseApiJson<List<CountryParam>> getOneCountryParam(@RequestBody String id) {
        logger.info("####################Star getOneCountryParam ####################");
        ResponseApiJson<List<CountryParam>> responseApiJson = countryService.getOneCountryParam(id);
        logger.info("#################### End getOneCountryParam ####################");
        return responseApiJson;
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/addCountryParam")
    public ResponseApiJson<?> addCountryParam(@RequestBody CountryParam countryParam) {
        logger.info("####################Star addCountryParam ####################");
        ResponseApiJson<?> responseApiJson = countryService.addCountryParam(countryParam);
        logger.info("#################### End addCountryParam ####################");
        return responseApiJson;

    }

    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/updateCountryParam")
    public ResponseApiJson<?> updateCountryParam(@RequestBody CountryParam countryParam) throws IllegalAccessException {
        logger.info("#################### Star updateCountryParam ####################");
        ResponseApiJson<?> responseApiJson = countryService.updateCountryParam(countryParam);
        logger.info("#################### End updateCountryParam ####################");
        return responseApiJson;


    }

    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/deleteCountryParam")
    public ResponseApiJson<?> deleteCountryParam(@RequestBody String  id) {
        logger.info("#################### Star deleteCountryParam ####################");
        ResponseApiJson<?> responseApiJson = countryService.deleteCountryParam(id);
        logger.info("#################### End deleteCountryParam ####################");
        return responseApiJson;
    }
}



