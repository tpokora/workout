package org.tpokora.workout.workouts.model;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Exercise {
    String name;
    int sets;
    int reps;
}
