package com.simulator.atm.repositories.repoatm;

import com.simulator.entities.TerminalLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TerminalLogRepository extends JpaRepository<TerminalLog, String> {

    List<TerminalLog> findById_BankCode(String bankCode);

    @Query("SELECT t FROM TerminalLog t WHERE t.id.bankCode = :bankCode AND t.logEDate BETWEEN :dateFrom AND :dateTo")
    List<TerminalLog> findByBankCodeAndDateRange(
            @Param("bankCode") String bankCode,
            @Param("dateFrom") String dateFrom,
            @Param("dateTo") String dateTo
    );

    @Query("SELECT t FROM TerminalLog t WHERE t.id.bankCode = :bankCode")
    List<TerminalLog> findByBankCode(@Param("bankCode") String bankCode);
}
