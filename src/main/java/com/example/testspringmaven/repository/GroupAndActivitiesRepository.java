package com.example.testspringmaven.repository;

import com.example.testspringmaven.persistant.GroupandactivitiesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

public interface GroupAndActivitiesRepository extends JpaRepository<GroupandactivitiesEntity, Long> {
    ArrayList<GroupandactivitiesEntity> getGroupandactivitiesEntitiesByIdActivity(int idActivity);
    ArrayList<GroupandactivitiesEntity> getGroupandactivitiesEntitiesByIdGroup(int id);
    GroupandactivitiesEntity getGroupandactivitiesEntityById(int id);
    @Modifying
    @Transactional
    @Query("update GroupandactivitiesEntity u set u.note = :note where u.id = :id")
    void setNote(@Param("note") int note,@Param("id") int id);
}
