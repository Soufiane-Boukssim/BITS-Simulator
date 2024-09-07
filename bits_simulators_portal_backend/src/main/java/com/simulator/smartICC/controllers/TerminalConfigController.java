package com.simulator.smartICC.controllers;

import com.simulator.globalModels.ResponseApiJson;
import com.simulator.smartICC.models.TerminalConfig;
import com.simulator.smartICC.services.TerminalConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/terminal-config")
public class TerminalConfigController {
    private final TerminalConfigService terminalConfigService;

    @Autowired
    public TerminalConfigController(TerminalConfigService terminalConfigService) {
        this.terminalConfigService = terminalConfigService;
    }


    // TerminalConfigController.java



    @PostMapping("/insert")
    public ResponseApiJson<TerminalConfig> insertTerminalConfig(@RequestBody TerminalConfig terminalConfig) {
        try {
            ResponseApiJson<TerminalConfig> insertedTerminalConfig = terminalConfigService.insertTerminalConfig(terminalConfig);
            return insertedTerminalConfig;
        } catch (Exception e) {
            e.printStackTrace(); // Ajoutez des logs pour voir l'erreur
            return new ResponseApiJson<>("error", "An error occurred while inserting profile", null);
        }
    }

    @GetMapping("/all")
    private ResponseApiJson<List<TerminalConfig>> getAllProfiles(){
        try {
            ResponseApiJson<List<TerminalConfig>> responseApiJson = terminalConfigService.getAllTerminalConfigs();
            return responseApiJson;
        } catch (Exception ex) {
            return new ResponseApiJson<>("error", "An error occurred while fetching tag profiles", null);
        }
    }


    @PostMapping("/getTermminalCongById")
    public ResponseApiJson<TerminalConfig> getTermenalByIdd(@RequestBody Map<String, String> requestBody) {
        try {
            String code_profil = requestBody.get("code_profil");
            ResponseApiJson<TerminalConfig> insertedProfile = terminalConfigService.getTermenalById(code_profil);
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
                ResponseApiJson<String> response = terminalConfigService.deleteTerminalConfig(code_profil);
                return response;
            } catch (Exception e) {
                e.printStackTrace(); // Ajoutez des logs pour voir l'erreur
                return new ResponseApiJson<>("error", "An error occurred while deleting terminal ConfigService", null);
            }
        }





}
