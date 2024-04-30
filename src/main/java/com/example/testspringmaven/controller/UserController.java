package com.example.testspringmaven.controller;

import com.example.testspringmaven.repository.UserRepository;
import com.example.testspringmaven.utilitary.Hasher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.NoSuchAlgorithmException;

@Controller
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping(path = "/inscription")
    public boolean inscription(String nickname, String password) throws NoSuchAlgorithmException {
        password = Hasher.hashing(password);
        boolean succes = true;//userRepository.inscrire(nickname,password);
        return succes;
    }
    @GetMapping(path = "/connection")
    public boolean connection(String nickname, String password) throws NoSuchAlgorithmException {
        password = Hasher.hashing(password);
        boolean succes = true;//userRepository.connect(nickname,password);
        return succes;
    }
    @GetMapping(path = "/deconnection")
    public void deconnection() throws NoSuchAlgorithmException {
       // userRepository.deconnect();
    }

}
