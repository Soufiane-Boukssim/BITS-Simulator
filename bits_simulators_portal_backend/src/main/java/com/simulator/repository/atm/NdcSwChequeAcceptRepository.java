package com.simulator.repository.atm;

import com.simulator.entities.atm.NdcSwChequeAccept;
import com.simulator.entities.atm.NdcSwChequeAcceptId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NdcSwChequeAcceptRepository extends JpaRepository<NdcSwChequeAccept, NdcSwChequeAcceptId> {
}