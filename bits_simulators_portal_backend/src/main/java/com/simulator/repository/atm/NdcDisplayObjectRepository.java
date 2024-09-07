package com.simulator.repository.atm;

import com.simulator.entities.atm.NdcDisplayObject;
import com.simulator.entities.atm.NdcDisplayObjectId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NdcDisplayObjectRepository extends JpaRepository<NdcDisplayObject, NdcDisplayObjectId> {
}