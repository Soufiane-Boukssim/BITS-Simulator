package com.simulator.repository;

import com.simulator.entities.ExchangeRateParam;
import com.simulator.entities.ExchangeRateParamId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExchangeRateRepository extends JpaRepository<ExchangeRateParam , ExchangeRateParamId> {



//    @Query("SELECT m FROM ExchangeRateParam m WHERE m.id.bankCode = :bankCode AND m.id.rateProtocol = :rateProtocol")
//    List<ExchangeRateParam> findByBankCodeAndRateProtocole(@Param("bankCode") String bankCode, @Param("rateProtocol") String rateProtocol);

    @Query("SELECT m FROM ExchangeRateParam m WHERE m.id.bankCode = :bankCode AND m.id.rateProtocol = :rateProtocol")
    List<ExchangeRateParam> findByBankCodeAndRateProtocol(@Param("bankCode") String bankCode, @Param("rateProtocol") String rateProtocol);

}
