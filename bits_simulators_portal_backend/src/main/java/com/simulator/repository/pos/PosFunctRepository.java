package com.simulator.repository.pos;

import com.simulator.entities.pos.PosFunctDef;
import com.simulator.entities.pos.PosFunctDefId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PosFunctRepository extends JpaRepository<PosFunctDef, PosFunctDefId> {


    @Query("SELECT m FROM PosFunctDef m WHERE m.id.bankCode = :bankCode AND m.id.fctProtocol = :fctProtocol")
    List<PosFunctDef> findByBankCodeAndFctProtocole(@Param("bankCode") String bankCode, @Param("fctProtocol") String fctProtocol);

    List<PosFunctDef> findByIdFctCode(String fctCode);

}
