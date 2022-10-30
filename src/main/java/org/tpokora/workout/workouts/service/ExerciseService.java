package org.tpokora.workout.workouts.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.tpokora.workout.workouts.exceptions.ItemAlreadyExistsException;
import org.tpokora.workout.workouts.exceptions.ItemNotFoundException;
import org.tpokora.workout.workouts.mapper.ExerciseMapper;
import org.tpokora.workout.workouts.model.ExerciseDto;
import org.tpokora.workout.workouts.persistance.ExerciseRepository;
import org.tpokora.workout.workouts.persistance.entity.ExerciseEntity;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class ExerciseService {

    private static final ExerciseMapper EXERCISE_MAPPER = new ExerciseMapper();

    private final ExerciseRepository exerciseRepository;

    public List<ExerciseDto> getAll() {
        return exerciseRepository.findAll()
                .stream()
                .map(this::entityToDto)
                .collect(Collectors.toList());
    }

    public ExerciseDto getByName(String name) {
        return findExerciseByName(name);
    }

    public ExerciseDto save(ExerciseDto exerciseToSave) {
        checkIfExerciseAlreadyExists(exerciseToSave);
        return entityToDto(exerciseRepository.save(dtoToEntity(exerciseToSave)));
    }

    @Transactional
    public void delete(String name) {
        findExerciseByName(name);
        exerciseRepository.deleteByName(name);
    }

    @Transactional
    public ExerciseDto update(ExerciseDto oldExercise, ExerciseDto changedExercise) {
        checkIfExerciseAlreadyExists(changedExercise);
        return entityToDto(
                exerciseRepository.save(
                        EXERCISE_MAPPER.toEntity(
                                changedExercise,
                                findExerciseIdByName(oldExercise.getName()))));
    }

    private void checkIfExerciseAlreadyExists(ExerciseDto exerciseDto) {
        exerciseRepository.findByName(exerciseDto.getName()).ifPresent(s -> {
            throw new ItemAlreadyExistsException();
        });
    }

    private ExerciseDto findExerciseByName(String name) {
        return exerciseRepository.findByName(name)
                .map(EXERCISE_MAPPER::toDto)
                .orElseThrow(ItemNotFoundException::new);
    }

    private Long findExerciseIdByName(String name) {
        return exerciseRepository.findByName(name)
                .map(ExerciseEntity::getId)
                .orElseThrow(ItemNotFoundException::new);
    }


    private ExerciseDto entityToDto(ExerciseEntity exerciseEntity) {
        return EXERCISE_MAPPER.toDto(exerciseEntity);
    }

    private ExerciseEntity dtoToEntity(ExerciseDto exerciseDto) {
        return EXERCISE_MAPPER.toEntity(exerciseDto);
    }
}

