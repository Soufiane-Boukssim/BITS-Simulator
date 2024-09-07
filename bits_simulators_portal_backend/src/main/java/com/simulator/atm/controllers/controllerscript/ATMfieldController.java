package com.simulator.atm.controllers.controllerscript;

import com.simulator.atm.model.ATMfield;
import com.simulator.atm.services.script.ATMfieldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/su/ATMfield")
public class ATMfieldController {

    @Autowired
    private ATMfieldService atmfieldService;

    @PostMapping("/add")
    public ResponseEntity<String> createATMfield(@RequestBody ATMfield atmfield) {
        try {
            ATMfield createdATMfield = atmfieldService.save(atmfield);
            return new ResponseEntity<>("000", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("001", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateATMfield(@PathVariable Long id, @RequestBody ATMfield updatedATMfield) {
        try {
            ATMfield update = atmfieldService.update(updatedATMfield, id);
            return new ResponseEntity<>("000", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("001", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteATMfield(@PathVariable Long id) {
        try {
            atmfieldService.deleteById(id);
            return new ResponseEntity<>("000", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("001", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<ATMfield>> getAllATMfields() {
        List<ATMfield> atmfields = atmfieldService.findAll();
        return new ResponseEntity<>(atmfields, HttpStatus.OK);
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<ATMfield> getATMfieldById(@PathVariable Long id) {
        ATMfield atmfield = atmfieldService.findById(id);
        return new ResponseEntity<>(atmfield, HttpStatus.OK);
    }
}
