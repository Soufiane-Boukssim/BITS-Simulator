package com.simulator.repository;

import com.simulator.entities.MerchantParam;
import com.simulator.entities.MerchantParamId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MerchantRepository extends JpaRepository <MerchantParam  , MerchantParamId>{


    @Query("SELECT m FROM MerchantParam m WHERE m.id.bankCode = :bankCode AND m.id.merProtocol = :merProtocol")
    List<MerchantParam> findByBankCodeAndMerProtocole(@Param("bankCode") String bankCode, @Param("merProtocol") String merProtocol);



}
