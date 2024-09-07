package com.simulator.repository.atm;

import com.simulator.entities.atm.NdcSgAmountCheck;
import com.simulator.entities.atm.NdcSgAmountCheckId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NdcSgAmountCheckRepository extends JpaRepository<NdcSgAmountCheck, NdcSgAmountCheckId> {
}