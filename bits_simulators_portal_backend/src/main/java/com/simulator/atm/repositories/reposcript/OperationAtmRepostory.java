package com.simulator.atm.repositories.reposcript;

import com.simulator.atm.model.OperationAtm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OperationAtmRepostory extends JpaRepository<OperationAtm, Long> {
    OperationAtm findByLibelle(String libelle);

    @Query("SELECT s FROM OperationAtm s WHERE s.bankCode = :bankCode")
    List<OperationAtm> findAllByBankCode(@Param("bankCode") String bankCode);
}
