package com.simulator.repository.atm;

import com.simulator.entities.atm.NdcSxFdkInfoEntry;
import com.simulator.entities.atm.NdcSxFdkInfoEntryId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NdcSxFdkInfoEntryRepository extends JpaRepository<NdcSxFdkInfoEntry, NdcSxFdkInfoEntryId> {
}