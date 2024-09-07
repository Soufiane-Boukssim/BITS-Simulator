package com.simulator.repository.atm;

import com.simulator.entities.atm.NdcDisplayText;
import com.simulator.entities.atm.NdcDisplayTextId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NdcDisplayTextRepository extends JpaRepository<NdcDisplayText, NdcDisplayTextId> {
}