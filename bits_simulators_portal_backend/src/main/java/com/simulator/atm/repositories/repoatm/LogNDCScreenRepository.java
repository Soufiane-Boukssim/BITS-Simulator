package com.simulator.atm.repositories.repoatm;

import com.simulator.entities.LogNDCScreen;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LogNDCScreenRepository extends JpaRepository<LogNDCScreen, String> {
    List<LogNDCScreen> findById_BankCode(String bankCode);
}
