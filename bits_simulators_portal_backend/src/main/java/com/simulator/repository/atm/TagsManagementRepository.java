package com.simulator.repository.atm;


import com.simulator.entities.atm.TagsManagement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagsManagementRepository extends JpaRepository<TagsManagement, String> {
}
