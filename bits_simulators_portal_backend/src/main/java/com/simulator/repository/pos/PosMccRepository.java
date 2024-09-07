package com.simulator.repository.pos;

import com.simulator.entities.pos.PosMccDef;
import com.simulator.entities.pos.PosMccDefId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PosMccRepository extends JpaRepository<PosMccDef, PosMccDefId> {

    Optional<PosMccDef> findById(PosMccDefId id);


    @Query("SELECT m FROM PosMccDef m WHERE m.id.bankCode = :bankCode AND m.id.mccProtocol = :mccProtocol")
    List<PosMccDef> findByBankCodeAndMccProtocol(@Param("bankCode") String bankCode, @Param("mccProtocol") String mccProtocol);


    List<PosMccDef> findByIdMccCode(String mccCode);


}
