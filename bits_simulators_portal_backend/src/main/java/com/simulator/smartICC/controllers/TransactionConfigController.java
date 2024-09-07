package com.simulator.smartICC.controllers;

import com.simulator.globalModels.ResponseApiJson;
import com.simulator.smartICC.models.TransactionConfig;
import com.simulator.smartICC.services.TransactionConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/transaction-config")
public class TransactionConfigController {
    private final TransactionConfigService transactionConfigService;

    @Autowired
    public TransactionConfigController(TransactionConfigService transactionConfigService) {
        this.transactionConfigService = transactionConfigService;
    }



    @PostMapping("/insert")
    public ResponseApiJson<TransactionConfig> insertTransactionConfig(@RequestBody TransactionConfig transactionConfig) {
        try {
            ResponseApiJson<TransactionConfig> reponse = transactionConfigService.insertTransactionConfig(transactionConfig);
            return reponse;
        } catch (Exception e) {
            e.printStackTrace(); // Ajoutez des logs pour voir l'erreur
            return new ResponseApiJson<>("error", "An error occurred while inserting TransactionConfig", null);
        }
    }

    @GetMapping("/all")
    private ResponseApiJson<List<TransactionConfig>> getAllProfiles(){
        try {
            ResponseApiJson<List<TransactionConfig>> responseApiJson = transactionConfigService.getAllTransactionConfigs();
            return responseApiJson;
        } catch (Exception ex) {
            return new ResponseApiJson<>("error", "An error occurred while fetching tag profiles", null);
        }
    }

    @PostMapping("/getTransactionConfigById")
    public ResponseApiJson<TransactionConfig> getTransactionConfigByIdd(@RequestBody Map<String, String> requestBody) {
        try {
            String code_profil = requestBody.get("code_profil");
            ResponseApiJson<TransactionConfig> insertedProfile = transactionConfigService.getTransactionConfigById(code_profil);
            return insertedProfile;
        } catch (Exception e) {
            e.printStackTrace(); // Ajoutez des logs pour voir l'erreur
            return new ResponseApiJson<>("error", "An error occurred while inserting profile", null);
        }
    }


    @PostMapping("/delete")
    public ResponseApiJson<String> deleteTransactionConfig(@RequestBody Map<String, String> requestBody) {
        try {
            String code_profil = requestBody.get("code_profil");
            ResponseApiJson<String> response = transactionConfigService.deleteTransactionConfig(code_profil);
            return response;
        } catch (Exception e) {
            e.printStackTrace(); // Ajoutez des logs pour voir l'erreur
            return new ResponseApiJson<>("error", "An error occurred while deleting terminal ConfigService", null);
        }
    }

}
