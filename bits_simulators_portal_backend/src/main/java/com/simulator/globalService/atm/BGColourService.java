package com.simulator.globalService.atm;


import com.simulator.entities.atm.BGColour;
import com.simulator.repository.atm.BGColourRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BGColourService {

    @Autowired
    private BGColourRepository bgColourRepository;

    // Save or update a BG colour
    public BGColour saveOrUpdateBGColour(BGColour bgColour) {
        return bgColourRepository.save(bgColour);
    }

    // Get a BG colour by code
    public Optional<BGColour> getBGColourByCode(String code) {
        return bgColourRepository.findById(code);
    }

    // Get all BG colours
    public List<BGColour> getAllBGColours() {
        return bgColourRepository.findAll();
    }

    // Delete a BG colour by code
    public void deleteBGColourByCode(String code) {
        bgColourRepository.deleteById(code);
    }
}
