package com.simulator.repository.atm;

import com.simulator.entities.TerminalMessNonSoliciteId;
import com.simulator.entities.TerminalMessNonsolicite;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TerminalMessNonsoliciteRepository extends JpaRepository<TerminalMessNonsolicite, TerminalMessNonSoliciteId> {
    List<TerminalMessNonsolicite> findById_BankCode(String bankCode);
}
