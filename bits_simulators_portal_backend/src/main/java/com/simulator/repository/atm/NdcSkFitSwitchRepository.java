package com.simulator.repository.atm;

import com.simulator.entities.atm.NdcSkFitSwitch;
import com.simulator.entities.atm.NdcSkFitSwitchId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NdcSkFitSwitchRepository extends JpaRepository<NdcSkFitSwitch, NdcSkFitSwitchId> {
}