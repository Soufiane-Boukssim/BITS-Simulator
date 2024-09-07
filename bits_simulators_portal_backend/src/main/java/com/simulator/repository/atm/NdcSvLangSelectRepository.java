package com.simulator.repository.atm;

import com.simulator.entities.atm.NdcSvLangSelect;
import com.simulator.entities.atm.NdcSvLangSelectId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NdcSvLangSelectRepository extends JpaRepository<NdcSvLangSelect, NdcSvLangSelectId> {
}