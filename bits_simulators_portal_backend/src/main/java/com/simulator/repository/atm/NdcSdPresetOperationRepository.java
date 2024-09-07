package com.simulator.repository.atm;

import com.simulator.entities.atm.NdcSdPresetOperation;
import com.simulator.entities.atm.NdcSdPresetOperationId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NdcSdPresetOperationRepository extends JpaRepository<NdcSdPresetOperation, NdcSdPresetOperationId> {
}