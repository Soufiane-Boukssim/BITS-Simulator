package com.simulator.smartICC.repositories;

import com.simulator.smartICC.models.EmvTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmvTagRepository extends JpaRepository<EmvTag, String> {

    Optional<EmvTag> findByTag(String tag);
}
