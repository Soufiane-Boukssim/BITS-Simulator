package com.simulator.repository.atm;

import com.simulator.entities.atm.NdcDisplayLayout;
import com.simulator.entities.atm.NdcDisplayLayoutId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NdcDisplayLayoutRepository extends JpaRepository<NdcDisplayLayout, NdcDisplayLayoutId> {
}