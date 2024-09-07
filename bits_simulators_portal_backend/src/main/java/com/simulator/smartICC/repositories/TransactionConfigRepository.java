package com.simulator.smartICC.repositories;

import com.simulator.smartICC.models.TransactionConfig;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TransactionConfigRepository extends JpaRepository<TransactionConfig, String> {
    Optional<TransactionConfig> findByCodeProfile(String codeProfile);
}
