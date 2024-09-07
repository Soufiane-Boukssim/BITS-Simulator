package com.simulator.smartICC.repositories;

import com.simulator.smartICC.models.EMVProfile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EMVProfileRepository extends JpaRepository<EMVProfile, String> {
    Optional<EMVProfile> findByCodeProfile(String codeProfile);

    void deleteByCodeProfile(String codeProfile);
    Optional<EMVProfile> findByActiveProfile(String activeProfile);

}
