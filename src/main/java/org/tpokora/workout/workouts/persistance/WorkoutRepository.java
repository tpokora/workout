//package org.tpokora.workout.workouts.persistance;
//
//import org.springframework.stereotype.Component;
//import org.tpokora.workout.workouts.model.Exercise;
//import org.tpokora.workout.workouts.model.Workout;
//import org.tpokora.workout.workouts.model.WorkoutDay;
//
//import java.util.*;
//
//@Component
//public class WorkoutRepository {
//
//    private HashMap<Integer, Workout> workouts;
//
//    public WorkoutRepository() {
//        workouts = new HashMap<>();
//        workouts.put(2, createMockWorkout("5x5"));
//        workouts.put(1, createMockWorkout("5x5v2"));
//    }
//
//    public List<Workout> getAllWorkouts() {
//        ArrayList<Workout> workoutsList = new ArrayList<>();
//        workouts.forEach((k, v) -> workoutsList.add(v));
//        return workoutsList;
//    }
//
//    public Optional<Workout> findWorkoutById(Integer id) {
//        return workouts.get(id) != null ? Optional.of(workouts.get(id)) : Optional.empty();
//    }
//
//    public Workout save(Workout workout) {
//        if (workout.getId() == null) {
//            workout.setId(generateNextId());
//        }
//        workouts.put(workout.getId(), workout);
//        return workout;
//    }
//
//    private int generateNextId() {
//        TreeSet<Integer> idSet = new TreeSet<>();
//        workouts.forEach((k, v) -> idSet.add(v.getId()));
//        Optional<Integer> optionalInteger = Optional.ofNullable(idSet.pollLast());
//        if (optionalInteger.isPresent()) {
//            int nextId = optionalInteger.get();
//            return ++nextId;
//        }
//
//        return 1;
//    }
//
//    private Workout createMockWorkout(String name) {
//        return Workout.builder()
//                .name(name)
//                .workoutDays(Arrays.asList(createMockWorkoutDay("A"), createMockWorkoutDay("B")))
//                .build();
//    }
//
//    private WorkoutDay createMockWorkoutDay(String name) {
//        return WorkoutDay.builder()
//                .name(name)
//                .exercises(
//                        Arrays.asList(
//                                createExercise("Squat", 5, 5),
//                                createExercise("Bench Press", 5, 5),
//                                createExercise("Deadlift", 1, 5)))
//                .build();
//    }
//
//    private Exercise createExercise(String name, int sets, int reps) {
//        return Exercise.builder()
//                .name(name)
//                .sets(sets)
//                .reps(reps)
//                .build();
//    }
//}
