package com.simulator.repository;

import com.simulator.entities.FunctDef;
import com.simulator.entities.FunctDefId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FunctRepository extends JpaRepository<FunctDef , FunctDefId> {


    @Query("SELECT fd FROM FunctDef fd WHERE fd.id.bankCode = :bankCode AND fd.id.fctProtocol = :fctProtocol")
    List<FunctDef> findByBankCodeAndFctProtocol(@Param("bankCode") String bankCode, @Param("fctProtocol") String fctProtocol);

    List<FunctDef> findByIdFctCode(String fctCode);
}
