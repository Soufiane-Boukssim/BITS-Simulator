package com.simulator.repository.pos;

import com.simulator.entities.pos.PosFieldsDefinition;
import com.simulator.entities.pos.PosFieldsDefinitionId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PosFieldsRepository extends JpaRepository<PosFieldsDefinition, PosFieldsDefinitionId> {




   /* @Query("SELECT m FROM PosFieldsDefinition m WHERE m.id.bankCode = :bankCode AND m.id.fieldProtocole = :fieldProtocole")
    List<PosFieldsDefinition> findByBankCodeAndFieldsProtocole(@Param("bankCode") String bankCode, @Param("fieldProtocole") Character fieldProtocole);*/
}
