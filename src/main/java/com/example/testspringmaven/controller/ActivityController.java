package com.example.testspringmaven.controller;

import com.example.testspringmaven.object.Activity;
import com.example.testspringmaven.persistant.ActivitiesEntity;
import com.example.testspringmaven.persistant.GroupandactivitiesEntity;
import com.example.testspringmaven.repository.ActivitiesRepository;
import com.example.testspringmaven.repository.GroupAndActivitiesRepository;
import com.example.testspringmaven.repository.GroupRepository;

import com.example.testspringmaven.utilitary.ActivityReader;
import com.example.testspringmaven.utilitary.Common;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.io.FileNotFoundException;
import java.util.ArrayList;

@Controller
public class ActivityController {
    ArrayList<ActivitiesEntity> listActivities;

    @Autowired
    private ActivitiesRepository activityRepository;


    @Autowired
    GroupRepository groupRepository;
    @Autowired
    private GroupAndActivitiesRepository groupAndActivityRepository;



    @GetMapping(path = "/activities")
    public void activities(Model model2,@RequestParam(name="activitySearch", defaultValue="") String name){
        ModelAndView model = new ModelAndView("/activities");
        Pageable pageable = PageRequest.of(0,10);
        Page<ActivitiesEntity> list;
        if(name.equals("")){
            list = activityRepository.findAllPage(pageable);

        }else{
            list = activityRepository.findAllLikeNamePageable(name,pageable);

        }
         for(ActivitiesEntity activities : list){
             calculateNote(activities);
         }
         System.out.println("hu");
         System.out.println(list.getNumberOfElements());
         System.out.println("hi");
         model.addObject("activities",list);
         System.out.println(Common.getUsers().getNickname());
         model.addObject("user", Common.getUsers());
         model2.addAttribute("activities",list.getContent());
         model2.addAttribute("user",Common.getUsers());

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

    @GetMapping(path = "/activity")
    public String activities(Model model, @RequestParam(name="id") int id ){
        ActivitiesEntity activities = activityRepository.findById(id);
        model.addAttribute("activity",activities);
        model.addAttribute("groups",groupRepository.findAll());

        return "activityPage";
    }



}
