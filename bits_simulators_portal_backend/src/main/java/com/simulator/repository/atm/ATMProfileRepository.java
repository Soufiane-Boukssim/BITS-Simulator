package com.simulator.repository.atm;

import com.simulator.entities.atm.ATMProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ATMProfileRepository extends JpaRepository<ATMProfile, String> {
}
