package com.example.testspringmaven.controller;

import com.example.testspringmaven.object.Activity;
import com.example.testspringmaven.persistant.ActivitiesEntity;
import com.example.testspringmaven.persistant.GroupactivitiesEntity;
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
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;

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
    public void activities(Model model2,@RequestParam(name="page", defaultValue="0") int page,@RequestParam(name="pageSize", defaultValue="10") int pageSize,@RequestParam(name="activitySearch", defaultValue="") String name,@RequestParam(name="filter", defaultValue="") String filter,@RequestParam(name="dir", defaultValue="") String dir){
        if(page < 0){page = 0;}
        if(pageSize < 0){pageSize = 0;}
        Pageable pageable = PageRequest.of(page,pageSize);
        model2.addAttribute("filter",filter);
        model2.addAttribute("dir",dir);
        if(dir.equals("asc")){
            pageable = PageRequest.of(page,pageSize, Sort.by("name").ascending());
        }else if(dir.equals("dsc")){
            pageable = PageRequest.of(page,pageSize, Sort.by("name").descending());
        }
        Page<ActivitiesEntity> list;

        if(name.equals("")){
            list = activityRepository.findAllPage(pageable);

        }else{
            if(!filter.equals("")){
                switch (filter){
                    case "description":
                        list = activityRepository.findAllLikeDescriptionPageable(name,pageable);
                        break;
                    case "name":
                        list = activityRepository.findAllLikeNamePageable(name,pageable);
                        break;
                    case "pathologie":
                        list = activityRepository.findAllLikePathologiePageable(name,pageable);
                        break;
                    default:
                        list = activityRepository.findAll(pageable);
                        break;
                }

            }else{
                list = activityRepository.findAll(pageable);
            }

        }
         for(ActivitiesEntity activities : list){
             calculateNote(activities);
         }

         System.out.println("hu");
         System.out.println(list.getNumberOfElements());
         System.out.println("hi");
         System.out.println(Common.getUsers().getNickname());
         model2.addAttribute("activities",list.getContent());
         model2.addAttribute("user",Common.getUsers());
         model2.addAttribute("nbPage",page);
         model2.addAttribute("sizePage",pageSize);

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
    public void noteActivity(Model model, @RequestParam(name="idIterate", defaultValue="-1") int id,@RequestParam(name="note", defaultValue="-1") int value ){

        if(value < 6 && value > -1 && id > -1){
            model.addAttribute("id",id);
            model.addAttribute("note",value);
        }
    }


    @GetMapping(path = "/activity")
    public String activities(Model model, @RequestParam(name="id") int id ){
        ActivitiesEntity activities = activityRepository.findById(id);
        model.addAttribute("activity",activities);
        model.addAttribute("groups",groupRepository.findAll());

        return "activityPage";
    }
    @GetMapping(path = "/add")
    public String add(Model model, @RequestParam(name="choice") int group,@RequestParam(name="activity") int activity ){
        GroupandactivitiesEntity ga = new GroupandactivitiesEntity(group,activity);
        groupAndActivityRepository.save(ga);
        GroupactivitiesEntity groupe = groupRepository.findById(group);
        model.addAttribute("group",groupe);
        ArrayList<GroupandactivitiesEntity> list = groupAndActivityRepository.getGroupandactivitiesEntitiesByIdGroup(group);
        ArrayList<Activity> activities = new ArrayList<>();
        for(GroupandactivitiesEntity g : list){
            activities.add(generateActivity(g));
        }
        model.addAttribute("activities",activities);
        return "group";
    }
    public Activity generateActivity(GroupandactivitiesEntity whole){
        ActivitiesEntity entity = activityRepository.findById(whole.getIdActivity());
        Activity activity = new Activity();
        activity.setId(entity.getId());
        activity.setName(entity.getName());
        activity.setNote(entity.getNote());
        activity.setGroupAndActionID(whole.getId());
        return activity;
    }
    @GetMapping(path = "/iterationActivity")
    public String iterate(Model model, @RequestParam(name="id") int idGroupAction){
        GroupandactivitiesEntity graa = groupAndActivityRepository.getGroupandactivitiesEntityById(idGroupAction);
        GroupactivitiesEntity gra = groupRepository.findById(graa.getIdGroup());
        ActivitiesEntity activity = activityRepository.findById(graa.getIdActivity());
        model.addAttribute("activity",activity);
        model.addAttribute("group",gra);
        model.addAttribute("iteration",graa);

        return "iterationActivity";
    }



}
