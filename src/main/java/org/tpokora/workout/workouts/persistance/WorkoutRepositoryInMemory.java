package org.tpokora.workout.workouts.persistance;

import org.springframework.stereotype.Component;
import org.tpokora.workout.workouts.model.Workout;

import java.util.*;

@Component
public class WorkoutRepositoryInMemory implements WorkoutRepository {

    private HashMap<Integer, Workout> workouts;

    public WorkoutRepositoryInMemory() {
        workouts = new HashMap<>();
        workouts.put(2, new Workout(2, "5x5"));
        workouts.put(1, new Workout(1, "PPL"));
    }

    public List<Workout> getAllWorkouts() {
        ArrayList<Workout> workoutsList = new ArrayList<>();
        workouts.forEach((k, v) -> workoutsList.add(v));
        return workoutsList;
    }

    public Optional<Workout> findWorkoutById(Integer id) {
        return workouts.get(id) != null ? Optional.of(workouts.get(id)) : Optional.empty();
    }

    public Workout save(Workout workout) {
        if (workout.getId() == null) {
            workout.setId(generateNextId());
        }
        workouts.put(workout.getId(), workout);
        return workout;
    }

    private int generateNextId() {
        TreeSet<Integer> idSet = new TreeSet<>();
        workouts.forEach((k, v) -> idSet.add(v.getId()));
        Optional<Integer> optionalInteger = Optional.ofNullable(idSet.pollLast());
        if (optionalInteger.isPresent()) {
            int nextId = optionalInteger.get();
            return ++nextId;
        }

        return 1;
    }
}
