package com.simulator.repository.atm;

import com.simulator.entities.atm.NdcEmvLanguage;
import com.simulator.entities.atm.NdcEmvLanguageId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NdcEmvLanguageRepository extends JpaRepository<NdcEmvLanguage, NdcEmvLanguageId> {
}