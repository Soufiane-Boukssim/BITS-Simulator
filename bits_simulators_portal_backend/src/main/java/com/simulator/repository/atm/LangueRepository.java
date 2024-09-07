package com.simulator.repository.atm;

import com.simulator.entities.atm.Langue;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LangueRepository extends JpaRepository<Langue, String> {
}
