package com.simulator.repository.atm;

import com.simulator.entities.atm.NdcSjClose;
import com.simulator.entities.atm.NdcSjCloseId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NdcSjCloseRepository extends JpaRepository<NdcSjClose, NdcSjCloseId> {
}