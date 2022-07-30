package org.tpokora.workout.workouts.web;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.tpokora.workout.workouts.model.ExerciseDto;
import org.tpokora.workout.workouts.service.ExerciseService;

import java.util.List;

@RestController
@RequestMapping(value = "/api/exercises")
@AllArgsConstructor
public class ExerciseController {

    private final ExerciseService exerciseService;

    @CrossOrigin
    @GetMapping(produces = "application/json")
    public ResponseEntity<List<ExerciseDto>> getExercises() {
        return new ResponseEntity<>(exerciseService.getAll(), HttpStatus.OK);
    }

    @CrossOrigin
    @PostMapping(produces = "application/json")
    public ResponseEntity<ExerciseDto> createExercise(@RequestBody ExerciseDto exerciseDto) {
        ExerciseDto savedExercise = exerciseService.save(exerciseDto);
        return new ResponseEntity<>(savedExercise, HttpStatus.CREATED);
    }

    @CrossOrigin
    @GetMapping(value = "/{name}", produces = "application/json")
    public ResponseEntity<ExerciseDto> getExercise(@PathVariable("name") String name) {
        return new ResponseEntity<>(exerciseService.getByName(name), HttpStatus.OK);
    }

    @CrossOrigin
    @DeleteMapping(value = "/{name}", produces = "application/json")
    public ResponseEntity<Void> deleteExercise(@PathVariable("name") String name) {
        exerciseService.delete(name);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
