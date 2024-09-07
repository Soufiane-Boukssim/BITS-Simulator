package com.simulator.repository;

import com.simulator.entities.FieldsDefinition;
import com.simulator.entities.FieldsDefinitionId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FieldsDefinitionRepository extends JpaRepository<FieldsDefinition, FieldsDefinitionId> {
}
