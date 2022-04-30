package org.tpokora.workout.workouts.service;

import org.springframework.stereotype.Service;
import org.tpokora.workout.workouts.model.Workout;
import org.tpokora.workout.workouts.persistance.WorkoutRepositoryInMemory;

import java.util.List;

@Service
public class WorkoutService {

    private WorkoutRepositoryInMemory workoutRepositoryInMemory;

    public WorkoutService(WorkoutRepositoryInMemory workoutRepositoryInMemory) {
        this.workoutRepositoryInMemory = workoutRepositoryInMemory;
    }

    public Workout getWorkoutById(Integer id) {
        return workoutRepositoryInMemory.findWorkoutById(id)
                .orElseThrow(() -> new IllegalArgumentException("Could not find Workout with id: " + id));
    }

    public List<Workout> getAllWorkouts() {
        return workoutRepositoryInMemory.getAllWorkouts();
    }

    public Workout save(Workout workout) {
        return workoutRepositoryInMemory.save(workout);
    }
}
