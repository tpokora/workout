package org.tpokora.workout.workouts;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class WorkoutServiceTest {

    @MockBean
    private WorkoutRepository mockedWorkoutRepository;

    private WorkoutService workoutService;

    @BeforeEach
    void setup() {
        workoutService = new WorkoutService(mockedWorkoutRepository);
    }

    @Test
    void getAllWorkoutsShouldReturnAllWorkouts() {
        // given
        ArrayList<Workout> workouts;
        workouts = new ArrayList<>();
        workouts.add(new Workout(1, "5x5"));
        workouts.add(new Workout(2, "PPL"));
        when(workoutService.getAllWorkouts()).thenReturn(workouts);

        // when
        List<Workout> allWorkouts = workoutService.getAllWorkouts();

        // expect
        assertThat(allWorkouts).isNotEmpty().hasSize(workouts.size());
    }

    @Test
    void getSaveShouldPersistWorkout() {
        // given
        WorkoutService workoutService = new WorkoutService(new WorkoutRepository());
        Workout newWorkout = new Workout(10, "newWorkout");
        int originalWorkoutsAmount = workoutService.getAllWorkouts().size();
        assertThat(originalWorkoutsAmount).isEqualTo(2);

        // when
        workoutService.save(newWorkout);

        // expect
        assertThat(workoutService.getAllWorkouts().size()).isEqualTo(3);
    }
}