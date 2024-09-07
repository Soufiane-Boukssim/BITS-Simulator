package com.simulator.repository.pos;

import com.simulator.entities.pos.PosProcessingDef;
import com.simulator.entities.pos.PosProcessingDefId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository


public interface PosProcessingRepository extends JpaRepository<PosProcessingDef, PosProcessingDefId> {



   @Query("SELECT m FROM PosProcessingDef m WHERE m.id.bankCode = :bankCode AND m.id.procProtocol = :procProtocol")
   List<PosProcessingDef> findByBankCodeAndProcProtocol(@Param("bankCode") String bankCode, @Param("procProtocol") String procProtocol);

}
