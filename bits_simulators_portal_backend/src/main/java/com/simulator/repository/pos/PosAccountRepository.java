package com.simulator.repository.pos;


import com.simulator.entities.pos.PosAccountDef;
import com.simulator.entities.pos.PosAccountDefId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PosAccountRepository extends JpaRepository<PosAccountDef, PosAccountDefId> {



    @Query("SELECT m FROM PosAccountDef m WHERE m.id.bankCode = :bankCode AND m.id.accProtocol = :accProtocol")
    List<PosAccountDef> findByBankCodeAndAccProtocol(@Param("bankCode") String bankCode, @Param("accProtocol") String accProtocol);


    /*
    List<PosAccountDef> findById_BankCodeAndId_AccProtocol(String bankCode, Character accProtocol);
*/

}

