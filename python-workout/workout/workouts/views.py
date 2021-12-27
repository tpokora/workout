from rest_framework import viewsets

# Create your views here.
from workouts.models import Exercise
from workouts.serializers import ExerciseSerializer


class ExerciseViewSet(viewsets.ModelViewSet):
    queryset = Exercise.objects.all()
    serializer_class = ExerciseSerializer
