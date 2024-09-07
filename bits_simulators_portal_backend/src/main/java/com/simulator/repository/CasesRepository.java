package com.simulator.repository;

import com.simulator.entities.CasesDefinition;
import com.simulator.entities.CasesDefinitionId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CasesRepository extends JpaRepository<CasesDefinition , CasesDefinitionId> {
    @Query("SELECT DISTINCT (m.id.caseNo), m.id.bankCode, m.id.caseProtocole, m.caseDesc FROM CasesDefinition m WHERE m.id.bankCode = :bankCode AND m.id.caseProtocole = :caseProtocole")
    List<Object[]> findByBankCodeAndCaseProtocole(@Param("bankCode") String bankCode, @Param("caseProtocole") String caseProtocole);

    List<CasesDefinition> findById_CaseNoAndId_BankCodeAndId_CaseProtocole(
            String caseNo,
            String bankCode,
            String caseProtocole
    );

    @Query("SELECT m FROM CasesDefinition m WHERE m.id.caseNo in :caseNo AND m.id.bankCode in :bankCode AND m.id.caseProtocole in :caseProtocole")
    List<CasesDefinition> findAllById_CaseNoAndId_BankCodeAndId_CaseProtocoleIn(
            @Param("caseNo") List<String> caseNo,
            @Param("bankCode") List<String> bankCode,
            @Param("caseProtocole") List<String> caseProtocole
    );

    @Override
    Optional<CasesDefinition> findById(CasesDefinitionId casesDefinitionId);
}


