package org.tpokora.workout.workouts.persistance.entity;

import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
@Table(name = ExerciseEntity.EXERCISE)
public class ExerciseEntity {

    public static final String EXERCISE = "exercise";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    Long id;

    @Column(unique = true, nullable = false)
    String name;

    @Column
    String description;
}
