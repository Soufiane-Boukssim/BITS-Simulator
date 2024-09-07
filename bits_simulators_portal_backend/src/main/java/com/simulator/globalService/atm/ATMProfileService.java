package com.simulator.globalService.atm;

import com.simulator.entities.atm.ATMProfile;
import com.simulator.repository.atm.ATMProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ATMProfileService {

    @Autowired
    private ATMProfileRepository atmProfileRepository;

    // Save or update an ATM profile
    public ATMProfile saveOrUpdateATMProfile(ATMProfile atmProfile) {
        return atmProfileRepository.save(atmProfile);
    }

    // Get an ATM profile by profile code
    public Optional<ATMProfile> getATMProfileByCode(String profileCode) {
        return atmProfileRepository.findById(profileCode);
    }

    // Get all ATM profiles
    public List<ATMProfile> getAllATMProfiles() {
        return atmProfileRepository.findAll();
    }

    // Delete an ATM profile by profile code
    public void deleteATMProfileByCode(String profileCode) {
        atmProfileRepository.deleteById(profileCode);
    }
}
