package com.simulator.repository.atm;

import com.simulator.entities.TerminalConfiguration;
import com.simulator.entities.TerminalDefinitionId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TerminalConfigurationRepository extends JpaRepository<TerminalConfiguration, TerminalDefinitionId> {
    Optional<TerminalConfiguration> findById_BankCode(String id_bankCode);
}
