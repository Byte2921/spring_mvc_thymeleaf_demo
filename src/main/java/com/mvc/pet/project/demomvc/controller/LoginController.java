package com.mvc.pet.project.demomvc.controller;

import com.mvc.pet.project.demomvc.service.sport.impl.LoginServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @Autowired
    LoginServiceImpl loginService;

    @GetMapping(value = {"/", "/login"})
    public String login() {
        if (loginService.isAuthenticated()) {
            return "redirect:/sport";
        }
        return "login";
    }
}
