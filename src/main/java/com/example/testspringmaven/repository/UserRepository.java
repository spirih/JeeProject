package com.example.testspringmaven.repository;

import com.example.testspringmaven.persistant.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UsersEntity, Long> {
    boolean inscrire(String nickname, String password);
}
