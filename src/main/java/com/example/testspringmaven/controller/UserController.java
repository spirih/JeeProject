package com.example.testspringmaven.controller;

import com.example.testspringmaven.persistant.UsersEntity;
import com.example.testspringmaven.repository.UserRepository;
import com.example.testspringmaven.utilitary.Hasher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.NoSuchAlgorithmException;
import java.util.Objects;

@Controller
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @PostMapping(path = "/sub")
    public String subscribe(@RequestParam(value = "nickname")String nickname, @RequestParam(value = "password")String password) throws NoSuchAlgorithmException {
        boolean value = canSub(nickname, password);
        if(!value){
            System.out.println("account creation failed");
            return "activityPage";
        }
        System.out.println("account creation successed");
        return "inscription";
    }
    @GetMapping(path = "/inscription")
    public String inscription(){
        return "inscription";
    }

    private boolean canSub(String nickname, String password) throws NoSuchAlgorithmException {
        password = Hasher.hashing(password);
        if (userRepository.findByNickname(nickname).size() < 1){
            return false;
        }
        UsersEntity users = new UsersEntity(nickname, password);
        userRepository.save(users);
        return true;
    }

    @GetMapping(path = "/connection")
    public String connection() {
        return "connection";
    }
    @PostMapping(path = "/connect")
    public String connect(@RequestParam(value = "nickname")String nickname, @RequestParam(value = "password")String password) throws NoSuchAlgorithmException {
        System.out.println("App survived long enought to see a tentative of connection");
        boolean can = canConnect(nickname, password);
        if(can){
            System.out.println("failed connection");
            return "main";
        }
        return "connection";
    }

    private boolean canConnect(String nickname, String password) throws NoSuchAlgorithmException {
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
