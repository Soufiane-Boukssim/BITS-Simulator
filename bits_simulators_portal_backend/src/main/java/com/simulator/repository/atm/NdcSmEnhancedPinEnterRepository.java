package com.simulator.repository.atm;

import com.simulator.entities.atm.NdcSmEnhancedPinEnter;
import com.simulator.entities.atm.NdcSmEnhancedPinEnterId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NdcSmEnhancedPinEnterRepository extends JpaRepository<NdcSmEnhancedPinEnter, NdcSmEnhancedPinEnterId> {
}