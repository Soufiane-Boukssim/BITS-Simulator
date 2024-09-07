package com.simulator.repository.atm;

import com.simulator.entities.atm.NdcSpvergIccReInitialise;
import com.simulator.entities.atm.NdcSpvergIccReInitialiseId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NdcSpvergIccReInitialiseRepository
		extends
			JpaRepository<NdcSpvergIccReInitialise, NdcSpvergIccReInitialiseId> {
}