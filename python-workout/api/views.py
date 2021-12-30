from rest_framework.generics import ListAPIView, RetrieveAPIView

# Create your pages here.
from workouts.models import Exercise, Workout
from api.serializers import ExerciseSerializer, WorkoutSerializer


class WorkoutView(ListAPIView):
    queryset = Workout.objects.all()
    serializer_class = WorkoutSerializer


class WorkoutDetailView(RetrieveAPIView):
    queryset = Workout.objects.all()
    serializer_class = WorkoutSerializer


class ExerciseView(ListAPIView):
    queryset = Exercise.objects.all()
    serializer_class = ExerciseSerializer
