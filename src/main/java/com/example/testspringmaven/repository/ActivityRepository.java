package com.example.testspringmaven.repository;

import com.example.testspringmaven.persistant.ActivitiesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;

public interface ActivityRepository extends JpaRepository<ActivitiesEntity, Long> {
    ArrayList<ActivitiesEntity> findByKey(String name);
    void noteActivity(int idActivity, int value);
}
