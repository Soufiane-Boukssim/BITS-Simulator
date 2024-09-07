package com.simulator.repository.atm;

import com.simulator.entities.atm.NdcSiTransRequest;
import com.simulator.entities.atm.NdcSiTransRequestId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NdcSiTransRequestRepository extends JpaRepository<NdcSiTransRequest, NdcSiTransRequestId> {
}