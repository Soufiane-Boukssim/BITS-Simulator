package com.simulator.smartICC.repositories;

import com.simulator.smartICC.models.TerminalConfig;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TerminalConfigRepository extends JpaRepository<TerminalConfig, String> {
    Optional<TerminalConfig> findByCodeProfile(String codeProfile);
}