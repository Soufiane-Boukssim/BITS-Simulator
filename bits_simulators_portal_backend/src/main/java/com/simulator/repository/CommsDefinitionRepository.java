package com.simulator.repository;

import com.simulator.entities.CommsDefinition;
import com.simulator.entities.CommsDefinitionId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommsDefinitionRepository extends JpaRepository<CommsDefinition, CommsDefinitionId> {
    @Query("SELECT m FROM CommsDefinition m WHERE m.id.bankCode = :bankCode")
    List<CommsDefinition> findByBankCodeAndCommProtocole(@Param("bankCode") String bankCode);
}
