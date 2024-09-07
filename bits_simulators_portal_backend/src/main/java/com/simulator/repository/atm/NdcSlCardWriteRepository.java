package com.simulator.repository.atm;

import com.simulator.entities.atm.NdcSlCardWrite;
import com.simulator.entities.atm.NdcSlCardWriteId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NdcSlCardWriteRepository extends JpaRepository<NdcSlCardWrite, NdcSlCardWriteId> {
}