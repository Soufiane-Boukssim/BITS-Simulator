package com.simulator.globalService.atm;


import com.simulator.entities.atm.Langue;
import com.simulator.repository.atm.LangueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LangueService {

    @Autowired
    private LangueRepository langueRepository;

    // Save or update a Langue
    public Langue saveOrUpdateLangue(Langue langue) {
        return langueRepository.save(langue);
    }

    // Get a Langue by langue code
    public Optional<Langue> getLangueByCode(String langueCode) {
        return langueRepository.findById(langueCode);
    }

    // Get all Langues
    public List<Langue> getAllLangues() {
        return langueRepository.findAll();
    }

    // Delete a Langue by langue code
    public void deleteLangueByCode(String langueCode) {
        langueRepository.deleteById(langueCode);
    }
}
