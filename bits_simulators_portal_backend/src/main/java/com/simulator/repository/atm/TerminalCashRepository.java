package com.simulator.repository.atm;

import com.simulator.entities.TerminalCash;
import com.simulator.entities.TerminalDefinitionId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TerminalCashRepository extends JpaRepository<TerminalCash, TerminalDefinitionId> {
    Optional<TerminalCash> findById_BankCode(String id_bankCode);
}
