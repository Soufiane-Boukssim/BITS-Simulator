package com.simulator.atm.services.script;

import com.simulator.atm.model.ATMfield;
import com.simulator.atm.repositories.reposcript.ATMfieldRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ATMfieldService {
    @Autowired
    private ATMfieldRepository atmfieldRepository;

    public ATMfield save(ATMfield atmfield) {
        return atmfieldRepository.save(atmfield);
    }
    public ATMfield findById(Long id) {
        return atmfieldRepository.findById(id).orElse(null);
    }
    public List<ATMfield> findAll() {
        return atmfieldRepository.findAll();
    }
    public void deleteById(Long id) {
        atmfieldRepository.deleteById(id);
    }
    public ATMfield update(ATMfield atmfield , Long id) {
        ATMfield existingATMfield = atmfieldRepository.findById(id).orElse(null);
        existingATMfield.setIdATMfield(atmfield.getIdATMfield());
        existingATMfield.setDescription(atmfield.getDescription());
        existingATMfield.setMessageTypeField(atmfield.getMessageTypeField());
        return atmfieldRepository.save(existingATMfield);
    }
}
