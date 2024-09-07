package com.simulator.repository.atm;

import com.simulator.entities.atm.NdcDisplayPolice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NdcDisplayPoliceRepository extends JpaRepository<NdcDisplayPolice, String> {
}