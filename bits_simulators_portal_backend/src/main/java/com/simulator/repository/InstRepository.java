package com.simulator.repository;

import com.simulator.entities.InstParam;
import com.simulator.entities.InstParamId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InstRepository extends JpaRepository<InstParam, InstParamId> {


    List<InstParam> findById_BankCodeAndId_InstProtocol(String bankCode,String commProtocol);

}
