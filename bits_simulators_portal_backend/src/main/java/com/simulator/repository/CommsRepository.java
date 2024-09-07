package com.simulator.repository;

import com.simulator.entities.CommsParam;
import com.simulator.entities.CommsParamId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommsRepository extends JpaRepository<CommsParam , CommsParamId> {



    @Query("SELECT m FROM CommsParam m WHERE m.id.bankCode = :bankCode AND m.id.commProtocol = :commProtocol")
    List<CommsParam> findByBankCodeAndCommProtocole(@Param("bankCode") String bankCode, @Param("commProtocol") String commProtocol);

}
