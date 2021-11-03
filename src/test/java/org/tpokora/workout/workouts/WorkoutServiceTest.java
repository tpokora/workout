package org.tpokora.workout.workouts;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class WorkoutServiceTest {

    @MockBean
    private WorkoutRepository workoutRepository;

    private WorkoutService workoutService;

    @BeforeEach
    void setup() {
        workoutService = new WorkoutService(workoutRepository);
    }

    @Test
    void getAllWorkoutsShouldReturnAllWorkouts() {
        // given
        ArrayList<Workout> workouts;
        workouts = new ArrayList<>();
        workouts.add(new Workout(1, "5x5"));
        workouts.add(new Workout(2, "PPL"));
        when(workoutRepository.getAllWorkouts()).thenReturn(workouts);

        // when
        List<Workout> allWorkouts = workoutService.getAllWorkouts();

        // expect
        assertThat(allWorkouts).isNotEmpty().hasSize(workouts.size());
    }
}