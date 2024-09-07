package com.simulator.smartICC.repositories;

import com.simulator.smartICC.models.IssuerConfig;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IssuerConfigRepository extends JpaRepository<IssuerConfig, String> {
    Optional<IssuerConfig> findByCodeProfile(String codeProfile);
}
