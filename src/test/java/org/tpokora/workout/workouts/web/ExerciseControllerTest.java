package org.tpokora.workout.workouts.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.MockMvc;
import org.tpokora.workout.workouts.exceptions.ItemNotFoundException;
import org.tpokora.workout.workouts.model.ExerciseDto;
import org.tpokora.workout.workouts.service.ExerciseService;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ExerciseController.class)
class ExerciseControllerTest {

    private static final String API_EXERCISES_URL = "/api/exercises";
    private final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @MockBean
    private ExerciseService exerciseService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testGetExercisesShouldReturnExercisesListWithStatusCode200() throws Exception {
        // given
        ExerciseDto firstExercise = createExercise("Squat", "Description");
        ExerciseDto secondExercise = createExercise("Bench Press", "Description");
        List<ExerciseDto> exerciseDtoList = Arrays.asList(firstExercise, secondExercise);

        when(exerciseService.getAll()).thenReturn(exerciseDtoList);

        // then
        this.mockMvc.perform(get(API_EXERCISES_URL)
                .with(testUserForRequest())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].name", is(firstExercise.getName())))
                .andExpect(jsonPath("$.[0].description", is(firstExercise.getDescription())))
                .andExpect(jsonPath("$.[1].name", is(secondExercise.getName())))
                .andExpect(jsonPath("$.[1].description", is(secondExercise.getDescription())));
    }

    @Test
    void testCreateWorkout() throws Exception {
        // given
        ExerciseDto exercise = createExercise("Squat", "Description");
        String workoutString = OBJECT_MAPPER.writeValueAsString(exercise);

        when(exerciseService.save(any())).thenReturn(exercise);

        // then
        this.mockMvc.perform(post(API_EXERCISES_URL)
                .with(testUserForRequest())
                .with(csrf())
                .content(workoutString)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", is(exercise.getName())))
                .andExpect(jsonPath("$.description", is(exercise.getDescription())));
    }

    @Test
    void testGetWorkoutShouldReturnWorkoutWithStatusCode200() throws Exception {
        // given
        ExerciseDto exercise = createExercise("Squat", "Description");

        when(exerciseService.getByName(anyString())).thenReturn(exercise);

        // then
        this.mockMvc.perform(get(API_EXERCISES_URL + "/" + exercise.getName())
                .with(testUserForRequest())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(exercise.getName())))
                .andExpect(jsonPath("$.description", is(exercise.getDescription())));
    }

    @Test
    void testGetWorkoutShouldReturnWithStatusCode404() throws Exception {
        // given
        ExerciseDto exercise = createExercise("Squat", "Description");

        when(exerciseService.getByName(anyString())).thenThrow(new ItemNotFoundException());

        // then
        this.mockMvc.perform(get(API_EXERCISES_URL + "/" + exercise.getName())
                .with(testUserForRequest())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    // TODO: Add test for rest of the exceptions

    private static SecurityMockMvcRequestPostProcessors.UserRequestPostProcessor testUserForRequest() {
        return user("testUser").password("testPassword");
    }

    private ExerciseDto createExercise(String name, String description) {
        return ExerciseDto.builder()
                .name(name)
                .description(description)
                .build();
    }
}