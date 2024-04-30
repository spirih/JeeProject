package com.example.testspringmaven.repository;

import com.example.testspringmaven.persistant.GroupandactivitiesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupAndActivitiesRepository extends JpaRepository<GroupandactivitiesEntity, Long> {
}
