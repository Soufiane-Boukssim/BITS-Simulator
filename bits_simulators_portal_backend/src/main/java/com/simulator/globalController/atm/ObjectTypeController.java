package com.simulator.globalController.atm;

import com.simulator.entities.atm.ObjectType;
import com.simulator.globalService.atm.ObjectTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/object-types")
public class ObjectTypeController {

    @Autowired
    private ObjectTypeService objectTypeService;

    // Create or update an ObjectType
    @PostMapping
    public ResponseEntity<ObjectType> createOrUpdateObjectType(@RequestBody ObjectType objectType) {
        ObjectType savedObjectType = objectTypeService.saveOrUpdateObjectType(objectType);
        return ResponseEntity.ok(savedObjectType);
    }

    // Get an ObjectType by code
    @GetMapping("/{code}")
    public ResponseEntity<ObjectType> getObjectTypeByCode(@PathVariable int code) {
        Optional<ObjectType> objectType = objectTypeService.getObjectTypeByCode(code);
        return objectType.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Get all ObjectTypes
    @GetMapping
    public ResponseEntity<List<ObjectType>> getAllObjectTypes() {
        List<ObjectType> objectTypes = objectTypeService.getAllObjectTypes();
        return ResponseEntity.ok(objectTypes);
    }

    // Delete an ObjectType by code
    @DeleteMapping("/{code}")
    public ResponseEntity<Void> deleteObjectTypeByCode(@PathVariable int code) {
        objectTypeService.deleteObjectTypeByCode(code);
        return ResponseEntity.noContent().build();
    }
}
