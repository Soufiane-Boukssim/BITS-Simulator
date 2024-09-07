package com.simulator.globalService.atm;

import com.simulator.entities.atm.Etape;
import com.simulator.repository.atm.EtapeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EtapeService {

    @Autowired
    private EtapeRepository etapeRepository;

    // Save or update an Etape
    public Etape saveOrUpdateEtape(Etape etape) {
        return etapeRepository.save(etape);
    }

    // Get an Etape by type
    public Optional<Etape> getEtapeByType(String type) {
        return etapeRepository.findById(type);
    }

    // Get all Etapes
    public List<Etape> getAllEtapes() {
        return etapeRepository.findAll();
    }

    // Delete an Etape by type
    public void deleteEtapeByType(String type) {
        etapeRepository.deleteById(type);
    }
}
