package com.simulator.atm.repositories.reposcript;

import com.simulator.atm.model.AtmIccProfil;
import com.simulator.atm.model.AtmIccProfilId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AtmIccRepository extends JpaRepository<AtmIccProfil, AtmIccProfilId> {
    Optional<AtmIccProfil> findById(AtmIccProfilId id);


    List<AtmIccProfil> findById_IcPrfAndId_IccProtocolAndId_BankCode(String icPrf, String protocol, String bankCode);
}
