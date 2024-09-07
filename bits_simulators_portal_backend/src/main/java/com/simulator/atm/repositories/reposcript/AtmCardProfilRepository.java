package com.simulator.atm.repositories.reposcript;

import com.simulator.atm.model.AtmCardProfil;
import com.simulator.atm.model.AtmCardProfilId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AtmCardProfilRepository extends JpaRepository<AtmCardProfil, AtmCardProfilId> {
    @Query("SELECT m FROM AtmCardProfil m WHERE m.id.bankCode = :bankCode")
    List<AtmCardProfil> findByBankCodeAndCardProtocol(@Param("bankCode") String bankCode);

//    AtmCardProfil findFirstByCardNo(String cardProfileCardNo);

    @Query("SELECT a FROM AtmCardProfil a WHERE a.id.cardNo = :cardNo")
    AtmCardProfil findFirstByCardNo(@Param("cardNo") String cardNo);

}
