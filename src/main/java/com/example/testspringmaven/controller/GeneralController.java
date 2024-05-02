package com.example.testspringmaven.controller;

import com.example.testspringmaven.persistant.ActivitiesEntity;
import com.example.testspringmaven.repository.ActivitiesRepository;
import com.example.testspringmaven.utilitary.ActivityReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.FileNotFoundException;
import java.util.ArrayList;

@Controller
public class GeneralController {

    @Autowired
    private ActivitiesRepository activityRepository;

    @GetMapping(path = "/error")
    public String choseActivity(Model model, @RequestParam(name="group", defaultValue="") String group ){
        return "error";
    }
    @PostMapping(path = "/")
    public String checkDataPost( ) throws FileNotFoundException {
        if(activityRepository.findAll().size() < 1){
            ArrayList<ActivitiesEntity> list = ActivityReader.analyseString("sportsantecvl.json");
            activityRepository.saveAll(list);
        }
        System.out.println("hello, I'm here to tell that it worked until /");

        return "redirect:/connection";
    }
}
