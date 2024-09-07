package com.simulator.smartICC.controllers;

import com.simulator.smartICC.models.EmvTag;
import com.simulator.smartICC.requests.GlobalResponse;
import com.simulator.smartICC.services.EmvTagService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/icc/emvtag")
@SecurityRequirement(name = "Bearer Authentication")
public class EmvTagController {

    private final EmvTagService emvTagService;

    @Autowired
    public EmvTagController(EmvTagService emvTagService) {
        this.emvTagService = emvTagService;
    }

    @GetMapping("/getAll")
    public GlobalResponse<List<EmvTag>> getAllEmvTags() {
        return emvTagService.getAllTags(); // Utilisez la méthode correcte ici
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<EmvTag> getTag(@PathVariable("id") String id) {
        try {
            EmvTag emvTag = emvTagService.getTag(id);
            if (emvTag != null) {
                return new ResponseEntity<>(emvTag, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



    @PostMapping("/save")
    public ResponseEntity<GlobalResponse<EmvTag>> saveNewTag(@RequestBody EmvTag emvTag) {
        GlobalResponse<EmvTag> response = emvTagService.saveTag(emvTag);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }





}