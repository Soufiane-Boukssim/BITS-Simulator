package com.simulator.smartICC.controllers;

import com.simulator.globalModels.ResponseApiJson;
import com.simulator.smartICC.models.EMVProfile;
import com.simulator.smartICC.services.EMVProfileService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api/profiles")
@SecurityRequirement(name = "Bearer Authentication")
public class EMVProfileController {
    @Autowired
    private EMVProfileService profileService;

    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/insert")
    public ResponseApiJson<EMVProfile> insertProfile(@RequestBody EMVProfile profile) {
        try {
            ResponseApiJson<EMVProfile> insertedProfile = profileService.insertProfile(profile);
            return insertedProfile;
        } catch (Exception e) {
            e.printStackTrace(); // Ajoutez des logs pour voir l'erreur
            return new ResponseApiJson<>("error", "An error occurred while inserting profile", null);
        }
     }



    @GetMapping("/getAllProfiles")
    private ResponseApiJson<List<EMVProfile>> getAllProfiles(){
        try {
            ResponseApiJson<List<EMVProfile>> responseApiJson = profileService.getAllProfiles();
            return responseApiJson;
        } catch (Exception ex) {
            return new ResponseApiJson<>("error", "An error occurred while fetching tag profiles", null);
        }
    }


    @PostMapping("/search")
    public ResponseApiJson<EMVProfile> searchProfile(@RequestBody Map<String, String> requestBody) {
        try {
            String code_profil = requestBody.get("code_profil");
            ResponseApiJson<EMVProfile> insertedProfile = profileService.searchProfil(code_profil);
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
            ResponseApiJson<String> response = profileService.deleteProfile(code_profil);
            return response;
        } catch (Exception e) {
            e.printStackTrace(); // Ajoutez des logs pour voir l'erreur
            return new ResponseApiJson<>("error", "An error occurred while deleting profile", null);
        }
    }




    @PostMapping("/changeActiveProfile")
    public ResponseApiJson<String> changeActiveProfile(@RequestBody Map<String, String> requestBody) {
        try {
            String code_profil = requestBody.get("code_profil");
            ResponseApiJson<String> response = profileService.changeActiveProfile(code_profil);
            return response;
        } catch (Exception e) {
            e.printStackTrace(); // Ajoutez des logs pour voir l'erreur
            return new ResponseApiJson<>("error", "An error occurred while inserting profile", null);
        }
    }





}
