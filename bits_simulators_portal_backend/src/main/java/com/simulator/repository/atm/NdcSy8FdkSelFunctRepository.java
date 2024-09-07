package com.simulator.repository.atm;

import com.simulator.entities.atm.NdcSy8FdkSelFunct;
import com.simulator.entities.atm.NdcSy8FdkSelFunctId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NdcSy8FdkSelFunctRepository extends JpaRepository<NdcSy8FdkSelFunct, NdcSy8FdkSelFunctId> {
}