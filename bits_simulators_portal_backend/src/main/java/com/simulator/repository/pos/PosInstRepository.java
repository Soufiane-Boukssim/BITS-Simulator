package com.simulator.repository.pos;

import com.simulator.entities.pos.PosInstParam;
import com.simulator.entities.pos.PosInstParamId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PosInstRepository extends JpaRepository<PosInstParam, PosInstParamId> {


    List<PosInstParam> findById_BankCodeAndId_InstProtocol(String bankCode,String commProtocol);

}
