package com.simulator.repository;

import com.simulator.entities.Protocole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProtocoleRepository extends JpaRepository<Protocole, String> {

    @Query(value = "select wording_protocole from Protocole where id_protocole = :id",nativeQuery = true)
    String getWordingById(@Param("id") String id);


}
