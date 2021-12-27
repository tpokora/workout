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
        response = client.get(f'/workouts/exercises/')

        self.assertEqual(response.status_code, 200)
        # TODO: check response content
