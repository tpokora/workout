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
        return entityToDto(returnExerciseEntityByNameOrThrowException(name));
    }

    public ExerciseDto save(ExerciseDto exerciseToSave) {
        checkIfExerciseAlreadyExists(exerciseToSave);
        return entityToDto(exerciseRepository.save(dtoToEntity(exerciseToSave)));
    }

    @Transactional
    public void delete(String name) {
        returnExerciseEntityByNameOrThrowException(name);
        exerciseRepository.deleteByName(name);
    }

    @Transactional
    public ExerciseDto update(ExerciseDto oldExercise, ExerciseDto changedExercise) {
        checkIfExerciseAlreadyExists(changedExercise);
        ExerciseEntity oldExerciseEntity = returnExerciseEntityByNameOrThrowException(oldExercise.getName());
        oldExerciseEntity.setName(changedExercise.getName());
        oldExerciseEntity.setDescription(changedExercise.getDescription());
        return entityToDto(exerciseRepository.save(oldExerciseEntity));
    }

    private void checkIfExerciseAlreadyExists(ExerciseDto exerciseDto) {
        exerciseRepository.findByName(exerciseDto.getName()).ifPresent(s -> {
            throw new ItemAlreadyExistsException();
        });
    }

    private ExerciseEntity returnExerciseEntityByNameOrThrowException(String name) {
        return exerciseRepository.findByName(name)
                .orElseThrow(ItemNotFoundException::new);
    }

    private ExerciseDto entityToDto(ExerciseEntity exerciseEntity) {
        return EXERCISE_MAPPER.toDto(exerciseEntity);
    }

    private ExerciseEntity dtoToEntity(ExerciseDto exerciseDto) {
        return EXERCISE_MAPPER.toEntity(exerciseDto);
    }
}
