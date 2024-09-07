package com.simulator.repository.atm;

import com.simulator.entities.atm.NdcSbPinEntry;
import com.simulator.entities.atm.NdcSbPinEntryId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NdcSbPinEntryRepository extends JpaRepository<NdcSbPinEntry, NdcSbPinEntryId> {
}