package com.simulator.repository;

import com.simulator.entities.MtiDef;
import com.simulator.entities.MtiDefId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface MtiRepository  extends JpaRepository<MtiDef,MtiDefId> {
    Optional<MtiDef> findById(MtiDefId id);

    @Query("SELECT m FROM MtiDef m WHERE m.id.bankCode = :bankCode AND m.id.mtiProtocol = :mtiProtocole")
    List<MtiDef> findByBankCodeAndMtiProtocole(@Param("bankCode") String bankCode, @Param("mtiProtocole") String mtiProtocole);
}





