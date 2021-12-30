from rest_framework.generics import ListAPIView, RetrieveAPIView

# Create your pages here.
from workouts.models import Exercise, Workout
from api.serializers import ExerciseSerializer, WorkoutSerializer, WorkoutDetailSerializer


# Workout API
class WorkoutAPIView(ListAPIView):
    queryset = Workout.objects.all()
    serializer_class = WorkoutSerializer


class WorkoutDetailAPIView(RetrieveAPIView):
    queryset = Workout.objects.all()
    serializer_class = WorkoutDetailSerializer


# Exercise API
class ExerciseAPIView(ListAPIView):
    queryset = Exercise.objects.all()
    serializer_class = ExerciseSerializer
