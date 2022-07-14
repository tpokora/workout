//package org.tpokora.workout.workouts.service;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//import org.tpokora.workout.workouts.model.Workout;
//import org.tpokora.workout.workouts.persistance.WorkoutRepository;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.assertj.core.api.Assertions.assertThatThrownBy;
//import static org.mockito.ArgumentMatchers.anyInt;
//import static org.mockito.Mockito.when;
//
//@ExtendWith(SpringExtension.class)
//class WorkoutServiceTest {
//
//    @MockBean
//    private WorkoutRepository mockedWorkoutRepository;
//
//    private WorkoutService workoutService;
//
//    @BeforeEach
//    void setup() {
//        workoutService = new WorkoutService(mockedWorkoutRepository);
//    }
//
//
//    @Test
//    void getAllWorkoutsShouldReturnAllWorkouts() {
//        // given
//        ArrayList<Workout> workouts;
//        workouts = new ArrayList<>();
//        workouts.add(new Workout(1, "5x5"));
//        workouts.add(new Workout(2, "PPL"));
//        when(workoutService.getAllWorkouts()).thenReturn(workouts);
//
//        // when
//        List<Workout> allWorkouts = workoutService.getAllWorkouts();
//
//        // expect
//        assertThat(allWorkouts).isNotEmpty().hasSize(workouts.size());
//    }
//
//    @Test
//    void getSaveShouldPersistWorkout() {
//        // given
//        WorkoutService workoutService = new WorkoutService(new WorkoutRepository());
//        Workout newWorkout = new Workout(10, "newWorkout");
//        int originalWorkoutsAmount = workoutService.getAllWorkouts().size();
//        assertThat(originalWorkoutsAmount).isEqualTo(2);
//
//        // when
//        workoutService.save(newWorkout);
//
//        // expect
//        assertThat(workoutService.getAllWorkouts().size()).isEqualTo(3);
//    }
//
//    @Test
//    void getWorkoutByIdShouldReturnWorkoutWhenExistInPersistance() {
//        // given
//        WorkoutService workoutService = new WorkoutService(new WorkoutRepository());
//
//        // when
//        Workout workoutById = workoutService.getWorkoutById(1);
//
//        assertThat(workoutById.getId()).isEqualTo(1);
//        assertThat(workoutById.getName()).isEqualTo("PPL");
//    }
//
//    @Test
//    void getWorkoutByIdShouldReturnWorkoutWhenExist() {
//        // given
//        Workout workout = new Workout(1, "test workout");
//        when(mockedWorkoutRepository.findWorkoutById(anyInt())).thenReturn(Optional.of(workout));
//
//        // when
//        Workout workoutById = workoutService.getWorkoutById(1);
//
//        assertThat(workoutById.getId()).isEqualTo(workout.getId());
//        assertThat(workoutById.getName()).isEqualTo(workout.getName());
//    }
//
//    @Test
//    void getWorkoutByIdShouldThrowNotFoundException() {
//        // given
//        when(mockedWorkoutRepository.findWorkoutById(anyInt())).thenReturn(Optional.empty());
//
//        // expect
//        assertThatThrownBy(() -> workoutService.getWorkoutById(1))
//                .isInstanceOf(IllegalArgumentException.class)
//                .hasMessage("Could not find Workout with id: " + 1);
//    }
//}