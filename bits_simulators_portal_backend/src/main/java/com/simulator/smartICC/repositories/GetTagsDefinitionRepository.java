package com.simulator.smartICC.repositories;

import com.simulator.smartICC.models.GetTagsDefinition;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GetTagsDefinitionRepository extends JpaRepository<GetTagsDefinition, String> {
    List<GetTagsDefinition> findByCodeProfile(String codeProfile);

}
