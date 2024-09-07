package com.simulator.repository.atm;

import com.simulator.entities.atm.NdcState;
import com.simulator.entities.atm.NdcStateId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NdcStateRepository extends JpaRepository<NdcState, NdcStateId> {
}