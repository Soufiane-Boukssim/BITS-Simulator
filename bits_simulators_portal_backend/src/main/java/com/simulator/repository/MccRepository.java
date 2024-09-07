package com.simulator.repository;

import com.simulator.entities.MccDef;
import com.simulator.entities.MccDefId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MccRepository extends JpaRepository<MccDef, MccDefId> {

    Optional<MccDef> findById(MccDefId id);


    @Query("SELECT m FROM MccDef m WHERE m.id.bankCode = :bankCode AND m.id.mccProtocol = :mccProtocol")
    List<MccDef> findByBankCodeAndMccProtocol(@Param("bankCode") String bankCode, @Param("mccProtocol") String mccProtocol);

    List<MccDef> findByIdMccCode(String mccCode);

}
