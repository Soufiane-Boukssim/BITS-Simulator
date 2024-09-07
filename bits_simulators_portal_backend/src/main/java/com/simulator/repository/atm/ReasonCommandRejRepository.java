package com.simulator.repository.atm;

import com.simulator.entities.ReasonCommandRej;
import com.simulator.entities.TerminalDefinitionId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReasonCommandRejRepository extends JpaRepository<ReasonCommandRej, TerminalDefinitionId> {

    Optional<ReasonCommandRej> findById_BankCode(String bank);
}

