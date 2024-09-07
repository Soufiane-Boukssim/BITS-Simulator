package com.simulator.smartICC.repositories;

import com.simulator.smartICC.models.CpaTagsProfil;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface CpaTagsProfilRepository extends JpaRepository<CpaTagsProfil, Long> {
    // You can add custom query methods here if needed
    List<CpaTagsProfil> findByProfileCode(String profileCode);
    @Transactional
    void deleteByProfileCode(String profileCode);
}