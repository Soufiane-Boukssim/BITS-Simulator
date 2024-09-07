package com.simulator.smartICC.controllers;

import com.simulator.globalModels.ResponseApiJson;
import com.simulator.smartICC.models.IssuerConfig;
import com.simulator.smartICC.services.IssuerConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/issuer-config")
public class IssuerConfigController {
    private final IssuerConfigService issuerConfigService;

    @Autowired
    public IssuerConfigController(IssuerConfigService issuerConfigService) {
        this.issuerConfigService = issuerConfigService;
    }



    @PostMapping("/insert")
    public ResponseApiJson<IssuerConfig> insertIssuerConfig(@RequestBody IssuerConfig issuerConfig) {
        try {
            ResponseApiJson<IssuerConfig> insertedTerminalConfig = issuerConfigService.insertIssuerConfig(issuerConfig);
            return insertedTerminalConfig;
        } catch (Exception e) {
            e.printStackTrace(); // Ajoutez des logs pour voir l'erreur
            return new ResponseApiJson<>("error", "An error occurred while inserting issuerConfigService", null);
        }
    }

    @GetMapping("/all")
    private ResponseApiJson<List<IssuerConfig>> getAllIssuerConfigs(){
        try {
            ResponseApiJson<List<IssuerConfig>> responseApiJson = issuerConfigService.getAllIssuerConfigs();
            return responseApiJson;
        } catch (Exception ex) {
            return new ResponseApiJson<>("error", "An error occurred while fetching  issuerConfigService", null);
        }
    }

    @PostMapping("/getIssuerConfigById")
    public ResponseApiJson<IssuerConfig> getIssuerConfigsIdd(@RequestBody Map<String, String> requestBody) {
        try {
            String code_profil = requestBody.get("code_profil");
            ResponseApiJson<IssuerConfig> insertedProfile = issuerConfigService.getIssuerConfigsId(code_profil);
            return insertedProfile;
        } catch (Exception e) {
            e.printStackTrace(); // Ajoutez des logs pour voir l'erreur
            return new ResponseApiJson<>("error", "An error occurred while inserting issuerConfigService", null);
        }
    }








    @PostMapping("/delete")
    public ResponseApiJson<String> deleteIssuerConfig(@RequestBody Map<String, String> requestBody) {
        try {
            String code_profil = requestBody.get("code_profil");
            ResponseApiJson<String> response = issuerConfigService.deleteIssuerConfig(code_profil);
            return response;
        } catch (Exception e) {
            e.printStackTrace(); // Ajoutez des logs pour voir l'erreur
            return new ResponseApiJson<>("error", "An error occurred while deleting  issuerConfigService", null);
        }
    }


}
