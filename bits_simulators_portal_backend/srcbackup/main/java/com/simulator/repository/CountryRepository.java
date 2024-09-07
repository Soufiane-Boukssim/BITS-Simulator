package com.simulator.repository;

import com.simulator.entities.CountryParam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepository extends JpaRepository<CountryParam , String> {



    /*@Query("SELECT m FROM AccountDef m WHERE m.id.bankCode = :bankCode AND m.id.accProtocol = :accProtocol")
    List<AccountDef> findByBankCodeAndAccProtocol(@Param("bankCode") String bankCode, @Param("accProtocol") Character accProtocol);
*/
}
