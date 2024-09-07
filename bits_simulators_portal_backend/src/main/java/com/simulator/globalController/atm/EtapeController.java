package com.simulator.globalController.atm;

import com.simulator.entities.atm.Etape;
import com.simulator.globalService.atm.EtapeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

        import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/etapes")
public class EtapeController {

    @Autowired
    private EtapeService etapeService;

    // Create or update an Etape
    @PostMapping
    public ResponseEntity<Etape> createOrUpdateEtape(@RequestBody Etape etape) {
        Etape savedEtape = etapeService.saveOrUpdateEtape(etape);
        return ResponseEntity.ok(savedEtape);
    }

    // Get an Etape by type
    @GetMapping("/{type}")
    public ResponseEntity<Etape> getEtapeByType(@PathVariable String type) {
        Optional<Etape> etape = etapeService.getEtapeByType(type);
        return etape.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Get all Etapes
    @GetMapping
    public ResponseEntity<List<Etape>> getAllEtapes() {
        List<Etape> etapes = etapeService.getAllEtapes();
        return ResponseEntity.ok(etapes);
    }

    // Delete an Etape by type
    @DeleteMapping("/{type}")
    public ResponseEntity<Void> deleteEtapeByType(@PathVariable String type) {
        etapeService.deleteEtapeByType(type);
        return ResponseEntity.noContent().build();
    }
}
