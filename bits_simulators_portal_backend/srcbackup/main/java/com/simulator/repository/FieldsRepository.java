package com.simulator.repository;

import com.simulator.entities.FieldsDefinition;
import com.simulator.entities.FieldsDefinitionId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FieldsRepository extends JpaRepository<FieldsDefinition , FieldsDefinitionId> {




    @Query("SELECT m FROM FieldsDefinition m WHERE m.id.bankCode = :bankCode AND m.id.fieldProtocole = :fieldProtocole")
    List<FieldsDefinition> findByBankCodeAndFieldsProtocole(@Param("bankCode") String bankCode, @Param("fieldProtocole") Character fieldProtocole);
}
