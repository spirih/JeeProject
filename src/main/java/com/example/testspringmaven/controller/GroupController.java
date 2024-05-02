package com.example.testspringmaven.controller;

import com.example.testspringmaven.persistant.ActivitiesEntity;
import com.example.testspringmaven.persistant.GroupactivitiesEntity;
import com.example.testspringmaven.repository.ActivitiesRepository;
import com.example.testspringmaven.repository.GroupRepository;
import com.example.testspringmaven.utilitary.Common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class GroupController {
    @Autowired
    GroupRepository groupRepository;
    @Autowired
    private ActivitiesRepository activityRepository;

    @GetMapping(path = "/groups")
    public void activities(Model model){
        //ArrayList<GroupactivitiesEntity> list= groupRepository.getAll();
        //model.addAttribute("groups",list);
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
