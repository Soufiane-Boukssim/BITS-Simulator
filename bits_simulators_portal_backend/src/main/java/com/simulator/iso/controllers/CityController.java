package com.simulator.iso.controllers;

import com.simulator.entities.City;
import com.simulator.globalModels.ResponseApiJson;
import com.simulator.iso.services.CityService;
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
@RequestMapping("/api/suPosCasesDefinition")
@RequiredArgsConstructor
public class CityController {

    @Autowired
    private CityService cityService;

    private final Logger logger = LogManager.getLogger(CityController.class);

    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/getAllCities")
    public ResponseApiJson<List<City>> getAllCities() {
        logger.info("#################### Star getAllCountryParams ####################");
        ResponseApiJson<List<City>> responseApiJson = cityService.getAllCities();
        return responseApiJson;
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/addCity")
        public ResponseApiJson<?> addCountryParam(@RequestBody City city) {
        logger.info("####################Star addCity ####################");
        ResponseApiJson<?> responseApiJson = cityService.addCity(city);
        logger.info("#################### End addCity ####################");
        return responseApiJson;

    }

    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/updateCity")
    public ResponseApiJson<?> updateCountryParam(@RequestBody City city) throws IllegalAccessException {
        logger.info("#################### Star updateCity ####################");
        ResponseApiJson<?> responseApiJson = cityService.updateCountryParam(city);
        logger.info("#################### End updateCity ####################");
        return responseApiJson;


    }

    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/deleteCity")
    public ResponseApiJson<?> deleteCity(@RequestBody String id) {
        logger.info("#################### Star deleteCountryParam ####################");
        ResponseApiJson<?> responseApiJson = cityService.deleteCity(id);
        logger.info("#################### End deleteCountryParam ####################");
        return responseApiJson;
    }
}
