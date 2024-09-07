package com.simulator.repository.pos;

import com.simulator.entities.pos.PosSubfldDefinition;
import com.simulator.entities.pos.PosSubfldDefinitionId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PosSubFldDefRepository extends JpaRepository<PosSubfldDefinition, PosSubfldDefinitionId> {

    @Override
    Optional<PosSubfldDefinition> findById(PosSubfldDefinitionId id);



    @Query("SELECT sd FROM PosSubfldDefinition sd WHERE sd.subfldDefinitionId.subfldProtocole = :protocol AND sd.subfldDefinitionId.bankCode = :bankCode")
    List<PosSubfldDefinition> findByProtocolAndBankCode(@Param("protocol") String protocol, @Param("bankCode") String bankCode);

}
