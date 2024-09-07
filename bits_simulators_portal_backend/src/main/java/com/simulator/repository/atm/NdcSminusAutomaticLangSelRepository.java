package com.simulator.repository.atm;

import com.simulator.entities.atm.NdcSminusAutomaticLangSel;
import com.simulator.entities.atm.NdcSminusAutomaticLangSelId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NdcSminusAutomaticLangSelRepository
		extends
			JpaRepository<NdcSminusAutomaticLangSel, NdcSminusAutomaticLangSelId> {
}