package com.simulator.repository.atm;

import com.simulator.entities.atm.NdcSaCardRead;
import com.simulator.entities.atm.NdcSaCardReadId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NdcSaCardReadRepository extends JpaRepository<NdcSaCardRead, NdcSaCardReadId> {
}