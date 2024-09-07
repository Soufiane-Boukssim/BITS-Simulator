package com.simulator.repository.atm;

import com.simulator.entities.atm.NdcEmvTransaction;
import com.simulator.entities.atm.NdcEmvTransactionId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NdcEmvTransactionRepository extends JpaRepository<NdcEmvTransaction, NdcEmvTransactionId> {
}