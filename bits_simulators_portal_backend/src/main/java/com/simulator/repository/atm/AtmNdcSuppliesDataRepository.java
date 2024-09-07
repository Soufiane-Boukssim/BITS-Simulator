package com.simulator.repository.atm;

import com.simulator.entities.atm.AtmNdcSuppliesData;
import com.simulator.entities.atm.AtmNdcSuppliesDataId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AtmNdcSuppliesDataRepository extends JpaRepository<AtmNdcSuppliesData, AtmNdcSuppliesDataId> {
}