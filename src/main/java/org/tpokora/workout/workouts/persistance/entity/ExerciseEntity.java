package org.tpokora.workout.workouts.persistance.entity;

import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = ExerciseEntity.EXERCISE)
public class ExerciseEntity {

    public static final String EXERCISE = "exercise";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    @Setter
    @Getter
    Long id;

    @Column(unique = true)
    @Getter
    @Setter
    String name;

    @Column
    @Getter
    @Setter
    String description;
}
