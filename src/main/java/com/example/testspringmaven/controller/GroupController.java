package com.example.testspringmaven.controller;

import com.example.testspringmaven.persistant.GroupactivitiesEntity;
import com.example.testspringmaven.repository.GroupRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@Controller
public class GroupController {
    @Autowired
    GroupRepository groupRepository;

    @GetMapping(path = "/groups")
    public void activities(Model model){
        //ArrayList<GroupactivitiesEntity> list= groupRepository.getAll();
        //model.addAttribute("groups",list);
    }

    @GetMapping(path = "/createGroup")
    public void choseActivity(Model model, @RequestParam(name="group", defaultValue="") String group ){
        if(group != null){
            if(!group.isEmpty()){
                //GroupactivitiesEntity gr = groupRepository.createGroupe(group);

            }
        }
    }

}
