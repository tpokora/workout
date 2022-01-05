from django.test import TestCase

# Create your tests here.
from workouts.models import Exercise, ExerciseSet, Workout, WorkoutSection


class ExerciseTest(TestCase):

    def test_model(self):
        # given
        exercise = Exercise.objects.create(name="Exercise", description="Description")
        expected_exercise = Exercise(name="Exercise", description="Description")
        expected_string = "Exercise{name: 'Exercise', description: 'Description'}"
        # then
        self.assertEqual(exercise.__str__(), expected_string)
        self.assertEqual(exercise.__str__(), expected_exercise.__str__())
        self.assertEqual(exercise.__repr__(), expected_exercise.__repr__())


class WorkoutTest(TestCase):
    def setUp(self) -> None:
        self.squat = Exercise.objects.create(name="Squat", description="")
        self.row = Exercise.objects.create(name="Row", description="")
        self.bench = Exercise.objects.create(name="Bench", description="")
        self.ohp = Exercise.objects.create(name="OHP", description="")
        self.deadlift = Exercise.objects.create(name="Deadlift", description="")

    def test_model(self):
        # given
        workout = Workout(name="Workout")
        workout.save()
        workout_section_a = WorkoutSection(name="A", workout=workout)
        workout_section_a.save()
        workout_section_b = WorkoutSection(name="B", workout=workout)
        workout_section_b.save()
        self.assertEqual(workout.workout_sections.count(), 2)

        exercise_set1_a = ExerciseSet(sets=5, reps=5, exercise=self.squat, workout_section=workout_section_a)
        exercise_set1_a.save()
        exercise_set2_a = ExerciseSet(sets=5, reps=5, exercise=self.bench, workout_section=workout_section_a)
        exercise_set2_a.save()
        exercise_set3_a = ExerciseSet(sets=5, reps=5, exercise=self.row, workout_section=workout_section_a)
        exercise_set3_a.save()

        exercise_set1_b = ExerciseSet(sets=5, reps=5, exercise=self.squat, workout_section=workout_section_b)
        exercise_set1_b.save()
        exercise_set2_b = ExerciseSet(sets=5, reps=5, exercise=self.ohp, workout_section=workout_section_b)
        exercise_set2_b.save()
        exercise_set3_b = ExerciseSet(sets=5, reps=5, exercise=self.deadlift, workout_section=workout_section_b)
        exercise_set3_b.save()

        # then
        self.assertEqual(workout_section_a.exercise_sets.count(), 3)
        self.assertEqual(workout_section_b.exercise_sets.count(), 3)

    def test_model_delete(self):
        # should delete workout, workout_sections, exercise_sets
        # exercise should still be in DB

        # given
        workout = Workout(name="Workout")
        workout.save()
        workout_section_a = WorkoutSection(name="A", workout=workout)
        workout_section_a.save()
        workout_section_b = WorkoutSection(name="B", workout=workout)
        workout_section_b.save()
        self.assertEqual(workout.workout_sections.count(), 2)

        exercise_set1_a = ExerciseSet(sets=5, reps=5, exercise=self.squat, workout_section=workout_section_a)
        exercise_set1_a.save()
        exercise_set2_a = ExerciseSet(sets=5, reps=5, exercise=self.bench, workout_section=workout_section_a)
        exercise_set2_a.save()
        exercise_set3_a = ExerciseSet(sets=5, reps=5, exercise=self.row, workout_section=workout_section_a)
        exercise_set3_a.save()

        exercise_set1_b = ExerciseSet(sets=5, reps=5, exercise=self.squat, workout_section=workout_section_b)
        exercise_set1_b.save()
        exercise_set2_b = ExerciseSet(sets=5, reps=5, exercise=self.ohp, workout_section=workout_section_b)
        exercise_set2_b.save()
        exercise_set3_b = ExerciseSet(sets=5, reps=5, exercise=self.deadlift, workout_section=workout_section_b)
        exercise_set3_b.save()

        self.assertEqual(workout_section_a.exercise_sets.count(), 3)
        self.assertEqual(workout_section_b.exercise_sets.count(), 3)

        # then
        workout.delete()
        self.assertEqual(Workout.objects.count(), 0)
        self.assertEqual(WorkoutSection.objects.count(), 0)
        self.assertEqual(ExerciseSet.objects.count(), 0)
        self.assertEqual(Exercise.objects.count(), 5)

