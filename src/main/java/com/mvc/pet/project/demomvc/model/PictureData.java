package com.mvc.pet.project.demomvc.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.awt.*;
import java.util.LinkedHashMap;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PictureData {
    private String picture;
    private LinkedHashMap<String, Integer> colors = new LinkedHashMap<>();
}
