package com.simulator.atm.repositories.repoatm;

import com.simulator.entities.LogNDCState;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LogNDCStateRepository extends JpaRepository<LogNDCState, String> {
    List<LogNDCState> findById_BankCode(String bankCode);
}
