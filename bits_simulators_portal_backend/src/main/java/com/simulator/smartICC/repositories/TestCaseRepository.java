package com.simulator.smartICC.repositories;

import com.simulator.smartICC.models.TestCases;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TestCaseRepository extends JpaRepository<TestCases, Long> {
    List<TestCases> findByProfileCode(String profileCode);

    @Transactional
    void deleteByProfileCode(String profileCode);
}
