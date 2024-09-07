package com.simulator.repository.atm;

import com.simulator.entities.atm.NdcEmvTerminal;
import com.simulator.entities.atm.NdcEmvTerminalId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NdcEmvTerminalRepository extends JpaRepository<NdcEmvTerminal, NdcEmvTerminalId> {
}