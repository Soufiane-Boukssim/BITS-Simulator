package com.simulator.globalController.atm;

import com.simulator.entities.atm.TagsManagement;
import com.simulator.globalService.atm.TagsManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/tags-management")
public class TagsManagementController {

    @Autowired
    private TagsManagementService tagsManagementService;

    // Create or update a TagsManagement
    @PostMapping
    public ResponseEntity<TagsManagement> createOrUpdateTagsManagement(@RequestBody TagsManagement tagsManagement) {
        TagsManagement savedTagsManagement = tagsManagementService.saveOrUpdateTagsManagement(tagsManagement);
        return ResponseEntity.ok(savedTagsManagement);
    }

    // Get a TagsManagement by tag code
    @GetMapping("/{tagCode}")
    public ResponseEntity<TagsManagement> getTagsManagementByTagCode(@PathVariable String tagCode) {
        Optional<TagsManagement> tagsManagement = tagsManagementService.getTagsManagementByTagCode(tagCode);
        return tagsManagement.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Get all TagsManagement
    @GetMapping
    public ResponseEntity<List<TagsManagement>> getAllTagsManagement() {
        List<TagsManagement> tagsManagementList = tagsManagementService.getAllTagsManagement();
        return ResponseEntity.ok(tagsManagementList);
    }

    // Delete a TagsManagement by tag code
    @DeleteMapping("/{tagCode}")
    public ResponseEntity<Void> deleteTagsManagementByTagCode(@PathVariable String tagCode) {
        tagsManagementService.deleteTagsManagementByTagCode(tagCode);
        return ResponseEntity.noContent().build();
    }
}
