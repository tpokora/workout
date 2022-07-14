package org.tpokora.workout.workouts.persistance;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.tpokora.workout.workouts.exceptions.ItemAlreadyExistsException;
import org.tpokora.workout.workouts.exceptions.ItemNotFoundException;
import org.tpokora.workout.workouts.model.Exercise;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ExerciseRepositoryTest {

    private ExerciseRepository exerciseRepository = new ExerciseRepository();

    @Test
    void saveShouldSaveExercise() {
        Assertions.assertEquals(0, exerciseRepository.getAll().size());
        // given
        Exercise exerciseToSave = createExercise("Squat", 5, 5);

        // when
        Exercise savedExercise = exerciseRepository.save(exerciseToSave);

        // then
        Assertions.assertEquals(exerciseToSave.hashCode(), savedExercise.hashCode());
    }

    @Test
    void saveShouldThrowItemAlreadyExistsExceptionForExercisesWithTheSameName() {
        // given
        Exercise exerciseToSave = createExercise("Squat", 5, 5);
        exerciseRepository.save(exerciseToSave);

        // expect
        Assertions.assertThrows(ItemAlreadyExistsException.class, () -> exerciseRepository.save(exerciseToSave));
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
        Assertions.assertEquals(2, exerciseList.size());
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
        Assertions.assertEquals(exerciseToSave1, exerciseByName);
    }

    @Test
    void getByNameShouldThrowItemNotFoundExceptionWhenExerciseIsNotFound() {
        // expect
        Assertions.assertThrows(ItemNotFoundException.class, () -> exerciseRepository.getByName("Squat"));
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
        Assertions.assertThrows(ItemNotFoundException.class, () -> exerciseRepository.getByName("Squat"));
    }

    @Test
    void deleteShouldThrowExceptionWhenItemToDeleteNotExists() {
        // given
        Assertions.assertEquals(0, exerciseRepository.getAll().size());

        // expect
        Assertions.assertThrows(ItemNotFoundException.class, () -> exerciseRepository.delete("Squat"));
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
        Assertions.assertEquals(changedExercise, updatedExercise);
    }

    @Test
    void updateShouldThrowExceptionIfExerciseToUpdateDoesNotExist() {
        // given
        Exercise changedExercise = createExercise("Front Squat", 3, 10);

        // expect
        Assertions.assertThrows(ItemNotFoundException.class, () -> exerciseRepository.update("NotExistingExercise", changedExercise));
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
        Assertions.assertThrows(ItemAlreadyExistsException.class, () -> exerciseRepository.update("Front Squat", changedExercise));
    }


    private Exercise createExercise(String name, int sets, int reps) {
        return Exercise.builder()
                .name(name)
                .sets(sets)
                .reps(reps)
                .build();
    }
}