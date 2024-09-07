package com.simulator.repository.atm;

import com.simulator.entities.TerminalDefinitionId;
import com.simulator.entities.TerminalKeys;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TerminalKeysRepository extends JpaRepository<TerminalKeys, TerminalDefinitionId> {
    Optional<TerminalKeys> findById_BankCode(String id_bankCode);
}
