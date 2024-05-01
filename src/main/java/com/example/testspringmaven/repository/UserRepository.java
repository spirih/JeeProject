package com.example.testspringmaven.repository;

import com.example.testspringmaven.persistant.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.ArrayList;

public interface UserRepository extends JpaRepository<UsersEntity, Long> {
   ArrayList<UsersEntity>findByNickname(String name);
   UsersEntity getUsersEntityByNickname(String nickname);
}
