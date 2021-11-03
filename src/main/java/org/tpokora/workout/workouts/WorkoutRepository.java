package org.tpokora.workout.workouts;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class WorkoutRepository {

    private ArrayList<Workout> workouts;

    public WorkoutRepository() {
        workouts = new ArrayList<>();
        workouts.add(new Workout(1, "5x5"));
        workouts.add(new Workout(2, "PPL"));
    }

    public List<Workout> getAllWorkouts() {
        return workouts;
    }

    public Workout save(Workout workout) {
        workouts.add(workout);
        return workout;
    }
}
