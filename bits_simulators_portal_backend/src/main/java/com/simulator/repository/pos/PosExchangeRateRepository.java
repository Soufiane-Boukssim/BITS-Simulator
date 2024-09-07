package com.simulator.repository.pos;

import com.simulator.entities.pos.PosExchangeRateParam;
import com.simulator.entities.pos.PosExchangeRateParamId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PosExchangeRateRepository extends JpaRepository<PosExchangeRateParam, PosExchangeRateParamId> {
    @Query("SELECT m FROM PosExchangeRateParam m WHERE m.id.bankCode = :bankCode AND m.id.rateProtocol = :rateProtocol")
    List<PosExchangeRateParam> findByBankCodeAndRateProtocol(@Param("bankCode") String bankCode, @Param("rateProtocol") String rateProtocol);


}
