package com.simulator.repository;

import com.simulator.entities.LogNDCAID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface LogNDCAIDRepository extends JpaRepository<LogNDCAID, String> {
    List<LogNDCAID> findById_BankCode(String bankCode);

}

