package com.simulator.repository.atm;

import com.simulator.entities.atm.NdcSplusBeginIccInit;
import com.simulator.entities.atm.NdcSplusBeginIccInitId;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NdcSplusBeginIccInitRepository extends JpaRepository<NdcSplusBeginIccInit, NdcSplusBeginIccInitId> {
}