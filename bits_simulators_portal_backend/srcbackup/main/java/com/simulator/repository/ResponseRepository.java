package com.simulator.repository;

import com.simulator.entities.ResponseDef;
import com.simulator.entities.ResponseDefId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ResponseRepository  extends JpaRepository<ResponseDef , ResponseDefId> {



    @Query("SELECT m FROM PosResponseDef m WHERE m.id.bankCode = :bankCode AND m.id.respProtocol = :respProtocole")
    List<ResponseDef> findByBankCodeAndRespProtocol(@Param("bankCode") String bankCode, @Param("respProtocole") Character rsepProtocole);


}
