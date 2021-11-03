package org.tpokora.workout.workouts;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class WorkoutsController {

    private static List<Workout> workouts = Arrays.asList(
            new Workout(1, "5x5"), new Workout(2, "PPL"));

    @CrossOrigin
    @GetMapping(value = "/workouts", produces = "application/json")
    public ResponseEntity<List<Workout>> getWorkouts() {
        return new ResponseEntity<>(workouts, HttpStatus.OK);
    }


}
