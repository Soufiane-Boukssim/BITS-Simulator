package com.simulator.repository.pos;

import com.simulator.entities.pos.PosFieldsDefinition;
import com.simulator.entities.pos.PosFieldsDefinitionId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PosFieldsDefinitionRepository extends JpaRepository<PosFieldsDefinition, PosFieldsDefinitionId> {
}
