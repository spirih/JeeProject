package com.example.testspringmaven.controller;

import com.example.testspringmaven.persistant.UsersEntity;
import com.example.testspringmaven.repository.UserRepository;
import com.example.testspringmaven.utilitary.Hasher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.NoSuchAlgorithmException;
import java.util.Objects;

@Controller
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping(path = "/inscription")
    public boolean inscription(String nickname, String password) throws NoSuchAlgorithmException {
        password = Hasher.hashing(password);
        if (userRepository.findByNickname(nickname).size() < 1){
            return false;
        }
        UsersEntity users = new UsersEntity(nickname,password);
        userRepository.save(users);
        return true;
    }
    @GetMapping(path = "/connection")
    public boolean connection(String nickname, String password) throws NoSuchAlgorithmException {
        password = Hasher.hashing(password);
        if (userRepository.findByNickname(nickname).size() < 1){
            return false;
        }
        UsersEntity users = userRepository.findByNickname(nickname).get(0);
        return Objects.equals(users.getPassword(), password);
    }
    @GetMapping(path = "/deconnection")
    public void deconnection() throws NoSuchAlgorithmException {
       // userRepository.deconnect();
    }

}
