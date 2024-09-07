package com.simulator.globalController.atm;


import com.simulator.entities.atm.StateSeq;
import com.simulator.globalService.atm.StateSeqService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/state-seq")
public class StateSeqController {

    @Autowired
    private StateSeqService stateSeqService;

    // Create or update a StateSeq
    @PostMapping
    public ResponseEntity<StateSeq> createOrUpdateStateSeq(@RequestBody StateSeq stateSeq) {
        StateSeq savedStateSeq = stateSeqService.saveOrUpdateStateSeq(stateSeq);
        return ResponseEntity.ok(savedStateSeq);
    }

    // Get a StateSeq by profile code
    @GetMapping("/{profileCode}")
    public ResponseEntity<StateSeq> getStateSeqByProfileCode(@PathVariable String profileCode) {
        Optional<StateSeq> stateSeq = stateSeqService.getStateSeqByProfileCode(profileCode);
        return stateSeq.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Get all StateSeqs
    @GetMapping
    public ResponseEntity<List<StateSeq>> getAllStateSeqs() {
        List<StateSeq> stateSeqList = stateSeqService.getAllStateSeqs();
        return ResponseEntity.ok(stateSeqList);
    }

    // Delete a StateSeq by profile code
    @DeleteMapping("/{profileCode}")
    public ResponseEntity<Void> deleteStateSeqByProfileCode(@PathVariable String profileCode) {
        stateSeqService.deleteStateSeqByProfileCode(profileCode);
        return ResponseEntity.noContent().build();
    }
}
