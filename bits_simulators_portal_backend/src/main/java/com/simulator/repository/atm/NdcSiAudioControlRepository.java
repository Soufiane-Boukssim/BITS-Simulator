package com.simulator.repository.atm;

import com.simulator.entities.atm.NdcSiAudioControl;
import com.simulator.entities.atm.NdcSiAudioControlId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NdcSiAudioControlRepository extends JpaRepository<NdcSiAudioControl, NdcSiAudioControlId> {
}