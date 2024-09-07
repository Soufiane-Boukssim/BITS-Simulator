package com.simulator.globalController.atm;

import com.simulator.entities.atm.FGColour;
import com.simulator.globalService.atm.FGColourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/fgcolours")
public class FGColourController {

    @Autowired
    private FGColourService fgColourService;

    // Create or update a FG colour
    @PostMapping
    public ResponseEntity<FGColour> createOrUpdateFGColour(@RequestBody FGColour fgColour) {
        FGColour savedFGColour = fgColourService.saveOrUpdateFGColour(fgColour);
        return ResponseEntity.ok(savedFGColour);
    }

    // Get a FG colour by code
    @GetMapping("/{code}")
    public ResponseEntity<FGColour> getFGColourByCode(@PathVariable String code) {
        Optional<FGColour> fgColour = fgColourService.getFGColourByCode(code);
        return fgColour.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Get all FG colours
    @GetMapping
    public ResponseEntity<List<FGColour>> getAllFGColours() {
        List<FGColour> fgColours = fgColourService.getAllFGColours();
        return ResponseEntity.ok(fgColours);
    }

    // Delete a FG colour by code
    @DeleteMapping("/{code}")
    public ResponseEntity<Void> deleteFGColourByCode(@PathVariable String code) {
        fgColourService.deleteFGColourByCode(code);
        return ResponseEntity.noContent().build();
    }
}
