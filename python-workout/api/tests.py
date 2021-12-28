from django.test import TestCase
from rest_framework.test import (
    force_authenticate, APIRequestFactory, APIClient
)

# Create your tests here.
from workouts.models import Exercise


class TestExerciseViewSet(TestCase):
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
