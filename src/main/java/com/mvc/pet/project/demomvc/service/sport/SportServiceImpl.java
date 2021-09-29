package com.mvc.pet.project.demomvc.service.sport;

import com.mvc.pet.project.demomvc.model.Sport;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;
import java.util.HashSet;
@Component
@NoArgsConstructor
public class SportServiceImpl implements SportService {
    private static final String COMMA_DELIMITER = ",";

    public HashSet<Sport> processSportFile() {
        HashSet<Sport> sports = new HashSet<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/SPORT_1632579326949.csv"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.matches("[^a-zA-Z]+")) {
                    sports.add(CreateNewSport(line));
                }
            }
            return sports;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return sports;
        }
    }

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
}
