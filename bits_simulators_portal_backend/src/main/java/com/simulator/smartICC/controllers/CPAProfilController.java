package com.simulator.smartICC.controllers;

import com.simulator.globalModels.ResponseApiJson;
import com.simulator.smartICC.models.CPAProfil;
import com.simulator.smartICC.services.CPAProfilService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api/cpa-profil")
@SecurityRequirement(name = "Bearer Authentication")
public class CPAProfilController {

    @Autowired
    private CPAProfilService cPAProfilService;


    @GetMapping("/getAllCPAProfiles")
    private ResponseApiJson<List<CPAProfil>> getAllCPAProfiles(){
        try {
            ResponseApiJson<List<CPAProfil>> responseApiJson = cPAProfilService.getAllCPAProfiles();
            return responseApiJson;
        } catch (Exception ex) {
            return new ResponseApiJson<>("error", "An error occurred while fetching tag profiles", null);
        }
    }

    @PostMapping("/getCpaProfilById")
    public ResponseApiJson<CPAProfil> getCpaProfilById(@RequestBody Map<String, String> requestBody) {
        try {
            String code_profil = requestBody.get("code_profil");
            ResponseApiJson<CPAProfil> insertedProfile = cPAProfilService.getCPAProfilById(code_profil);
            return insertedProfile;
        } catch (Exception e) {
            e.printStackTrace(); // Ajoutez des logs pour voir l'erreur
            return new ResponseApiJson<>("error", "An error occurred while inserting profile", null);
        }
    }
    @PostMapping("/insert")
    public ResponseApiJson<CPAProfil> insertProfile(@RequestBody CPAProfil cpaprofile) {
        try {
            ResponseApiJson<CPAProfil> insertedProfile = cPAProfilService.insertCPAProfile(cpaprofile);
            return insertedProfile;
        } catch (Exception e) {
            e.printStackTrace(); // Ajoutez des logs pour voir l'erreur
            return new ResponseApiJson<>("error", "An error occurred while inserting profile", null);
        }
    }


    @PostMapping("/delete")
    public ResponseApiJson<String> deleteProfile(@RequestBody Map<String, String> requestBody) {
        try {
            String code_profil = requestBody.get("code_profil");
            ResponseApiJson<String> response = cPAProfilService.deleteProfile(code_profil);
            return response;
        } catch (Exception e) {
            e.printStackTrace(); // Ajoutez des logs pour voir l'erreur
            return new ResponseApiJson<>("error", "An error occurred while deleting profile", null);
        }
    }



}
