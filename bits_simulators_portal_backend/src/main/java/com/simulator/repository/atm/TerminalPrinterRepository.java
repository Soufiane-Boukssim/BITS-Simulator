package com.simulator.repository.atm;

import com.simulator.entities.TerminalDefinitionId;
import com.simulator.entities.TerminalPrinter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TerminalPrinterRepository extends JpaRepository<TerminalPrinter, TerminalDefinitionId> {
    Optional<TerminalPrinter> findById_BankCode(String id_bankCode);
}
