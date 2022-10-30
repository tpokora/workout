package org.tpokora.workout.workouts.persistance.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
@Table(name = WorkoutEntity.WORKOUT)
public class WorkoutEntity {

    public static final String WORKOUT = "workout";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    Long id;

    @Column(unique = true, nullable = false)
    String name;

    @Column
    String description;
}
