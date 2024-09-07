package com.simulator.repository.atm;

import com.simulator.entities.atm.NdcStCardReadPinEnt;
import com.simulator.entities.atm.NdcStCardReadPinEntId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NdcStCardReadPinEntRepository extends JpaRepository<NdcStCardReadPinEnt, NdcStCardReadPinEntId> {
}