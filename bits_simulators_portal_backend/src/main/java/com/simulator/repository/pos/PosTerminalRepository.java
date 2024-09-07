package com.simulator.repository.pos;

import com.simulator.entities.pos.PosTerminalParam;
import com.simulator.entities.pos.PosTerminalParamId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PosTerminalRepository extends JpaRepository<PosTerminalParam, PosTerminalParamId> {


    @Query("SELECT m FROM PosTerminalParam m WHERE m.id.bankCode = :bankCode AND m.id.terProtocol = :terProtocol")
    List<PosTerminalParam> findByBankCodeAndTerProtocol(@Param("bankCode") String bankCode, @Param("terProtocol") String terProtocol);





}
