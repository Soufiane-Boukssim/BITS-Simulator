package com.simulator.repository.atm;

import com.simulator.entities.TerminalDefinition;
import com.simulator.entities.TerminalDefinitionId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TerminalDefinitionRepository extends JpaRepository<TerminalDefinition, TerminalDefinitionId> {

    Optional<TerminalDefinition> findById_BankCode(String id_bankCode);


}
