package com.simulator.globalService.atm;


import com.simulator.entities.atm.ObjectType;
import com.simulator.repository.atm.ObjectTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ObjectTypeService {

    @Autowired
    private ObjectTypeRepository objectTypeRepository;

    // Save or update an ObjectType
    public ObjectType saveOrUpdateObjectType(ObjectType objectType) {
        return objectTypeRepository.save(objectType);
    }

    // Get an ObjectType by code
    public Optional<ObjectType> getObjectTypeByCode(int code) {
        return objectTypeRepository.findById(code);
    }

    // Get all ObjectTypes
    public List<ObjectType> getAllObjectTypes() {
        return objectTypeRepository.findAll();
    }

    // Delete an ObjectType by code
    public void deleteObjectTypeByCode(int code) {
        objectTypeRepository.deleteById(code);
    }
}
