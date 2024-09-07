package com.simulator.repository.atm;

import com.simulator.entities.atm.NdcSwFdkSwitch;
import com.simulator.entities.atm.NdcSwFdkSwitchId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NdcSwFdkSwitchRepository extends JpaRepository<NdcSwFdkSwitch, NdcSwFdkSwitchId> {
}