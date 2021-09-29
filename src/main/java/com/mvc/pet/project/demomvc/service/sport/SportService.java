package com.mvc.pet.project.demomvc.service.sport;

import com.mvc.pet.project.demomvc.model.Sport;

import java.util.HashSet;

public interface SportService {
    HashSet<Sport> processSportFile();
    Sport CreateNewSport(String line);
}
