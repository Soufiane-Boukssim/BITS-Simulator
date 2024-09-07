package com.simulator.repository;

import com.simulator.entities.AccountDef;
import com.simulator.entities.AccountDefId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<AccountDef , AccountDefId> {



    @Query("SELECT m FROM AccountDef m WHERE m.id.bankCode = :bankCode AND m.id.accProtocol = :accProtocol")
    List<AccountDef> findByBankCodeAndAccProtocol(@Param("bankCode") String bankCode, @Param("accProtocol") Character accProtocol);

}

