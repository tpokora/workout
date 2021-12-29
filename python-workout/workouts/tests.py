from django.test import TestCase

# Create your tests here.
from workouts.models import Exercise, ExerciseSet


class ExerciseTest(TestCase):

    def test_model(self):
        # given
        exercise = Exercise.objects.create(name="Exercise", description="Description")
        expected_exercise = Exercise(name="Exercise", description="Description")

        # then
        self.assertEqual(exercise, expected_exercise)
        self.assertEqual(exercise.__str__(), expected_exercise.__str__())
        self.assertEqual(exercise.__repr__(), expected_exercise.__repr__())
        self.assertTrue(exercise == expected_exercise)


class ExerciseSetTest(TestCase):

    def test_model(self):
        # given
        exercise = Exercise.objects.create(name="Exercise", description="Description")
        exercise_set = ExerciseSet.objects.create(sets=5, reps=5, exercise=exercise)
        expected_exercise = Exercise(name="Exercise", description="Description")
        expected_exercise_set = ExerciseSet(sets=5, reps=5, exercise=expected_exercise)

        # then
        self.assertEqual(exercise_set, expected_exercise_set)
        self.assertEqual(exercise_set.__str__(), expected_exercise_set.__str__())
        self.assertEqual(exercise_set.__repr__(), expected_exercise_set.__repr__())
        self.assertTrue(exercise_set == expected_exercise_set)
