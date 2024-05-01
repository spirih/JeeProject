package com.example.testspringmaven.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class GeneralController {
    @GetMapping(path = "/error")
    public String choseActivity(Model model, @RequestParam(name="group", defaultValue="") String group ){
        return "error";
    }
}
