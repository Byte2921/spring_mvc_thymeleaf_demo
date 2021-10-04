package com.mvc.pet.project.demomvc.controller;

import com.mvc.pet.project.demomvc.model.PictureData;
import com.mvc.pet.project.demomvc.service.sport.impl.PictureServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
public class PictureController {

    @Autowired
    PictureServiceImpl pictureService;

    @GetMapping("/picture")
    public String picture(Model model) {
        model.addAttribute("pictureData", new PictureData());
        return "picture";
    }

    @PostMapping("/picture")
    public String processPicture(@RequestParam("picture") MultipartFile file, Model model) throws IOException {
        model.addAttribute("pictureData", pictureService.processImage(file));
        return "picture";
    }
}
