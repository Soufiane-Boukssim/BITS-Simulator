package com.simulator.repository.pos;

import com.simulator.entities.pos.PosCardProfile;
import com.simulator.entities.pos.PosCardProfileId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PosCardRepository extends JpaRepository<PosCardProfile, PosCardProfileId> {

    @Query("SELECT m FROM PosCardProfile m WHERE m.id.bankCode = :bankCode AND m.id.cardProtocol = :cardProtocol")
    List<PosCardProfile> findByBankCodeAndCardProtocol(@Param("bankCode") String bankCode, @Param("cardProtocol") String cardProtocol);
}
