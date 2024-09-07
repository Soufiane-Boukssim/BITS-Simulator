package com.simulator.smartICC.controllers;

import com.simulator.atm.controllers.controlleratm.LogNDCController;
import com.simulator.globalModels.ResponseApiJson;
import com.simulator.smartICC.models.TagProfil;
import com.simulator.smartICC.services.TagProfilService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tags-profil")
public class TagProfilController {

    private final TagProfilService tagProfilService;

    @Autowired
    public TagProfilController(TagProfilService tagProfilService) {
        this.tagProfilService = tagProfilService;
    }

    private final Logger logger = LogManager.getLogger(LogNDCController.class);



    @GetMapping("/all")
    private ResponseApiJson<List<TagProfil>> getNDCConfig(){
        try {
        ResponseApiJson<List<TagProfil>> responseApiJson=tagProfilService.getAllTagProfils();
        return responseApiJson;
        } catch (Exception ex) {
           return new ResponseApiJson<>("error", "An error occurred while fetching tag profiles", null);
      }
    }


    @PostMapping("/add")
    public ResponseEntity<TagProfil> addTagProfil(@RequestBody TagProfil tagProfil) {
        TagProfil savedTagProfil = tagProfilService.saveTagProfil(tagProfil);
        return new ResponseEntity<>(savedTagProfil, HttpStatus.CREATED);
    }

    // Dans le contrôleur


    @GetMapping("/all-tags")
    public ResponseEntity<List<TagProfil>> getAllTagProfil() {
        List<TagProfil> allTagProfils = tagProfilService.retrieveAllTagProfils();
        return new ResponseEntity<>(allTagProfils, HttpStatus.OK);
    }



    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteTagProfil(@PathVariable Long id) {
        tagProfilService.deleteTagProfil(id);
        return ResponseEntity.ok().build();
    }

    // Ajoutez d'autres méthodes pour la mise à jour et la suppression si nécessaire
}
