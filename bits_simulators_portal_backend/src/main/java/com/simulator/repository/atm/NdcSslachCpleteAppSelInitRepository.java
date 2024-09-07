package com.simulator.repository.atm;

import com.simulator.entities.atm.NdcSslachCpleteAppSelInit;
import com.simulator.entities.atm.NdcSslachCpleteAppSelInitId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NdcSslachCpleteAppSelInitRepository
		extends
			JpaRepository<NdcSslachCpleteAppSelInit, NdcSslachCpleteAppSelInitId> {
}