package com.simulator.repository.atm;

import com.simulator.entities.atm.NdcDenominationType;
import com.simulator.entities.atm.NdcDenominationTypeId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NdcDenominationTypeRepository extends JpaRepository<NdcDenominationType, NdcDenominationTypeId> {
}