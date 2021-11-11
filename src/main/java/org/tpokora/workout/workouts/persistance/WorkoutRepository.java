package org.tpokora.workout.workouts.persistance;

import org.springframework.stereotype.Component;
import org.tpokora.workout.workouts.model.Workout;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class WorkoutRepository {

    private ArrayList<Workout> workouts;

    public WorkoutRepository() {
        workouts = new ArrayList<>();
        workouts.add(new Workout(2, "5x5"));
        workouts.add(new Workout(1, "PPL"));
    }

    public List<Workout> getAllWorkouts() {
        return workouts;
    }

    public Workout save(Workout workout) {
        if (workout.getId() == null) {
            workout.setId(generateNextId());
        }
        workouts.add(workout);
        return workout;
    }

    private int generateNextId() {
        TreeSet<Integer> idSet = workouts.stream().map(Workout::getId).collect(Collectors.toCollection(TreeSet::new));
        Optional<Integer> optionalInteger = Optional.ofNullable(idSet.pollLast());
        if (optionalInteger.isPresent()) {
            int nextId = optionalInteger.get();
            return ++nextId;
        }

        return 1;
    }
}
