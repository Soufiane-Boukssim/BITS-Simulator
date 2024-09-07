package com.simulator.repository.atm;

import com.simulator.entities.atm.NdcSbCustomerSelectablePin;
import com.simulator.entities.atm.NdcSbCustomerSelectablePinId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NdcSbCustomerSelectablePinRepository extends
		JpaRepository<NdcSbCustomerSelectablePin, NdcSbCustomerSelectablePinId> {
}