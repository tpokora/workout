package org.tpokora.workout.workouts.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.tpokora.workout.workouts.model.WorkoutDto;

import java.util.Collections;
import java.util.List;

@AllArgsConstructor
@Service
public class WorkoutService {


    public List<WorkoutDto> getAllWorkouts() {
        return Collections.emptyList();
    }
}
