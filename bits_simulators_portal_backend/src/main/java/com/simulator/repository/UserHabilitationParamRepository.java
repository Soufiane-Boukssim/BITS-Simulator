package com.simulator.repository;

import com.simulator.entities.UserHabilitationParam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface UserHabilitationParamRepository extends JpaRepository<UserHabilitationParam, Integer> {
    List<UserHabilitationParam> findByUserCode(String s);

    @Query("select max(u.userHabilitationId) from UserHabilitationParam u")
    int maxValue();

    @Transactional
    @Modifying
    void deleteByhabilitationCode(String habilitationCodes);


    @Query(value = "select protocol_code from user_habilitation_param where user_code = :userC and habilitation_code = 'ISO'",nativeQuery = true)
    List<String> getUserIsoProtocols(@Param("userC") String userCode);
}