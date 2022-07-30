package org.tpokora.workout.workouts.mapper;

import org.junit.jupiter.api.Test;
import org.tpokora.workout.workouts.model.ExerciseDto;
import org.tpokora.workout.workouts.persistance.entity.ExerciseEntity;

import static org.assertj.core.api.Assertions.assertThat;

class ExerciseMapperTest {

    public static final String NAME = "exercise";
    public static final String DESCRIPTION = "description";

    private final ExerciseMapper exerciseMapper = new ExerciseMapper();

    @Test
    void toEntityShouldHaveEqualFieldsAsDto() {
        // given
        ExerciseDto exerciseDto = ExerciseDto.builder()
                .name(NAME)
                .description(DESCRIPTION)
                .build();

        // when
        ExerciseEntity exerciseEntity = exerciseMapper.toEntity(exerciseDto);

        // then
        assertThat(exerciseEntity.getName()).isEqualTo(NAME);
        assertThat(exerciseEntity.getDescription()).isEqualTo(DESCRIPTION);
    }

    @Test
    void toDtoShouldHaveEqualFieldsAsEntity() {
        // given
        ExerciseEntity exerciseEntity = new ExerciseEntity(1L, NAME, DESCRIPTION);

        // when
        ExerciseDto exerciseDto = exerciseMapper.toDto(exerciseEntity);

        // then
        assertThat(exerciseDto.getName()).isEqualTo(NAME);
        assertThat(exerciseDto.getDescription()).isEqualTo(DESCRIPTION);
    }
}