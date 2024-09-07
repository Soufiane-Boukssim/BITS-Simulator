package com.simulator.repository.atm;

import com.simulator.entities.atm.NdcStateType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NdcStateTypeRepository extends JpaRepository<NdcStateType, Character> {
}