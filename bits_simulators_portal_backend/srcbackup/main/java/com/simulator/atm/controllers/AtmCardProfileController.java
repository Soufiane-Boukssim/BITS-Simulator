package com.simulator.atm.controllers;

import com.simulator.atm.services.AtmCardProfileService;
import com.simulator.entities.CardProfile;
import com.simulator.entities.CardProfileId;
import com.simulator.globalModels.ResponseApiJson;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/su/atmCardProfile")
public class AtmCardProfileController {


    private final AtmCardProfileService atmCardProfileService;

    private final Logger logger = LogManager.getLogger(AtmCardProfileController.class);


    @Autowired
    public AtmCardProfileController(AtmCardProfileService atmCardProfileService) {
        this.atmCardProfileService = atmCardProfileService;
    }


    /*@SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/getAllAtmCardProfiles")
    public List<CardProfile> getAllCardProfiles() {
        return atmCardProfileService.getAllCardProfiles();
    }*/


    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/getAllCardProfiles")
    public ResponseApiJson<List<CardProfile>> getAllCardProfile() {
        logger.info("#################### Star getAllCardProfiles ####################");
        ResponseApiJson<List<CardProfile>> responseApiJson = atmCardProfileService.getAllCardProfiles();
        return responseApiJson;
    }


    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/{cardNumber}")
    public ResponseEntity<CardProfile> getCardProfileById(@PathVariable String cardNumber) {
        CardProfileId cardProfileId;
        cardProfileId = new CardProfileId(cardNumber);
        Optional<CardProfile> cardProfile = atmCardProfileService.getCardProfileById(cardProfileId);

        return cardProfile.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }


   /* @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/addAtmCardProfile")
    public ResponseEntity<Void> addCardProfile(@RequestBody CardProfile cardProfile) {
        atmCardProfileService.addCardProfile(cardProfile);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }*/


   /* @PostMapping ("/getOneAtmCardProfiles")
    public ResponseEntity<String> getOneCardProfile(@PathVariable String cardNumber) {
        CardProfileId cardProfileId = new CardProfileId(cardNumber);
        atmCardProfileService.deleteCardProfile(cardProfileId);
        return ResponseEntity.noContent().build();
    }*/


    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/getOneAtmCardProfile")
    public ResponseApiJson<List<CardProfile>> getOneCardProfile(@RequestBody CardProfileId id) {
        logger.info("####################Star getOneAtmCardProfile ####################");
        ResponseApiJson<List<CardProfile>> responseApiJson = atmCardProfileService.getOneCardProfile(id);
        logger.info("#################### End getOneAtmCardProfile####################");
        return responseApiJson;
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/addAtmCardProfile")
    public ResponseApiJson<?> addCardProfile(@RequestBody CardProfile cardProfile) {
        logger.info("####################Star addAtmCardProfile ####################");
        ResponseApiJson<?> responseApiJson = atmCardProfileService.addCardProfile(cardProfile);
        logger.info("#################### End addAtmCardProfile ####################");
        return responseApiJson;

    }



    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/updateAtmCardProfile")
    public ResponseApiJson<?> updateCardProfile(@RequestBody CardProfile cardProfile) throws IllegalAccessException {
        logger.info("#################### Star updateAtmCardProfile ####################");
        ResponseApiJson<?> responseApiJson = atmCardProfileService.updateCardProfile(cardProfile);
        logger.info("#################### End updateAtmCardProfile ####################");
        return responseApiJson;

    }



  /*  @PostMapping("/updateAtmCardProfile")
    public ResponseEntity<String> updateCardProfile(@PathVariable String cardNumber, @PathVariable @RequestBody CardProfile updatedCardProfile) {
        CardProfileId cardProfileId = new CardProfileId(cardNumber);
        atmCardProfileService.updateCardProfile(cardProfileId, updatedCardProfile);
        return ResponseEntity.ok().build();
    }*/



   /* @PostMapping ("/deleteAtmCardProfile")
    public ResponseEntity<String> deleteCardProfile(@PathVariable String cardNumber) {
        CardProfileId cardProfileId = new CardProfileId(cardNumber);
        atmCardProfileService.deleteCardProfile(cardProfileId);
        return ResponseEntity.noContent().build();
    }*/


    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/deleteAtmCardProfile")
    public ResponseApiJson<?> deleteAtmCardProfile(@RequestBody CardProfileId id) {
        logger.info("#################### Star deleteAtmCardProfile ####################");
        ResponseApiJson<?> responseApiJson = atmCardProfileService.deleteCardProfile(id);
        logger.info("#################### End deleteAtmCardProfile ####################");
        return responseApiJson;
    }
}


