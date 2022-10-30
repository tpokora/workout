package org.tpokora.workout.workouts.mapper;

import org.tpokora.workout.workouts.model.ExerciseDto;
import org.tpokora.workout.workouts.persistance.entity.ExerciseEntity;

public class ExerciseMapper {

    public ExerciseEntity toEntity(ExerciseDto exerciseDto) {
        return new ExerciseEntity(null, exerciseDto.getName(), exerciseDto.getDescription());
    }

    public ExerciseEntity toEntity(ExerciseDto exerciseDto, Long id) {
        return new ExerciseEntity(id, exerciseDto.getName(), exerciseDto.getDescription());
    }

    public ExerciseDto toDto(ExerciseEntity exerciseEntity) {
        return ExerciseDto.builder(exerciseEntity.getName())
                .description(exerciseEntity.getDescription())
                .build();
    }
}