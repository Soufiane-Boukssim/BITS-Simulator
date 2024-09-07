package com.simulator.repository.atm;

import com.simulator.entities.atm.NdcSpointBeginAppSelInit;
import com.simulator.entities.atm.NdcSpointBeginAppSelInitId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NdcSpointBeginAppSelInitRepository
		extends
			JpaRepository<NdcSpointBeginAppSelInit, NdcSpointBeginAppSelInitId> {
}