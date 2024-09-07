package com.simulator.smartICC.controllers;

import com.simulator.globalModels.ResponseApiJson;
import com.simulator.smartICC.models.KeyParam;
import com.simulator.smartICC.services.KeyParamService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;


@RestController
@RequestMapping("/api/key-paramCpa")
@SecurityRequirement(name = "Bearer Authentication")
public class KeyParamController {


    @Autowired
    private KeyParamService keyParamService;


    @PostMapping("/getKeyPAramCpaProfilById")
    public ResponseApiJson<KeyParam> getKeyPAramCpaProfilById(@RequestBody Map<String, String> requestBody) {
        try {
            String code_profil = requestBody.get("code_profil");
            ResponseApiJson<KeyParam> insertedProfile = keyParamService.keyParamCPAProfilById(code_profil);
            return insertedProfile;
        } catch (Exception e) {
            e.printStackTrace(); // Ajoutez des logs pour voir l'erreur
            return new ResponseApiJson<>("error", "An error occurred while inserting profile", null);
        }
    }
    @PostMapping("/insert")
    public ResponseApiJson<KeyParam> insertProfile(@RequestBody KeyParam keyParam) {
        try {
            ResponseApiJson<KeyParam> insertedProfile = keyParamService.insertKeyParamCPAProfile(keyParam);
            return insertedProfile;
        } catch (Exception e) {
            e.printStackTrace(); // Ajoutez des logs pour voir l'erreur
            return new ResponseApiJson<>("error", "An error occurred while inserting key param profile", null);
        }
    }


    @PostMapping("/delete")
    public ResponseApiJson<String> deleteKeyParamCpa(@RequestBody Map<String, String> requestBody) {
        try {
            String code_profil = requestBody.get("code_profil");
            ResponseApiJson<String> response = keyParamService.deleteKeyParamCpa(code_profil);
            return response;
        } catch (Exception e) {
            e.printStackTrace(); // Ajoutez des logs pour voir l'erreur
            return new ResponseApiJson<>("error", "An error occurred while deleting cpa key param", null);
        }
    }


}
