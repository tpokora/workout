package org.tpokora.workout.workouts.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.MockMvc;
import org.tpokora.workout.workouts.model.Workout;
import org.tpokora.workout.workouts.service.WorkoutService;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(WorkoutsController.class)
class WorkoutsControllerTest {

    private final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @MockBean
    private WorkoutService workoutService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testGetWorkoutsShouldReturnWorkoutsListWithStatusCode200() throws Exception {
        // given
        Workout firstWorkout = new Workout(1, "test workout 1");
        Workout secondWorkout = new Workout(2, "test workout 2");
        List<Workout> workouts = Arrays.asList(firstWorkout, secondWorkout);

        when(workoutService.getAllWorkouts()).thenReturn(workouts);

        // then
        this.mockMvc.perform(get("/api/workouts")
                .with(testUserForRequest())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].id", is(firstWorkout.getId())))
                .andExpect(jsonPath("$.[0].name", is(firstWorkout.getName())))
                .andExpect(jsonPath("$.[1].id", is(secondWorkout.getId())))
                .andExpect(jsonPath("$.[1].name", is(secondWorkout.getName())));
    }

    @Test
    void testCreateWorkout() throws Exception {
        // given
        Workout testWorkout = new Workout(null, "test workout 1");
        String workoutString = OBJECT_MAPPER.writeValueAsString(testWorkout);

        when(workoutService.save(any())).thenReturn(testWorkout);

        // then
        this.mockMvc.perform(post("/api/workouts")
                .with(testUserForRequest())
                .with(csrf())
                .content(workoutString)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", is(testWorkout.getId())))
                .andExpect(jsonPath("$.name", is(testWorkout.getName())));
    }

    private static SecurityMockMvcRequestPostProcessors.UserRequestPostProcessor testUserForRequest() {
        return user("testUser").password("testPassword");
    }
}