package com.simulator.smartICC.repositories;

import com.simulator.smartICC.models.StepsResult;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StepsResultRepository extends JpaRepository<StepsResult, Long> {

    List<StepsResult> findByProfileCode(String profileCode);

}
