package com.simulator.repository;

import com.simulator.entities.ProcessingDef;
import com.simulator.entities.ProcessingDefId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository


public interface ProcessingRepository extends JpaRepository<ProcessingDef , ProcessingDefId> {



    @Query("SELECT m FROM ProcessingDef m WHERE m.id.bankCode = :bankCode AND m.id.procProtocol = :procProtocol")
    List<ProcessingDef> findByBankCodeAndProcProtocol(@Param("bankCode") String bankCode, @Param("procProtocol") Character procProtocol);

}
