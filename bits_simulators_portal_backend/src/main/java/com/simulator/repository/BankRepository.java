package com.simulator.repository;

import com.simulator.entities.Bank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BankRepository extends JpaRepository<Bank, String> {
    @Override
    Optional<Bank> findById(String s);

}