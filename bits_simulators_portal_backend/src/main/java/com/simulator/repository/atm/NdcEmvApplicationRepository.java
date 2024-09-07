package com.simulator.repository.atm;

import com.simulator.entities.atm.NdcEmvApplication;
import com.simulator.entities.atm.NdcEmvApplicationId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NdcEmvApplicationRepository extends JpaRepository<NdcEmvApplication, NdcEmvApplicationId> {
}