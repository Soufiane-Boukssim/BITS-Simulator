package com.simulator.repository.atm;

import com.simulator.entities.atm.NdcDisplayAnimed;
import com.simulator.entities.atm.NdcDisplayAnimedId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NdcDisplayAnimedRepository extends JpaRepository<NdcDisplayAnimed, NdcDisplayAnimedId> {
}