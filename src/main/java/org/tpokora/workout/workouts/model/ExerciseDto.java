package org.tpokora.workout.workouts.model;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ExerciseDto {
    String name;
    String description;
}
