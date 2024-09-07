package com.simulator.repository;

import com.simulator.entities.TerminalParam;
import com.simulator.entities.TerminalParamId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TerminalRepository extends JpaRepository<TerminalParam , TerminalParamId> {


    @Query("SELECT m FROM TerminalParam m WHERE m.id.bankCode = :bankCode AND m.id.terProtocol = :terProtocol")
    List<TerminalParam> findByBankCodeAndTerProtocol(@Param("bankCode") String bankCode, @Param("terProtocol") String terProtocol);





}
