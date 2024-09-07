package com.simulator.globalService.atm;


import com.simulator.entities.atm.Pindx;
import com.simulator.repository.atm.PindxRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PindxService {

    @Autowired
    private PindxRepository pindxRepository;

    // Save or update a Pindx
    public Pindx saveOrUpdatePindx(Pindx pindx) {
        return pindxRepository.save(pindx);
    }

    // Get a Pindx by hex value
    public Optional<Pindx> getPindxByHex(String hex) {
        return pindxRepository.findById(hex);
    }

    // Get all Pindx entries
    public List<Pindx> getAllPindx() {
        return pindxRepository.findAll();
    }

    // Delete a Pindx by hex value
    public void deletePindxByHex(String hex) {
        pindxRepository.deleteById(hex);
    }
}
