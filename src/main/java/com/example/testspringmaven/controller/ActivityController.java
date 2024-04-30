package com.example.testspringmaven.controller;

import com.example.testspringmaven.persistant.ActivitiesEntity;
import com.example.testspringmaven.repository.ActivityRepository;
import com.example.testspringmaven.repository.GroupRepository;
import com.example.testspringmaven.repository.UserRepository;
import com.example.testspringmaven.utilitary.Hasher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

@Controller
public class ActivityController {
    ArrayList<ActivitiesEntity> listActivities;

    @Autowired
    private ActivityRepository activityRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GroupRepository groupRepository;

    @GetMapping(path = "/activites")
    public void activities(Model model, @RequestParam(name="name", defaultValue="") String name ){
        ArrayList<ActivitiesEntity> list= activityRepository.findByKey(name);
        model.addAttribute("activities",list);
    }
    @GetMapping(path = "/choseActivity")
    public void choseActivity(Model model, @RequestParam(name="id", defaultValue="-1") int id,@RequestParam(name="group", defaultValue="-1") int group ){
        if(id > -1 && group > -1){
            groupRepository.addActivityToGroup(group,id);
            model.addAttribute("id",id);
            model.addAttribute("group",group);
        }
    }
    @GetMapping(path = "/noteActivity")
    public void noteActivity(Model model, @RequestParam(name="id", defaultValue="-1") int id,@RequestParam(name="note", defaultValue="-1") int value ){
        if(id < 6 && id > -1){
            activityRepository.noteActivity(id,value);
            model.addAttribute("id",id);
            model.addAttribute("noe",value);
        }
    }
    @GetMapping(path = "/inscription")
    public boolean inscription(String nickname, String password) throws NoSuchAlgorithmException {
        password = Hasher.hashing(password);
        boolean succes = userRepository.inscrire(nickname,password);
        return succes;
    }

}
