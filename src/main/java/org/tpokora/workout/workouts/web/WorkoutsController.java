//package org.tpokora.workout.workouts.web;
//
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//import org.tpokora.workout.workouts.model.Workout;
//import org.tpokora.workout.workouts.service.WorkoutService;
//
//import java.util.List;
//
//@RestController
//@RequestMapping(value = "/api")
//public class WorkoutsController {
//
//    private final WorkoutService workoutService;
//
//    public WorkoutsController(WorkoutService workoutService) {
//        this.workoutService = workoutService;
//    }
//
//    @CrossOrigin
//    @GetMapping(value = "/workouts", produces = "application/json")
//    public ResponseEntity<List<Workout>> getWorkouts() {
//        return new ResponseEntity<>(workoutService.getAllWorkouts(), HttpStatus.OK);
//    }
//
//    @CrossOrigin
//    @PostMapping(value = "/workouts", produces = "application/json")
//    public ResponseEntity<Workout> createWorkout(@RequestBody Workout workout) {
//        Workout savedWorkout = workoutService.save(workout);
//        return new ResponseEntity<>(savedWorkout, HttpStatus.CREATED);
//    }
//
//    @CrossOrigin
//    @GetMapping(value = "/workouts/{id}", produces = "application/json")
//    public ResponseEntity<Workout> getWorkout(@PathVariable("id") Integer id) {
//        return new ResponseEntity<>(workoutService.getWorkoutById(id), HttpStatus.OK);
//    }
//
//
//}
