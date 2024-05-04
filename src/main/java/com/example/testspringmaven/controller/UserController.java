package com.example.testspringmaven.controller;

import com.example.testspringmaven.persistant.UsersEntity;
import com.example.testspringmaven.repository.UserRepository;
import com.example.testspringmaven.utilitary.Common;
import com.example.testspringmaven.utilitary.Hasher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    public String subscribe(@RequestParam(value = "nickname")String nickname, @RequestParam(value = "password")String password, @RequestParam(value = "password2")String password2 ,@RequestParam(value = "age")int age,@RequestParam(value = "pathologie")String pathologie,@RequestParam(value = "sexe")char gender) throws NoSuchAlgorithmException {
        boolean value = canSub(nickname, password,age,gender,pathologie,password2);
        if(value){
            System.out.println("account creation succesfull");
            return "redirect:/activities";
        }
        System.out.println("account creation failed");
        return "inscription";
    }
    @GetMapping(path = "/inscription")
    public String inscription(){
        return "inscription";
    }

    private boolean canSub(String nickname, String password, int age, char gender, String pathologir, String password2) throws NoSuchAlgorithmException {
        if(!password.equals(password2)){
            return false;
        }
        password = Hasher.hashing(password);
        if (userRepository.findByNickname(nickname).size() > 0){
            System.out.println("Name already use");
            return false;
        }
        UsersEntity users = new UsersEntity(nickname, password,age,gender,pathologir);
        userRepository.save(users);
        setUser(nickname);
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
        if(!can){
            System.out.println("failed connection");
            return "connection";
        }
        setUser(nickname);
        return "redirect:/activities";
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
    public String deconnection() throws NoSuchAlgorithmException {
        Common.setUsers(null);
        return "connection";
       // userRepository.deconnect();
    }
    @GetMapping(path = "/user")
    public String user(Model model){
        model.addAttribute("user",Common.getUsers());
        return "user";
        // userRepository.deconnect();
    }
    private void setUser(String nickname){
        UsersEntity usersEntity = userRepository.getUsersEntityByNickname(nickname);
        Common.setUsers(usersEntity);
    }

}
