package com.example.testspringmaven.controller;

import com.example.testspringmaven.object.Activity;
import com.example.testspringmaven.persistant.ActivitiesEntity;
import com.example.testspringmaven.persistant.GroupandactivitiesEntity;
import com.example.testspringmaven.repository.ActivitiesRepository;
import com.example.testspringmaven.repository.GroupAndActivitiesRepository;
import com.example.testspringmaven.repository.GroupRepository;

import com.example.testspringmaven.utilitary.ActivityReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileNotFoundException;
import java.util.ArrayList;

@Controller
public class ActivityController {
    ArrayList<ActivitiesEntity> listActivities;

    @Autowired
    private ActivitiesRepository activityRepository;



    @Autowired
    private GroupAndActivitiesRepository groupAndActivityRepository;

    @GetMapping(path = "/activites")
    public void activities(Model model, @RequestParam(name="name", defaultValue="") String name ){
        ArrayList<ActivitiesEntity> list= activityRepository.findAllByName(name);
         for(ActivitiesEntity activities : list){
             calculateNote(activities);
         }
         model.addAttribute("activities",list);
    }

    private void calculateNote(ActivitiesEntity activities) {
        ArrayList<GroupandactivitiesEntity> gral = groupAndActivityRepository.getGroupandactivitiesEntitiesByIdActivity(activities.getId());
        float value = 0;
        for(GroupandactivitiesEntity g : gral){
            value += g.getNote();
        }
        if(gral.size() > 0){
            value = value / gral.size();
        }
        activities.setNote(value);
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
            model.addAttribute("note",value);
        }
    }
    @GetMapping(path = "/")
    public String checkData( ) throws FileNotFoundException {
        if(activityRepository.findAll().size() < 1){
            ArrayList<ActivitiesEntity> list = ActivityReader.analyseString("sportsantecvl.json");
            activityRepository.saveAll(list);
        }
        System.out.println("hello, I'm here to tell that it worked until /");

        return "redirect:/connection";
    }



}
