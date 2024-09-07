package com.simulator.repository.atm;

import com.simulator.entities.atm.NdcSvergCompleteIccInit;
import com.simulator.entities.atm.NdcSvergCompleteIccInitId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NdcSvergCompleteIccInitRepository
		extends
			JpaRepository<NdcSvergCompleteIccInit, NdcSvergCompleteIccInitId> {
}