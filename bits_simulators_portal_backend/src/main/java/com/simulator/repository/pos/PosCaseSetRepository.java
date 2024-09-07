package com.simulator.repository.pos;

import com.simulator.entities.pos.PosCaseSetInfo;
import com.simulator.entities.pos.PosCasesSetInfoId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PosCaseSetRepository extends JpaRepository<PosCaseSetInfo, PosCasesSetInfoId> {



    @Query("SELECT m FROM PosCaseSetInfo m WHERE m.id.bankCode = :bankCode AND m.id.caseSetProtocole = :caseSetProtocole")
    List<PosCaseSetInfo> findByBankCodeAndCaseSetProtocole(@Param("bankCode") String bankCode, @Param("caseSetProtocole") String caseSetProtocole);

}
