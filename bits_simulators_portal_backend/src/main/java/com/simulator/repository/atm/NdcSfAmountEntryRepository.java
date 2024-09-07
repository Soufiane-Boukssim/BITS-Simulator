package com.simulator.repository.atm;

import com.simulator.entities.atm.NdcSfAmountEntry;
import com.simulator.entities.atm.NdcSfAmountEntryId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NdcSfAmountEntryRepository extends JpaRepository<NdcSfAmountEntry, NdcSfAmountEntryId> {
}