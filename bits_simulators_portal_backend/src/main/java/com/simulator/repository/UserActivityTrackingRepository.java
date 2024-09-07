package com.simulator.repository;

import com.simulator.entities.UserActivityTracking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserActivityTrackingRepository extends JpaRepository<UserActivityTracking, Integer> {

    @Query(value = "select * from USER_ACTIVITY_TRACKING where USER_CODE =:uCode",nativeQuery = true)
    List<UserActivityTracking> findByUserCode(@Param("uCode") String UserCode);
}
