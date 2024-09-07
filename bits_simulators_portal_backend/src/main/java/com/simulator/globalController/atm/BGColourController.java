package com.simulator.globalController.atm;

import com.simulator.entities.atm.BGColour;
import com.simulator.globalService.atm.BGColourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/bg-colours")
public class BGColourController {

    @Autowired
    private BGColourService bgColourService;

    // Create or update a BG colour
    @PostMapping
    public ResponseEntity<BGColour> createOrUpdateBGColour(@RequestBody BGColour bgColour) {
        BGColour savedColour = bgColourService.saveOrUpdateBGColour(bgColour);
        return ResponseEntity.ok(savedColour);
    }

    // Get a BG colour by code
    @GetMapping("/{code}")
    public ResponseEntity<BGColour> getBGColourByCode(@PathVariable String code) {
        Optional<BGColour> bgColour = bgColourService.getBGColourByCode(code);
        return bgColour.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Get all BG colours
    @GetMapping
    public ResponseEntity<List<BGColour>> getAllBGColours() {
        List<BGColour> bgColours = bgColourService.getAllBGColours();
        return ResponseEntity.ok(bgColours);
    }

    // Delete a BG colour by code
    @DeleteMapping("/{code}")
    public ResponseEntity<Void> deleteBGColourByCode(@PathVariable String code) {
        bgColourService.deleteBGColourByCode(code);
        return ResponseEntity.noContent().build();
    }
}
