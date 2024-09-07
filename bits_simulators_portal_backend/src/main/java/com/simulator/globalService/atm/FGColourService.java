package com.simulator.globalService.atm;

import com.simulator.entities.atm.FGColour;
import com.simulator.repository.atm.FGColourRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FGColourService {

    @Autowired
    private FGColourRepository fgColourRepository;

    // Save or update an FG colour
    public FGColour saveOrUpdateFGColour(FGColour fgColour) {
        return fgColourRepository.save(fgColour);
    }

    // Get an FG colour by code
    public Optional<FGColour> getFGColourByCode(String code) {
        return fgColourRepository.findById(code);
    }

    // Get all FG colours
    public List<FGColour> getAllFGColours() {
        return fgColourRepository.findAll();
    }

    // Delete an FG colour by code
    public void deleteFGColourByCode(String code) {
        fgColourRepository.deleteById(code);
    }
}
