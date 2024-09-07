package com.simulator.globalController.atm;

import com.simulator.entities.atm.Langue;
import com.simulator.globalService.atm.LangueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/langues")
public class LangueController {

    @Autowired
    private LangueService langueService;

    // Create or update a Langue
    @PostMapping
    public ResponseEntity<Langue> createOrUpdateLangue(@RequestBody Langue langue) {
        Langue savedLangue = langueService.saveOrUpdateLangue(langue);
        return ResponseEntity.ok(savedLangue);
    }

    // Get a Langue by langueCode
    @GetMapping("/{langueCode}")
    public ResponseEntity<Langue> getLangueByCode(@PathVariable String langueCode) {
        Optional<Langue> langue = langueService.getLangueByCode(langueCode);
        return langue.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Get all Langues
    @GetMapping
    public ResponseEntity<List<Langue>> getAllLangues() {
        List<Langue> langues = langueService.getAllLangues();
        return ResponseEntity.ok(langues);
    }

    // Delete a Langue by langueCode
    @DeleteMapping("/{langueCode}")
    public ResponseEntity<Void> deleteLangueByCode(@PathVariable String langueCode) {
        langueService.deleteLangueByCode(langueCode);
        return ResponseEntity.noContent().build();
    }
}
