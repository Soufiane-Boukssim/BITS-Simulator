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


    @Query("SELECT m FROM FunctDef m WHERE m.id.bankCode = :bankCode AND m.id.fctProtocol = :fctProtocol")
    List<FunctDef> findByBankCodeAndFctProtocole(@Param("bankCode") String bankCode, @Param("fctProtocol") Character fctProtocol);
}
