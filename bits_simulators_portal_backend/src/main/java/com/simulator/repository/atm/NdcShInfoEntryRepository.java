package com.simulator.repository.atm;

import com.simulator.entities.atm.NdcShInfoEntry;
import com.simulator.entities.atm.NdcShInfoEntryId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NdcShInfoEntryRepository extends JpaRepository<NdcShInfoEntry, NdcShInfoEntryId> {
}