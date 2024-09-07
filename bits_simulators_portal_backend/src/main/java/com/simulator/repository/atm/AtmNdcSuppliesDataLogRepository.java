package com.simulator.repository.atm;

import com.simulator.entities.atm.AtmNdcSuppliesDataLogId;
import com.simulator.entities.atm.AtmNdcSuppliesDataLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AtmNdcSuppliesDataLogRepository extends JpaRepository<AtmNdcSuppliesDataLog, AtmNdcSuppliesDataLogId> {
}