package com.simulator.repository;

import com.simulator.entities.UserManagement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserManagementRepository extends JpaRepository<UserManagement, String> {

    Optional<UserManagement> findByUserCodeAndStatus(String s,String status);
    List<UserManagement> findByStatus(String status);
    List<UserManagement> findByUserCode(String userCode);


}