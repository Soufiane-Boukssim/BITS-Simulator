package com.simulator.repository.atm;

import com.simulator.entities.atm.NdcSiterogSetIccTrxData;
import com.simulator.entities.atm.NdcSiterogSetIccTrxDataId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NdcSiterogSetIccTrxDataRepository
		extends
			JpaRepository<NdcSiterogSetIccTrxData, NdcSiterogSetIccTrxDataId> {
}