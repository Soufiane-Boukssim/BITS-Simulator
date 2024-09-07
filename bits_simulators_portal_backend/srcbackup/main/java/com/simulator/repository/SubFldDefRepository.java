package com.simulator.repository;

import com.simulator.entities.SubfldDefinition;
import com.simulator.entities.SubfldDefinitionId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SubFldDefRepository extends JpaRepository<SubfldDefinition, SubfldDefinitionId> {

    @Override
    Optional<SubfldDefinition> findById(SubfldDefinitionId id);

}
