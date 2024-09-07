package com.simulator.atm.repositories.repoatm;

import com.simulator.entities.LogNDCTimer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LogNDCTimerRepository extends JpaRepository<LogNDCTimer,String> {
    List<LogNDCTimer> findById_BankCode(String bankCode);
}
