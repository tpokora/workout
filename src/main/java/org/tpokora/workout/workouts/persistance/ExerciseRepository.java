package org.tpokora.workout.workouts.persistance;

import org.springframework.data.jpa.repository.JpaRepository;
import org.tpokora.workout.workouts.persistance.entity.ExerciseEntity;

import java.util.Optional;

public interface ExerciseRepository extends JpaRepository<ExerciseEntity, Integer> {

    Optional<ExerciseEntity> findByName(String name);
    void deleteByName(String name);
}
