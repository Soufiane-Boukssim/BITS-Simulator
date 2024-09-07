package com.simulator.repository.pos;

import com.simulator.entities.pos.PosIccProfile;
import com.simulator.entities.pos.PosIccProfileId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PosIccRepository extends JpaRepository<PosIccProfile, PosIccProfileId> {
    Optional<PosIccProfile> findById(PosIccProfileId id);


    List<PosIccProfile> findById_IcPrfAndId_IccProtocolAndId_BankCode(String icPrf, String protocol, String bankCode);

    /*  @Query("SELECT m FROM PosIccProfile m WHERE m.id.bankCode = :bankCode AND m.id.iccProtocol = :iccProtocole")
    List<PosIccProfile> findByBankCodeAndIccProtocole(@Param("bankCode") String bankCode, @Param("iccProtocole") String iccProtocole);*/
}
