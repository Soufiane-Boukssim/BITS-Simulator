package com.simulator.repository.atm;

import com.simulator.entities.atm.NdcSkSmartFitCheck;
import com.simulator.entities.atm.NdcSkSmartFitCheckId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NdcSkSmartFitCheckRepository extends JpaRepository<NdcSkSmartFitCheck, NdcSkSmartFitCheckId> {
}