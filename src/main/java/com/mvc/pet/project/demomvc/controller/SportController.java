package com.mvc.pet.project.demomvc.controller;

import com.mvc.pet.project.demomvc.service.sport.SportServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SportController {

    @Autowired
    SportServiceImpl sportService;

    @GetMapping("/sport")
    public String greeting(Model model) {
        model.addAttribute("sports", sportService.processSportFile());
        return "sport";
    }
}
