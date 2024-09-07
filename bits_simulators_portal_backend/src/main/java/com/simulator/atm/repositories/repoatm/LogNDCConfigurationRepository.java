package com.simulator.atm.repositories.repoatm;

import com.simulator.entities.LogNDCConfiguration;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LogNDCConfigurationRepository extends JpaRepository<LogNDCConfiguration, String> {
    List<LogNDCConfiguration> findById_BankCode(String bankCode);
}
