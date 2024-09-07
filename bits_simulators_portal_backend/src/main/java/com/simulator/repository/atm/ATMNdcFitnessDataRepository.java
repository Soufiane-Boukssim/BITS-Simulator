package com.simulator.repository.atm;

import com.simulator.entities.atm.ATMNdcFitnessData;
import com.simulator.entities.atm.ATMNdcFitnessDataId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ATMNdcFitnessDataRepository extends JpaRepository<ATMNdcFitnessData, ATMNdcFitnessDataId> {
}