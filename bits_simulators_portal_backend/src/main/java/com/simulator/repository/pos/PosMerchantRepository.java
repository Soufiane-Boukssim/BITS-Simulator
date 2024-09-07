package com.simulator.repository.pos;

import com.simulator.entities.pos.PosMerchantParam;
import com.simulator.entities.pos.PosMerchantParamId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PosMerchantRepository extends JpaRepository <PosMerchantParam, PosMerchantParamId>{


    @Query("SELECT m FROM PosMerchantParam m WHERE m.id.bankCode = :bankCode AND m.id.merProtocol = :merProtocol")
    List<PosMerchantParam> findByBankCodeAndMerProtocole(@Param("bankCode") String bankCode, @Param("merProtocol") String merProtocol);



}
