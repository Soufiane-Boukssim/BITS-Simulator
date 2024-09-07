package com.simulator.repository.atm;

import com.simulator.entities.atm.NdcEmvCurrency;
import com.simulator.entities.atm.NdcEmvCurrencyId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NdcEmvCurrencyRepository extends JpaRepository<NdcEmvCurrency, NdcEmvCurrencyId> {
}