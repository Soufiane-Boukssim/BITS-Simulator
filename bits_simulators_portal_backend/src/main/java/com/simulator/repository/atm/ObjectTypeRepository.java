package com.simulator.repository.atm;

import com.simulator.entities.atm.ObjectType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ObjectTypeRepository extends JpaRepository<ObjectType, Integer> {
}
