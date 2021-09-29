package com.mvc.pet.project.demomvc.model;

import java.util.Arrays;
import java.util.Optional;

public enum Activity {
    OTHER(0),
    IDLE(1),
    SLEEPING(2),
    RUNNING(3),
    JUMP_ROPE(4),
    TREADMILL(5),
    INDOOR_CYCLING(6),
    WALKING(7),
    ELLIPTICAL(8),
    OUTDOOR_CYCLING(9),
    ROWING(10),
    YOGA(11),
    SWIMMING(14),
    FREESTYLE(16);

    private final double number;

    Activity(double number) {
        this.number = number;
    }

    public static Optional<Activity> valueOf(double number) {
        return Arrays.stream(values())
                .filter(Activity -> Activity.number == number)
                .findFirst();
    }

    public double getNumber() {
        return this.number;
    }
}
