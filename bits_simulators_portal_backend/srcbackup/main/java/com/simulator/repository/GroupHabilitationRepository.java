package com.simulator.repository;

import com.simulator.entities.GroupHabilitation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroupHabilitationRepository extends JpaRepository<GroupHabilitation, String> {
    List<GroupHabilitation> findByGroupName(String s);
}