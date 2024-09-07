package com.simulator.atm.repositories.reposcript;

import com.simulator.atm.model.ATMfield;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ATMfieldRepository extends JpaRepository<ATMfield, Long> {
    ATMfield findFirstByDescription(String atmfieldDescription);
}
