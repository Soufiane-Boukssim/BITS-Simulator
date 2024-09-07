package com.simulator.smartICC.repositories;

import com.simulator.smartICC.models.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tag, Long> {
    // Ajoutez des méthodes personnalisées si nécessaire
}