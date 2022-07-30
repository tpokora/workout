package org.tpokora.workout.workouts.persistance;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.tpokora.workout.workouts.exceptions.ItemAlreadyExistsException;
import org.tpokora.workout.workouts.exceptions.ItemNotFoundException;
import org.tpokora.workout.workouts.model.Exercise;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class ExerciseRepositoryTest {

    private final ExerciseRepository exerciseRepository = new ExerciseRepository();

    @Test
    void saveShouldSaveExercise() {
        Assertions.assertEquals(0, exerciseRepository.getAll().size());
        // given
        Exercise exerciseToSave = createExercise("Squat", 5, 5);

        // when
        Exercise savedExercise = exerciseRepository.save(exerciseToSave);

        // then
        assertThat(exerciseToSave).hasSameHashCodeAs(savedExercise);
    }

    @Test
    void saveShouldThrowItemAlreadyExistsExceptionForExercisesWithTheSameName() {
        // given
        Exercise exerciseToSave = createExercise("Squat", 5, 5);
        exerciseRepository.save(exerciseToSave);

        // expect
        assertThatThrownBy(() -> exerciseRepository.save(exerciseToSave)).isInstanceOf(ItemAlreadyExistsException.class);
    }

    @Test
    void getAllShouldReturnAllElements() {
        // given
        Exercise exerciseToSave1 = createExercise("Squat", 5, 5);
        Exercise exerciseToSave2 = createExercise("Bench press", 5, 5);
        exerciseRepository.save(exerciseToSave1);
        exerciseRepository.save(exerciseToSave2);

        // when
        List<Exercise> exerciseList = exerciseRepository.getAll();

        // then
        assertThat(exerciseList.size()).isEqualTo(2);
    }

    @Test
    void getByNameShouldReturnExerciseByName() {
        // given
        Exercise exerciseToSave1 = createExercise("Squat", 5, 5);
        Exercise exerciseToSave2 = createExercise("Bench press", 5, 5);
        exerciseRepository.save(exerciseToSave1);
        exerciseRepository.save(exerciseToSave2);

        // when
        Exercise exerciseByName = exerciseRepository.getByName("Squat");

        // then
        assertThat(exerciseToSave1).isEqualTo(exerciseByName);
    }

    @Test
    void getByNameShouldThrowItemNotFoundExceptionWhenExerciseIsNotFound() {
        // expect
        assertThatThrownBy(() -> exerciseRepository.getByName("Squat")).isInstanceOf(ItemNotFoundException.class);
    }

    @Test
    void deleteShouldRemoveExerciseByName() {
        // given
        Exercise exerciseToSave1 = createExercise("Squat", 5, 5);
        Exercise savedExercise = exerciseRepository.save(exerciseToSave1);
        Assertions.assertEquals(exerciseToSave1, savedExercise);

        // when
        exerciseRepository.delete(savedExercise.getName());

        // expect
        assertThatThrownBy(() -> exerciseRepository.getByName("Squat")).isInstanceOf(ItemNotFoundException.class);
    }

    @Test
    void deleteShouldThrowExceptionWhenItemToDeleteNotExists() {
        // given
        Assertions.assertEquals(0, exerciseRepository.getAll().size());

        // expect
        assertThatThrownBy(() -> exerciseRepository.delete("Squat")).isInstanceOf(ItemNotFoundException.class);
    }

    @Test
    void updateShouldUpdateExercise() {
        // given
        Exercise exerciseToSave = createExercise("Squat", 5, 5);
        exerciseRepository.save(exerciseToSave);
        Exercise changedExercise = createExercise("Front Squat", 3, 10);

        // when
        Exercise updatedExercise = exerciseRepository.update(exerciseToSave.getName(), changedExercise);

        // then
        assertThat(changedExercise).isEqualTo(updatedExercise);
    }

    @Test
    void updateShouldThrowExceptionIfExerciseToUpdateDoesNotExist() {
        // given
        Exercise changedExercise = createExercise("Front Squat", 3, 10);

        // expect
        assertThatThrownBy(() -> exerciseRepository.update("NotExistingExercise", changedExercise)).isInstanceOf(ItemNotFoundException.class);
    }

    @Test
    void updateShouldThrowExceptionIfExerciseWithTheSameNameAlreadyExists() {
        // given
        Exercise exerciseToSave1 = createExercise("Squat", 5, 5);
        Exercise exerciseToSave2 = createExercise("Front Squat", 5, 5);
        exerciseRepository.save(exerciseToSave1);
        exerciseRepository.save(exerciseToSave2);
        Exercise changedExercise = createExercise("Squat", 3, 10);

        // expect
        assertThatThrownBy(() -> exerciseRepository.update("Front Squat", changedExercise)).isInstanceOf(ItemAlreadyExistsException.class);
    }


    private Exercise createExercise(String name, int sets, int reps) {
        return Exercise.builder()
                .name(name)
                .sets(sets)
                .reps(reps)
                .build();
    }
}