package com.simulator.atm.repositories.reposcript;

import com.simulator.atm.model.SenarioAtm;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SenarioAtmRepository extends JpaRepository<SenarioAtm, Long> {
    List<SenarioAtm> findByBankCode(String bankCode);

}
