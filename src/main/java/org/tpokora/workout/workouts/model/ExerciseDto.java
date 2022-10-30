package org.tpokora.workout.workouts.model;

import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

@Builder
@Value
public class ExerciseDto {

    @NonNull
    String name;
    String description;

    public static ExerciseDtoBuilder builder(String name) {
        return new ExerciseDtoBuilder().name(name);
    }
}
