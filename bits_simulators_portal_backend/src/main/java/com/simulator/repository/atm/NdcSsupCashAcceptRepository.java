package com.simulator.repository.atm;

import com.simulator.entities.atm.NdcSsupCashAccept;
import com.simulator.entities.atm.NdcSsupCashAcceptId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NdcSsupCashAcceptRepository extends JpaRepository<NdcSsupCashAccept, NdcSsupCashAcceptId> {
}