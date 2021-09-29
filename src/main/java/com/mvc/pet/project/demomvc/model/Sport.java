package com.mvc.pet.project.demomvc.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Sport {
    private Activity activityType;
    private LocalDateTime startTime;
    private Duration duration;
    private double distance;
    private Duration maximumPace;
    private Duration minimumPace;
    private Duration averagePace;
    private double calories;
    public static class SportBuilder {
        public SportBuilder activityType(double type) {
            this.activityType = Activity.valueOf(type).orElse(Activity.OTHER);
            return this;
        }

        public SportBuilder duration(double duration) {
            this.duration = Duration.ofSeconds((long) duration);
            return this;
        }

        public SportBuilder startTime(double epochSecond) {
            this.startTime = LocalDateTime.ofEpochSecond((long) epochSecond, 0, ZoneOffset.ofHours(2));
            return this;
        }

        public SportBuilder maximumPace(double maxPace) {
            this.maximumPace = Duration.ofSeconds((long) (maxPace * 60));
            return this;
        }

        public SportBuilder minimumPace(double minPace) {
            this.minimumPace = Duration.ofSeconds((long) (minPace * 60));
            return this;
        }

        public SportBuilder averagePace(double avgPace) {
            this.averagePace = Duration.ofSeconds((long) (avgPace * 60));
            return this;
        }
    }
}
