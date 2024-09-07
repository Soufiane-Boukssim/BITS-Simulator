package com.simulator.repository.atm;

import com.simulator.entities.atm.NdcFit;
import com.simulator.entities.atm.NdcFitId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NdcFitRepository extends JpaRepository<NdcFit, NdcFitId> {
}