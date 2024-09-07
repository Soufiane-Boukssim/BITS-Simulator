package com.simulator.repository;

import com.simulator.entities.IccProfile;
import com.simulator.entities.IccProfileId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IccRepository extends JpaRepository<IccProfile, IccProfileId> {
    Optional<IccProfile> findById(IccProfileId id);


    List<IccProfile> findById_IcPrfAndId_IccProtocolAndId_BankCode(String icPrf, String protocol, String bankCode);

    /*  @Query("SELECT m FROM IccProfile m WHERE m.id.bankCode = :bankCode AND m.id.iccProtocol = :iccProtocole")
    List<IccProfile> findByBankCodeAndIccProtocole(@Param("bankCode") String bankCode, @Param("iccProtocole") String iccProtocole);*/
}
