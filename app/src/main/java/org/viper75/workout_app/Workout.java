package org.viper75.workout_app;

import java.util.Arrays;
import java.util.List;

public class Workout {
    private String title;
    private String description;

    public static final List<Workout> WORKOUTS = Arrays.asList(
            new Workout("Workout 1","Workout 1 Description"),
            new Workout("Workout 2","Workout 2 Description"),
            new Workout("Workout 3","Workout 3 Description"),
            new Workout("Workout 4","Workout 4 Description")
    );

    public Workout(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
