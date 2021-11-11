package org.tpokora.workout.workouts.service;

import org.springframework.stereotype.Service;
import org.tpokora.workout.workouts.persistance.WorkoutRepository;
import org.tpokora.workout.workouts.model.Workout;

import java.util.List;

@Service
public class WorkoutService {

    private WorkoutRepository workoutRepository;

    public WorkoutService(WorkoutRepository workoutRepository) {
        this.workoutRepository = workoutRepository;
    }

    public List<Workout> getAllWorkouts() {
        return workoutRepository.getAllWorkouts();
    }

    public Workout save(Workout workout) {
        return workoutRepository.save(workout);
    }
}
