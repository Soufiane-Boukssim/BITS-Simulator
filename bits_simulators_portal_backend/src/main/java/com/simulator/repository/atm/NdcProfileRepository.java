package com.simulator.repository.atm;

import com.simulator.entities.atm.NdcProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NdcProfileRepository extends JpaRepository<NdcProfile, String> {
}