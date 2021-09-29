package com.mvc.pet.project.demomvc.service.sport;

import com.mvc.pet.project.demomvc.model.Activity;
import com.mvc.pet.project.demomvc.model.Sport;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
@NoArgsConstructor
public class SportServiceImpl implements SportService {
    private static final String COMMA_DELIMITER = ",";

    @Override
    public HashSet<Sport> processSports(double activityType) {
        HashSet<Sport> sports = new HashSet<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/SPORT_1632579326949.csv"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.matches("[^a-zA-Z]+")) {
                    sports.add(CreateNewSport(line));
                }
            }
            return activityType == -1 ? sports : sports.stream()
                    .filter(sport -> Activity.valueOf(activityType).get().equals(sport.getActivityType()))
                    .collect(Collectors.toCollection(HashSet::new));
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return sports;
        }
    }

    @Override
    public Sport CreateNewSport(String line) {
        double[] data = Arrays.stream(line.split(COMMA_DELIMITER))
                .mapToDouble(Double::parseDouble)
                .toArray();
        return Sport.builder()
                .activityType(data[0])
                .startTime(data[1])
                .duration(data[2])
                .minimumPace(data[3])
                .maximumPace(data[4])
                .distance(data[5])
                .averagePace(data[6])
                .calories(data[7])
                .build();
    }

    @Override
    public Map<String, Double> getAllActivities() {
        return Stream.of(Activity.values())
                .collect(Collectors.toMap(Enum::toString, Activity::getNumber))
                .entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, HashMap::new));
    }
}
