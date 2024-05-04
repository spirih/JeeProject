package com.example.testspringmaven.controller;

import com.example.testspringmaven.object.Activity;
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
    public String groups(Model model){
        ArrayList<GroupactivitiesEntity> list= groupRepository.getGroupactivitiesEntitiesByUser(Common.getUsers().getNickname());
        model.addAttribute("groups2",list);
        return "groups";
    }
    @GetMapping(path = "/group" )
    public String group(Model model,@RequestParam(name="id", defaultValue="-1") int id){
        if(id < 0){
            id = (int) model.getAttribute("group");
        }
        GroupactivitiesEntity grae = groupRepository.findById(id);
        model.addAttribute("group",grae);
        ArrayList<GroupandactivitiesEntity> graae = gral.getGroupandactivitiesEntitiesByIdGroup(id);
        ArrayList<Activity> activities = new ArrayList<>();
        float average = 0;
        int count = 0;
        for(GroupandactivitiesEntity g : graae){
            if(g.getNote() > 0 ){
                count++;
                average += g.getNote();
            }
            activities.add(generateActivity(g));
        }
        if(count > 0){
            average = average/count;
        }
        model.addAttribute("average",average);
        model.addAttribute("activities",activities);


        return "group";
    }

    public Activity generateActivity(GroupandactivitiesEntity whole){
        ActivitiesEntity entity = activityRepository.findById(whole.getIdActivity());
        Activity activity = new Activity();
        activity.setId(entity.getId());
        activity.setName(entity.getName());
        activity.setNote(whole.getNote());
        activity.setGroupAndActionID(whole.getId());
        return activity;
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
