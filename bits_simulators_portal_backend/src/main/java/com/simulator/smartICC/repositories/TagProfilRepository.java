
package com.simulator.smartICC.repositories;

import com.simulator.smartICC.models.TagProfil;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagProfilRepository extends JpaRepository<TagProfil, Long> {
    // Ajoutez des méthodes personnalisées si nécessaire
}
