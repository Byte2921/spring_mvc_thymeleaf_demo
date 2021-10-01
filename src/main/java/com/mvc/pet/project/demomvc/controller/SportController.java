package com.mvc.pet.project.demomvc.controller;

import com.mvc.pet.project.demomvc.model.Sport;
import com.mvc.pet.project.demomvc.service.sport.SportServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Controller
public class SportController{

    @Autowired
    SportServiceImpl sportService;

    @GetMapping("/sport")
    public String greeting(@RequestParam(name = "activity", required = false, defaultValue = "-1") double activityType, Model model) {
        model.addAttribute("sports", sportService.getSports(activityType));
        model.addAttribute("activityType", sportService.getAllActivities());
        return "sport";
    }

    @GetMapping("/sport/new")
    public String showForm(Sport sport, Model model) {
        model.addAttribute("activities", sportService.getAllActivities());
        return "sportForm";
    }

    @PostMapping("sport/new")
    public String checkNewSport(@Valid Sport sport, BindingResult bindingResult,  Model model) {
        model.addAttribute("activities", sportService.getAllActivities());
        if (bindingResult.hasErrors()) {
            return "sportForm";
        }
        sportService.addNewSport(sport);
        return "redirect:/sport";
    }
}

