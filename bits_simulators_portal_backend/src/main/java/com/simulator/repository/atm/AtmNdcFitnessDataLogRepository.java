package com.simulator.repository.atm;

import com.simulator.entities.atm.AtmNdcFitnessDataLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AtmNdcFitnessDataLogRepository extends JpaRepository<AtmNdcFitnessDataLog, String> {
}