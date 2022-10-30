package org.tpokora.workout.workouts.model;

import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

@Builder(builderMethodName = "hiddenBuilder")
@Value
public class WorkoutDto {

    @NonNull
    String name;
    String description;

    public static WorkoutDtoBuilder builder(String name) {
        return new WorkoutDtoBuilder().name(name);
    }
}
