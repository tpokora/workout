package org.tpokora.workout.workouts.persistance;

import org.springframework.stereotype.Component;
import org.tpokora.workout.workouts.exceptions.ItemAlreadyExistsException;
import org.tpokora.workout.workouts.exceptions.ItemNotFoundException;
import org.tpokora.workout.workouts.model.Exercise;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Component
public class ExerciseRepository {

    private final HashMap<String, Exercise> exerciseHashMap;

    public ExerciseRepository() {
        this.exerciseHashMap = new HashMap<>();
    }

    public List<Exercise> getAll() {
        return new ArrayList<>(exerciseHashMap.values());
    }

    public Exercise getByName(String name) {
        return Optional.ofNullable(exerciseHashMap.get(name)).orElseThrow(ItemNotFoundException::new);
    }

    public Exercise save(Exercise exerciseToSave) {
        checkForExistingExercise(exerciseToSave).ifPresent(exercise -> { throw new ItemAlreadyExistsException();});
        exerciseHashMap.put(exerciseToSave.getName(), exerciseToSave);
        return exerciseHashMap.get(exerciseToSave.getName());
    }

    private Optional<String> checkForExistingExercise(Exercise exerciseToSave) {
        return exerciseHashMap.keySet()
                .stream()
                .filter(exercise -> exercise.equalsIgnoreCase(exerciseToSave.getName()))
                .findAny();
    }

    public void delete(String exerciseName) {
        Exercise exerciseByName = getByName(exerciseName);
        exerciseHashMap.remove(exerciseByName.getName());
    }

    public Exercise update(String exerciseToUpdate, Exercise newExercise) {
        getByName(exerciseToUpdate);
        save(newExercise);
        delete(exerciseToUpdate);
        return exerciseHashMap.get(newExercise.getName());
    }
}
