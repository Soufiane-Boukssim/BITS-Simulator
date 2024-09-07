package com.simulator.repository.pos;

import com.simulator.entities.pos.PosCommsParam;
import com.simulator.entities.pos.PosCommsParamId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PosCommsRepository extends JpaRepository<PosCommsParam, PosCommsParamId> {



    @Query("SELECT m FROM PosCommsParam m WHERE m.id.bankCode = :bankCode AND m.id.commProtocol = :commProtocol")
    List<PosCommsParam> findByBankCodeAndCommProtocole(@Param("bankCode") String bankCode, @Param("commProtocol") String commProtocol);

}
