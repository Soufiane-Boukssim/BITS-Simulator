package com.simulator.repository.atm;

import com.simulator.entities.atm.NdcScUnlockDispenser;
import com.simulator.entities.atm.NdcScUnlockDispenserId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NdcScUnlockDispenserRepository extends JpaRepository<NdcScUnlockDispenser, NdcScUnlockDispenserId> {
}