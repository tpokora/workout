package org.tpokora.workout.workouts.persistance;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.tpokora.workout.workouts.persistance.entity.WorkoutEntity;

public interface WorkoutRepository extends PagingAndSortingRepository<WorkoutEntity, Integer> {

}
