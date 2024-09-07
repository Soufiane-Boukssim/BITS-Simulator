package com.simulator.globalService.atm;

import com.simulator.entities.atm.Tmp;
import com.simulator.repository.atm.TmpRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TmpService {

    @Autowired
    private TmpRepository tmpRepository;

    // Save or update a Tmp
    public Tmp saveOrUpdateTmp(Tmp tmp) {
        return tmpRepository.save(tmp);
    }

    // Get a Tmp by profile code
    public Optional<Tmp> getTmpByProfileCode(String profileCode) {
        return tmpRepository.findById(profileCode);
    }

    // Get all Tmp entries
    public List<Tmp> getAllTmps() {
        return tmpRepository.findAll();
    }

    // Delete a Tmp by profile code
    public void deleteTmpByProfileCode(String profileCode) {
        tmpRepository.deleteById(profileCode);
    }
}
