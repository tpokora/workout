package org.tpokora.workout.workouts.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class Workout {

    Integer id;
    String name;
    List<WorkoutDay> workoutDays;
}