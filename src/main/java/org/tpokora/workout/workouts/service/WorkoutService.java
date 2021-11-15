package org.tpokora.workout.workouts.service;

import org.springframework.stereotype.Service;
import org.tpokora.workout.workouts.model.Workout;
import org.tpokora.workout.workouts.persistance.WorkoutRepository;

import java.util.List;

@Service
public class WorkoutService {

    private WorkoutRepository workoutRepository;

    public WorkoutService(WorkoutRepository workoutRepository) {
        this.workoutRepository = workoutRepository;
    }

    public Workout getWorkoutById(Integer id) {
        return workoutRepository.findWorkoutById(id)
                .orElseThrow(() -> new IllegalArgumentException("Could not find Workout with id: " + id));
    }

    public List<Workout> getAllWorkouts() {
        return workoutRepository.getAllWorkouts();
    }

    public Workout save(Workout workout) {
        return workoutRepository.save(workout);
    }
}
