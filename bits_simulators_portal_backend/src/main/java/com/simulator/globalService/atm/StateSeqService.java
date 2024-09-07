package com.simulator.globalService.atm;


import com.simulator.entities.atm.StateSeq;
import com.simulator.repository.atm.StateSeqRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StateSeqService {

    @Autowired
    private StateSeqRepository stateSeqRepository;

    // Save or update a StateSeq
    public StateSeq saveOrUpdateStateSeq(StateSeq stateSeq) {
        return stateSeqRepository.save(stateSeq);
    }

    // Get a StateSeq by profile code
    public Optional<StateSeq> getStateSeqByProfileCode(String profileCode) {
        return stateSeqRepository.findById(profileCode);
    }

    // Get all StateSeqs
    public List<StateSeq> getAllStateSeqs() {
        return stateSeqRepository.findAll();
    }

    // Delete a StateSeq by profile code
    public void deleteStateSeqByProfileCode(String profileCode) {
        stateSeqRepository.deleteById(profileCode);
    }
}
