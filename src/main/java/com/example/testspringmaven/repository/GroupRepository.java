package com.example.testspringmaven.repository;

import com.example.testspringmaven.persistant.GroupactivitiesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.ArrayList;

public interface GroupRepository extends JpaRepository<GroupactivitiesEntity, Long> {
    /**
    void addActivityToGroup(int idGroup, int idActivity);**/

    @Query("SELECT b from GroupactivitiesEntity b")
    ArrayList<GroupactivitiesEntity> getAll();
    /**

    GroupactivitiesEntity createGroupe(String group);**/
    /**
    void insertGroupactivities(String group);**/
    GroupactivitiesEntity findByName(String name);
    GroupactivitiesEntity findById(int id);
    ArrayList<GroupactivitiesEntity> getGroupactivitiesEntitiesByUser(String str);
}
