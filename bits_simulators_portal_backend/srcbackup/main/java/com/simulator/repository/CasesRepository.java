package com.simulator.repository;

import com.simulator.entities.CasesDefinition;
import com.simulator.entities.CasesDefinitionId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CasesRepository extends JpaRepository<CasesDefinition , CasesDefinitionId> {


    @Query("SELECT m FROM CasesDefinition m WHERE m.id.bankCode = :bankCode AND m.id.caseProtocole = :caseProtocole")
    List<CasesDefinition> findByBankCodeAndCaseProtocole(@Param("bankCode") String bankCode, @Param("caseProtocole") Character caseProtocole);

}
