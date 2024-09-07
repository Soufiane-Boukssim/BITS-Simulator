package com.simulator.repository;

import com.simulator.entities.GroupHabilitation;
import com.simulator.entities.GroupHabilitationId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface GroupHabilitationRepository extends JpaRepository<GroupHabilitation, GroupHabilitationId> {
    List<GroupHabilitation> findByGroupId_GroupCode(String s);
    List<GroupHabilitation> findByGroupName(String s);

    @Transactional
    @Modifying
    @Query(value = "update GROUP_HABILITATION set group_name =:groupName , menu_code =:menuCode where group_code =:groupCode",nativeQuery = true)
    int updateGroup(@Param(value = "groupCode") String id, @Param(value = "groupName") String groupName, @Param(value = "menuCode") String menuCode);


    @Transactional
    @Modifying
    @Query(value = "delete from GROUP_HABILITATION where group_code =:groupCode",nativeQuery = true)
    void deleteGroup(@Param(value = "groupCode") String id);
}