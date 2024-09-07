package com.simulator.globalController.atm;

import com.simulator.entities.atm.Pindx;
import com.simulator.globalService.atm.PindxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/pindx")
public class PindxController {

    @Autowired
    private PindxService pindxService;

    // Create or update a Pindx
    @PostMapping
    public ResponseEntity<Pindx> createOrUpdatePindx(@RequestBody Pindx pindx) {
        Pindx savedPindx = pindxService.saveOrUpdatePindx(pindx);
        return ResponseEntity.ok(savedPindx);
    }

    // Get a Pindx by hex
    @GetMapping("/{hex}")
    public ResponseEntity<Pindx> getPindxByHex(@PathVariable String hex) {
        Optional<Pindx> pindx = pindxService.getPindxByHex(hex);
        return pindx.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Get all Pindx
    @GetMapping
    public ResponseEntity<List<Pindx>> getAllPindx() {
        List<Pindx> pindxList = pindxService.getAllPindx();
        return ResponseEntity.ok(pindxList);
    }

    // Delete a Pindx by hex
    @DeleteMapping("/{hex}")
    public ResponseEntity<Void> deletePindxByHex(@PathVariable String hex) {
        pindxService.deletePindxByHex(hex);
        return ResponseEntity.noContent().build();
    }
}
