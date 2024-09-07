package com.simulator.repository;

import com.simulator.entities.CurrencyParam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CurrencyRepository extends JpaRepository<CurrencyParam , String > {



    /*@Query("SELECT m FROM MtiDef m WHERE m.id.bankCode = :bankCode AND m.id.mtiProtocol = :mtiProtocole")
    List<MtiDef> findByBankCodeAndMtiProtocole(@Param("bankCode") String bankCode, @Param("mtiProtocole") String mtiProtocole);
*/

    Optional<CurrencyParam> findById(String s);
}
