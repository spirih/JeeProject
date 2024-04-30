package com.example.testspringmaven.repository;

import com.example.testspringmaven.persistant.GroupactivitiesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;

public interface GroupRepository extends JpaRepository<GroupactivitiesEntity, Long> {
    void addActivityToGroup(int idGroup, int idActivity);

    ArrayList<GroupactivitiesEntity> getAll();

    GroupactivitiesEntity createGroupe(String group);
}
