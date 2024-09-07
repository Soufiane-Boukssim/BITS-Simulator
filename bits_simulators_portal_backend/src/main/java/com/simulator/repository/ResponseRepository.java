package com.simulator.repository;

import com.simulator.entities.ResponseDef;
import com.simulator.entities.ResponseDefId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ResponseRepository  extends JpaRepository<ResponseDef , ResponseDefId> {




    List<ResponseDef> findById_BankCodeAndId_RespProtocol(@Param("bankCode") String bankCode, @Param("respProtocole") String rsepProtocole);

    List<ResponseDef> findByIdRespCode(String respCode);



}
