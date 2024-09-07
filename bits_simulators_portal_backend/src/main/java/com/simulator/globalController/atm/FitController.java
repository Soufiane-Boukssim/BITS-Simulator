package com.simulator.globalController.atm;

import com.simulator.entities.atm.Fit;
import com.simulator.globalService.atm.FitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/fits")
public class FitController {

    @Autowired
    private FitService fitService;

    // Create or update a Fit
    @PostMapping
    public ResponseEntity<Fit> createOrUpdateFit(@RequestBody Fit fit) {
        Fit savedFit = fitService.saveOrUpdateFit(fit);
        return ResponseEntity.ok(savedFit);
    }

    // Get a Fit by hex
    @GetMapping("/{hex}")
    public ResponseEntity<Fit> getFitByHex(@PathVariable String hex) {
        Optional<Fit> fit = fitService.getFitByHex(hex);
        return fit.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Get all Fits
    @GetMapping
    public ResponseEntity<List<Fit>> getAllFits() {
        List<Fit> fits = fitService.getAllFits();
        return ResponseEntity.ok(fits);
    }

    // Delete a Fit by hex
    @DeleteMapping("/{hex}")
    public ResponseEntity<Void> deleteFitByHex(@PathVariable String hex) {
        fitService.deleteFitByHex(hex);
        return ResponseEntity.noContent().build();
    }
}
