package org.tpokora.workout.workouts;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class WorkoutsController {
    private final WorkoutService workoutService;

    public WorkoutsController(WorkoutService workoutService) {
        this.workoutService = workoutService;
    }

    @CrossOrigin
    @GetMapping(value = "/workouts", produces = "application/json")
    public ResponseEntity<List<Workout>> getWorkouts() {
        return new ResponseEntity<>(workoutService.getAllWorkouts(), HttpStatus.OK);
    }

    @CrossOrigin
    @PostMapping(value = "/workouts", produces = "application/json")
    public ResponseEntity<Workout> createWorkout(@RequestBody Workout workout) {
        return new ResponseEntity<>(workoutService.save(workout), HttpStatus.OK);
    }


}
