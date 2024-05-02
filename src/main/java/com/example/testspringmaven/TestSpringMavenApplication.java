package com.example.testspringmaven;

import com.example.testspringmaven.persistant.UsersEntity;
import com.example.testspringmaven.repository.GroupRepository;
import com.example.testspringmaven.repository.UserRepository;
import com.example.testspringmaven.utilitary.Hasher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.NoSuchAlgorithmException;

@SpringBootApplication
@RestController
public class TestSpringMavenApplication {
    @Autowired
    GroupRepository groupRepository;
    @Autowired
    UserRepository userRepository;
    public static void main(String[] args) {
        SpringApplication.run(TestSpringMavenApplication.class, args);
    }

    @PostMapping("/hello")
    @GetMapping("/hello")
    public String sayHello(@RequestParam(value = "myName", defaultValue = "World") String name) throws NoSuchAlgorithmException {
        String password = Hasher.hashing("1234");
        return String.format("Hello %s!", name);
    }
}

