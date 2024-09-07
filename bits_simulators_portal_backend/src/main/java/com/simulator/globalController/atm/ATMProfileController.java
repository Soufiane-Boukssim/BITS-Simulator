package com.simulator.globalController.atm;

import com.simulator.entities.atm.ATMProfile;
import com.simulator.globalService.atm.ATMProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/atm-profiles")
public class ATMProfileController {

    @Autowired
    private ATMProfileService atmProfileService;

    // Create or update an ATM profile
    @PostMapping
    public ResponseEntity<ATMProfile> createOrUpdateATMProfile(@RequestBody ATMProfile atmProfile) {
        ATMProfile savedProfile = atmProfileService.saveOrUpdateATMProfile(atmProfile);
        return ResponseEntity.ok(savedProfile);
    }

    // Get an ATM profile by profile code
    @GetMapping("/{profileCode}")
    public ResponseEntity<ATMProfile> getATMProfileByCode(@PathVariable String profileCode) {
        Optional<ATMProfile> atmProfile = atmProfileService.getATMProfileByCode(profileCode);
        return atmProfile.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Get all ATM profiles
    @GetMapping
    public ResponseEntity<List<ATMProfile>> getAllATMProfiles() {
        List<ATMProfile> atmProfiles = atmProfileService.getAllATMProfiles();
        return ResponseEntity.ok(atmProfiles);
    }

    // Delete an ATM profile by profile code
    @DeleteMapping("/{profileCode}")
    public String deleteATMProfileByCode(@PathVariable String profileCode) {
        atmProfileService.deleteATMProfileByCode(profileCode);
        return "000";
    }
}
