package com.simulator.repository.pos;

import com.simulator.entities.pos.PosMtiDef;
import com.simulator.entities.pos.PosMtiDefId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface PosMtiRepository extends JpaRepository<PosMtiDef, PosMtiDefId> {
    Optional<PosMtiDef> findById(PosMtiDefId id);

    @Query("SELECT m FROM PosMtiDef m WHERE m.id.bankCode = :bankCode AND m.id.mtiProtocol = :mtiProtocole")
    List<PosMtiDef> findByBankCodeAndMtiProtocole(@Param("bankCode") String bankCode, @Param("mtiProtocole") String mtiProtocole);
}





