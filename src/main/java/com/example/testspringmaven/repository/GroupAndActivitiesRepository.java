package com.example.testspringmaven.repository;

import com.example.testspringmaven.persistant.GroupandactivitiesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;

public interface GroupAndActivitiesRepository extends JpaRepository<GroupandactivitiesEntity, Long> {
    ArrayList<GroupandactivitiesEntity> getGroupandactivitiesEntitiesByIdActivity(int idActivity);
    ArrayList<GroupandactivitiesEntity> getGroupandactivitiesEntitiesByIdGroup(int id);
    GroupandactivitiesEntity getGroupandactivitiesEntityById(int id);
}
