package org.tpokora.workout.workouts;

import org.springframework.stereotype.Service;

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
