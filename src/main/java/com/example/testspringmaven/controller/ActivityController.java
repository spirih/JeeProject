package com.example.testspringmaven.controller;

import com.example.testspringmaven.persistant.ActivitiesEntity;
import com.example.testspringmaven.repository.ActivitiesRepository;
import com.example.testspringmaven.repository.GroupRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

@Controller
public class ActivityController {
    ArrayList<ActivitiesEntity> listActivities;

    @Autowired
    private ActivitiesRepository activityRepository;



    @Autowired
    private GroupRepository groupRepository;

    @GetMapping(path = "/activites")
    public void activities(Model model, @RequestParam(name="name", defaultValue="") String name ){
        //ArrayList<ActivitiesEntity> list= activityRepository.findByKey(name);
       // model.addAttribute("activities",list);
    }
    @GetMapping(path = "/choseActivity")
    public void choseActivity(Model model, @RequestParam(name="id", defaultValue="-1") int id,@RequestParam(name="group", defaultValue="-1") int group ){
        if(id > -1 && group > -1){
            //groupRepository.addActivityToGroup(group,id);
            model.addAttribute("id",id);
            model.addAttribute("group",group);
        }
    }
    @GetMapping(path = "/noteActivity")
    public void noteActivity(Model model, @RequestParam(name="id", defaultValue="-1") int id,@RequestParam(name="note", defaultValue="-1") int value ){
        if(id < 6 && id > -1){
            //activityRepository.noteActivity(id,value);
            model.addAttribute("id",id);
            model.addAttribute("noe",value);
        }
    }


}
