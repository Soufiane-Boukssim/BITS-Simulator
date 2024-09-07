package com.simulator.repository;

import com.simulator.entities.CardProfile;
import com.simulator.entities.CardProfileId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CardRepository  extends JpaRepository <CardProfile, CardProfileId> {





    @Query("SELECT m FROM CardProfile m WHERE m.id.bankCode = :bankCode AND m.id.cardProtocol = :cardProtocol")
    List<CardProfile> findByBankCodeAndCardProtocol(@Param("bankCode") String bankCode, @Param("cardProtocol") Character cardProtocol);

}
