package com.simulator.repository;

import com.simulator.entities.CaseSetInfo;
import com.simulator.entities.CasesSetInfoId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CaseSetRepository extends JpaRepository<CaseSetInfo, CasesSetInfoId> {




    @Query("SELECT m FROM CaseSetInfo m WHERE m.id.bankCode = :bankCode AND m.id.caseSetProtocole = :caseSetProtocole")
    List<CaseSetInfo> findByBankCodeAndCaseSetProtocole(@Param("bankCode") String bankCode, @Param("caseSetProtocole") String caseSetProtocole);

}
