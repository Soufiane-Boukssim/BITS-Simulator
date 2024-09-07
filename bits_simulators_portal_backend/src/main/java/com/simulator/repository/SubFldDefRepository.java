package com.simulator.repository;

import com.simulator.entities.SubfldDefinition;
import com.simulator.entities.SubfldDefinitionId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SubFldDefRepository extends JpaRepository<SubfldDefinition, SubfldDefinitionId> {

    @Override
    Optional<SubfldDefinition> findById(SubfldDefinitionId id);


    @Query("SELECT sd FROM SubfldDefinition sd WHERE sd.subfldDefinitionId.subfldProtocole = :protocol AND sd.subfldDefinitionId.bankCode = :bankCode")
    List<SubfldDefinition> findByProtocolAndBankCode(@Param("protocol") String protocol, @Param("bankCode") String bankCode);

}
