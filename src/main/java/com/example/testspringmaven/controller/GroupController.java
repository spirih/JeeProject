package com.example.testspringmaven.controller;

import com.example.testspringmaven.persistant.ActivitiesEntity;
import com.example.testspringmaven.persistant.GroupactivitiesEntity;
import com.example.testspringmaven.persistant.GroupandactivitiesEntity;
import com.example.testspringmaven.repository.ActivitiesRepository;
import com.example.testspringmaven.repository.GroupAndActivitiesRepository;
import com.example.testspringmaven.repository.GroupRepository;
import com.example.testspringmaven.utilitary.Common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;


@Controller
public class GroupController {
    @Autowired
    GroupRepository groupRepository;
    @Autowired
    private ActivitiesRepository activityRepository;
    @Autowired
    private GroupAndActivitiesRepository gral;

    @GetMapping(path = "/groups")
    public void groups(Model model){
        //ArrayList<GroupactivitiesEntity> list= groupRepository.getAll();
        //model.addAttribute("groups",list);
    }
    @GetMapping(path = "/group")
    public String group(Model model){
        int group = (int) model.getAttribute("group");
        return "lol";
    }


    @GetMapping(path = "/createGroup")
    public String choseActivity(Model model,@RequestParam(name="nomGroup") String group , @RequestParam(name="id") int id){
            if(group != null){
            if(!group.isEmpty()){
                //GroupactivitiesEntity gr = groupRepository.createGroupe(group);
                GroupactivitiesEntity g = new GroupactivitiesEntity(Common.getUsers().getNickname(),group);
                groupRepository.save(g);
                ActivitiesEntity activities = activityRepository.findById(id);
                model.addAttribute("activity",activities);
                model.addAttribute("groups",groupRepository.findAll());

            }
        }
        return "redirect:/activity?id="+id;
    }

}
