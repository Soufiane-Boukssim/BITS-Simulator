package com.simulator.repository.atm;

import com.simulator.entities.atm.NdcSe4FdkSelection;
import com.simulator.entities.atm.NdcSe4FdkSelectionId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NdcSe4FdkSelectionRepository extends JpaRepository<NdcSe4FdkSelection, NdcSe4FdkSelectionId> {
}