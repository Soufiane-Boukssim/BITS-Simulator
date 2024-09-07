package com.simulator.globalService.atm;

import com.simulator.entities.atm.Fit;
import com.simulator.repository.atm.FitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FitService {

    @Autowired
    private FitRepository fitRepository;

    // Save or update a Fit
    public Fit saveOrUpdateFit(Fit fit) {
        return fitRepository.save(fit);
    }

    // Get a Fit by hex value
    public Optional<Fit> getFitByHex(String hex) {
        return fitRepository.findById(hex);
    }

    // Get all Fits
    public List<Fit> getAllFits() {
        return fitRepository.findAll();
    }

    // Delete a Fit by hex value
    public void deleteFitByHex(String hex) {
        fitRepository.deleteById(hex);
    }
}
