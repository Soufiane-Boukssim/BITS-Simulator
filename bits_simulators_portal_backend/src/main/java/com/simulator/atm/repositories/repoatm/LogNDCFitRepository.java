package com.simulator.atm.repositories.repoatm;

import com.simulator.entities.LogNDCFit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LogNDCFitRepository extends JpaRepository<LogNDCFit, String> {
    List<LogNDCFit> findById_BankCode(String bankCode);
}
