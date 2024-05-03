package com.example.testspringmaven.repository;

import com.example.testspringmaven.persistant.ActivitiesEntity;
;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.ArrayList;

public interface ActivitiesRepository extends JpaRepository<ActivitiesEntity, Long> {
    /**
    ArrayList<ActivitiesEntity> findByKey(String name);
    void noteActivity(int idActivity, int value);**/
    @Query("select p from ActivitiesEntity p")
    ArrayList<ActivitiesEntity> findAll();
    @Query("select p from ActivitiesEntity p")
    Page<ActivitiesEntity> findAllPage(Pageable pageable);
    ArrayList<ActivitiesEntity> findAllByName(String name);
    @Query("select p from ActivitiesEntity p where p.name = :name")
    Page<ActivitiesEntity> findAllByNamePageable(String name, Pageable pageable);
    @Query("select p from ActivitiesEntity p where p.name like %:name%")
    Page<ActivitiesEntity> findAllLikeNamePageable(String name, Pageable pageable);
    @Query("select p from ActivitiesEntity p where p.description like %:name%")
    Page<ActivitiesEntity> findAllLikeDescriptionPageable(String name, Pageable pageable);
    @Query("select p from ActivitiesEntity p where p.pathologie like %:name%")
    Page<ActivitiesEntity> findAllLikePathologiePageable(String name, Pageable pageable);

    ActivitiesEntity findById(int id);

}
