package org.tpokora.workout.workouts.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.tpokora.workout.workouts.exceptions.ItemAlreadyExistsException;
import org.tpokora.workout.workouts.exceptions.ItemNotFoundException;
import org.tpokora.workout.workouts.model.ExerciseDto;
import org.tpokora.workout.workouts.persistance.ExerciseRepository;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class ExerciseServiceTest {

    @Autowired
    ExerciseService exerciseService;

    @Autowired
    ExerciseRepository exerciseRepository;

    @AfterEach
    void teardown() {
        exerciseRepository.deleteAll();
    }

    @Test
    void saveShouldSaveExercise() {
        Assertions.assertEquals(0, exerciseService.getAll().size());
        // given
        ExerciseDto exerciseToSave = createExerciseDto("Squat", "Description");

        // when
        ExerciseDto savedExercise = exerciseService.save(exerciseToSave);

        // then
        assertThat(exerciseToSave).hasSameHashCodeAs(savedExercise);
    }

    @Test
    void saveShouldThrowItemAlreadyExistsExceptionForExercisesWithTheSameName() {
        // given
        ExerciseDto exerciseToSave = createExerciseDto("Squat", "Description");
        exerciseService.save(exerciseToSave);

        // expect
        assertThatThrownBy(() -> exerciseService.save(exerciseToSave)).isInstanceOf(ItemAlreadyExistsException.class);
    }

    @Test
    void getAllShouldReturnAllElements() {
        // given
        ExerciseDto exerciseToSave1 = createExerciseDto("Squat", "Description");
        ExerciseDto exerciseToSave2 = createExerciseDto("Bench press", "Description");
        exerciseService.save(exerciseToSave1);
        exerciseService.save(exerciseToSave2);

        // when
        List<ExerciseDto> exerciseList = exerciseService.getAll();

        // then
        assertThat(exerciseList.size()).isEqualTo(2);
    }

    @Test
    void getByNameShouldReturnExerciseByName() {
        // given
        ExerciseDto exerciseToSave1 = createExerciseDto("Squat", "Description");
        ExerciseDto exerciseToSave2 = createExerciseDto("Bench press", "Description");
        exerciseService.save(exerciseToSave1);
        exerciseService.save(exerciseToSave2);

        // when
        ExerciseDto exerciseByName = exerciseService.getByName("Squat");

        // then
        assertThat(exerciseToSave1).isEqualTo(exerciseByName);
    }

    @Test
    void getByNameShouldThrowItemNotFoundExceptionWhenExerciseIsNotFound() {
        // expect
        assertThatThrownBy(() -> exerciseService.getByName("Squat")).isInstanceOf(ItemNotFoundException.class);
    }

    @Test
    void deleteShouldRemoveExerciseByName() {
        // given
        ExerciseDto exerciseToSave1 = createExerciseDto("Squat", "Description");
        ExerciseDto savedExercise = exerciseService.save(exerciseToSave1);
        assertThat(exerciseToSave1).isEqualTo(savedExercise);

        // when
        exerciseService.delete(savedExercise.getName());

        // expect
        assertThatThrownBy(() -> exerciseService.getByName("Squat")).isInstanceOf(ItemNotFoundException.class);
    }

    @Test
    void deleteShouldThrowExceptionWhenItemToDeleteNotExists() {
        // given
        assertThat(exerciseService.getAll().size()).isZero();

        // expect
        assertThatThrownBy(() -> exerciseService.delete("Squat")).isInstanceOf(ItemNotFoundException.class);
    }

    @Test
    void updateShouldUpdateExercise() {
        // given
        ExerciseDto exerciseToSave = createExerciseDto("Squat", "Description");
        exerciseService.save(exerciseToSave);
        ExerciseDto changedExercise = createExerciseDto("Front Squat", "Updated description");

        // when
        ExerciseDto updatedExercise = exerciseService.update(exerciseToSave, changedExercise);

        // then
        assertThat(changedExercise).isEqualTo(updatedExercise);
    }

    @Test
    void updateShouldThrowExceptionIfExerciseToUpdateDoesNotExist() {
        // given
        ExerciseDto notExistingExercise = createExerciseDto("NotExistingExercise", "NotExistingDescription");
        ExerciseDto changedExercise = createExerciseDto("Front Squat", "Updated description");

        // expect
        assertThatThrownBy(() -> exerciseService.update(notExistingExercise, changedExercise))
                .isInstanceOf(ItemNotFoundException.class);
    }

    @Test
    void updateShouldThrowExceptionIfExerciseWithTheSameNameAlreadyExists() {
        // given
        ExerciseDto exerciseToSave1 = createExerciseDto("Squat", "Description");
        ExerciseDto exerciseToSave2 = createExerciseDto("Bench press", "Description");
        exerciseService.save(exerciseToSave1);
        exerciseService.save(exerciseToSave2);
        ExerciseDto changedExercise = createExerciseDto("Squat", "New Description");

        // expect
        assertThatThrownBy(() -> exerciseService.update(exerciseToSave2, changedExercise))
                .isInstanceOf(ItemAlreadyExistsException.class);
    }

    private ExerciseDto createExerciseDto(String name, String description) {
        return ExerciseDto.builder()
                .name(name)
                .description(description)
                .build();
    }
}
