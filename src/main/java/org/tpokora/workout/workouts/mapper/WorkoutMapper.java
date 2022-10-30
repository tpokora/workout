package org.tpokora.workout.workouts.mapper;

import org.tpokora.workout.workouts.model.WorkoutDto;
import org.tpokora.workout.workouts.persistance.entity.WorkoutEntity;

public class WorkoutMapper {

    public WorkoutEntity toEntity(WorkoutDto workoutDto) {
        return new WorkoutEntity(null, workoutDto.getName(), workoutDto.getDescription());
    }

    public WorkoutDto toDto(WorkoutEntity workoutEntity) {
        return WorkoutDto.builder(workoutEntity.getName())
                .description(workoutEntity.getDescription())
                .build();
    }
}
