package com.simulator.repository;

import com.simulator.entities.UserHabilitationParam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
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

}