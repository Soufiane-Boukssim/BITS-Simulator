package com.simulator.globalController.atm;

import com.simulator.entities.atm.Tmp;
import com.simulator.globalService.atm.TmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/tmp")
public class TmpController {

    @Autowired
    private TmpService tmpService;

    // Create or update a Tmp
    @PostMapping
    public ResponseEntity<Tmp> createOrUpdateTmp(@RequestBody Tmp tmp) {
        Tmp savedTmp = tmpService.saveOrUpdateTmp(tmp);
        return ResponseEntity.ok(savedTmp);
    }

    // Get a Tmp by profile code
    @GetMapping("/{profileCode}")
    public ResponseEntity<Tmp> getTmpByProfileCode(@PathVariable String profileCode) {
        Optional<Tmp> tmp = tmpService.getTmpByProfileCode(profileCode);
        return tmp.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Get all Tmp entries
    @GetMapping
    public ResponseEntity<List<Tmp>> getAllTmps() {
        List<Tmp> tmpList = tmpService.getAllTmps();
        return ResponseEntity.ok(tmpList);
    }

    // Delete a Tmp by profile code
    @DeleteMapping("/{profileCode}")
    public ResponseEntity<Void> deleteTmpByProfileCode(@PathVariable String profileCode) {
        tmpService.deleteTmpByProfileCode(profileCode);
        return ResponseEntity.noContent().build();
    }
}
