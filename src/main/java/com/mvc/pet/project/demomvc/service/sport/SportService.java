package com.mvc.pet.project.demomvc.service.sport;

import com.mvc.pet.project.demomvc.model.Sport;

import java.util.HashSet;
import java.util.Map;

public interface SportService {
    HashSet<Sport> getSports(double activityType);
    HashSet<Sport> filterSports(HashSet<Sport> sports, double activityType);
    HashSet<Sport> mergeSportsData(HashSet<Sport> sports);
    Sport CreateNewSport(String line);
    Map<String, Double> getAllActivities();
    void addNewSport(Sport sport);
}
