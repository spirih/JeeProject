package com.example.testspringmaven.repository;

import com.example.testspringmaven.persistant.ActivitiesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.ArrayList;

public interface ActivitiesRepository extends JpaRepository<ActivitiesEntity, Long> {
    /**
    ArrayList<ActivitiesEntity> findByKey(String name);
    void noteActivity(int idActivity, int value);**/
    @Query("select p from ActivitiesEntity p")
    ArrayList<ActivitiesEntity> findAll();
    ArrayList<ActivitiesEntity> findAllByName(String name);


}
