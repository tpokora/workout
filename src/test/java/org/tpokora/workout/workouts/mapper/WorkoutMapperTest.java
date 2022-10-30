package org.tpokora.workout.workouts.mapper;

import org.junit.jupiter.api.Test;
import org.tpokora.workout.workouts.model.WorkoutDto;
import org.tpokora.workout.workouts.persistance.entity.WorkoutEntity;

import static org.assertj.core.api.Assertions.assertThat;

class WorkoutMapperTest {

    public static final String NAME = "workout";
    public static final String DESCRIPTION = "description";

    private final WorkoutMapper workoutMapper = new WorkoutMapper();

    @Test
    void toEntityShouldHaveEqualFieldsAsDto() {
        // given
        WorkoutDto workoutDto = WorkoutDto.builder(NAME)
                .description(DESCRIPTION)
                .build();

        // when
        WorkoutEntity workoutEntity = workoutMapper.toEntity(workoutDto);

        // then
        assertThat(workoutEntity.getName()).isEqualTo(NAME);
        assertThat(workoutEntity.getDescription()).isEqualTo(DESCRIPTION);
    }

    @Test
    void toDtoShouldHaveEqualFieldsAsEntity() {
        // given
        WorkoutEntity workoutEntity = new WorkoutEntity(1L, NAME, DESCRIPTION);

        // when
        WorkoutDto workoutDto = workoutMapper.toDto(workoutEntity);

        // then
        assertThat(workoutDto.getName()).isEqualTo(NAME);
        assertThat(workoutDto.getDescription()).isEqualTo(DESCRIPTION);
    }
}