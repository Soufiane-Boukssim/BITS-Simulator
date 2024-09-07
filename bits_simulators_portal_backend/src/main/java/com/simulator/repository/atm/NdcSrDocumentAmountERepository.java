package com.simulator.repository.atm;

import com.simulator.entities.atm.NdcSrDocumentAmountE;
import com.simulator.entities.atm.NdcSrDocumentAmountEId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NdcSrDocumentAmountERepository extends JpaRepository<NdcSrDocumentAmountE, NdcSrDocumentAmountEId> {
}