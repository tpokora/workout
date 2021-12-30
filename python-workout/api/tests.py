from django.test import TestCase
from rest_framework.test import (
    APIClient
)

# Create your tests here.
from workouts.models import Exercise, Workout, WorkoutSection, ExerciseSet


class TestExerciseAPIView(TestCase):
    def setUp(self) -> None:
        # Create Exercise
        self.first_exercise = \
            Exercise.objects.create(name="First test exercise", description="First test description")
        self.second_exercise = \
            Exercise.objects.create(name="Second test exercise", description="Second test description")

    def test_get_request(self):
        client = APIClient()
        response = client.get(f'/api/workouts/exercises/')

        self.assertEqual(response.status_code, 200)

        results = response.data['results']
        self.assertEqual(len(results), 2)
        results_first_exercise = response.data['results'][0]
        results_second_exercise = response.data['results'][1]

        self.assertResultExercise(self.first_exercise, results_first_exercise)
        self.assertResultExercise(self.second_exercise, results_second_exercise)

    def assertResultExercise(self, exercise, result_exercise):
        self.assertEqual(exercise.name, result_exercise['name'])
        self.assertEqual(exercise.description, result_exercise['description'])


class WorkoutDetailAPIView(TestCase):
    def setUp(self) -> None:
        self.squat = Exercise(name="Squat", description="Description")
        self.squat.save()

        self.workout = Workout(name="Workout")
        self.workout.save()
        self.workout_section_a = WorkoutSection(name="A", workout=self.workout)
        self.workout_section_a.save()
        self.exercise_set1_a = ExerciseSet(sets=5, reps=5, exercise=self.squat, workout_section=self.workout_section_a)
        self.exercise_set1_a.save()

    def test_get(self):
        client = APIClient()
        response = client.get(f'/api/workouts/{self.workout.pk}/')

        self.assertEqual(response.status_code, 200)
        expected_json_response = {'id': 1,
                                  'name': 'Workout',
                                  'workout_sections':
                                      [{'name': 'A',
                                        'exercise_sets': [
                                            {'sets': 5,
                                             'reps': 5,
                                             'exercise': {
                                                 'id': 1,
                                                 'name': 'Squat',
                                                 'description': 'Description'
                                             }}
                                        ]}]}
        self.assertEqual(response.data, expected_json_response)
