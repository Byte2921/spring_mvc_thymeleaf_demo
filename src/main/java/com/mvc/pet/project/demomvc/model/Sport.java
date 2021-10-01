package com.mvc.pet.project.demomvc.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Sport {
    @NotNull
    private Activity activityType;
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    @NotNull
    private LocalDateTime startTime = LocalDateTime.now();
    @NotNull
    private double duration;
    @NotNull
    @Min(100)
    private double distance;
    private double maximumPace;
    private double minimumPace;
    private double averagePace;
    private double calories;
    public static class SportBuilder {
        public SportBuilder activityType(double type) {
            this.activityType = Activity.valueOf(type).orElse(Activity.OTHER);
            return this;
        }

        public SportBuilder startTime(double epochSecond) {
            this.startTime = LocalDateTime.ofEpochSecond((long) epochSecond, 0, ZoneOffset.ofHours(2));
            return this;
        }

        public SportBuilder maximumPace(double maxPace) {
            this.maximumPace = maxPace * 60;
            return this;
        }

        public SportBuilder minimumPace(double minPace) {
            this.minimumPace = minPace * 60;
            return this;
        }

        public SportBuilder averagePace(double avgPace) {
            this.averagePace = avgPace * 60;
            return this;
        }
    }
}
