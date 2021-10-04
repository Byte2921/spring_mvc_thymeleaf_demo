package com.mvc.pet.project.demomvc.service.sport;

import com.mvc.pet.project.demomvc.model.Sport;

import java.util.HashSet;
import java.util.Map;

public interface SportService {
    HashSet<Sport> filterSports(double activityType);
    Sport CreateNewSport(String line);
    Map<String, Double> getActivities();
    void addNewSport(Sport sport);
}
