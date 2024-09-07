package com.simulator.repository.pos;

import com.simulator.entities.pos.PosResponseDef;
import com.simulator.entities.pos.PosResponseDefId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PosResponseRepository extends JpaRepository<PosResponseDef, PosResponseDefId> {




    List<PosResponseDef> findById_BankCodeAndId_RespProtocol(String bankCode, String rsepProtocole);

    List<PosResponseDef> findByIdRespCode(String respCode);
}
