package com.simulator.repository.atm;

import com.simulator.entities.atm.NdcTimersProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NdcTimersProfileRepository extends JpaRepository<NdcTimersProfile, Character> {
}