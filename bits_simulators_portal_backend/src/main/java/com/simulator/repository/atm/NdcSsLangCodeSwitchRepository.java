package com.simulator.repository.atm;

import com.simulator.entities.atm.NdcSsLangCodeSwitch;
import com.simulator.entities.atm.NdcSsLangCodeSwitchId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NdcSsLangCodeSwitchRepository extends JpaRepository<NdcSsLangCodeSwitch, NdcSsLangCodeSwitchId> {
}