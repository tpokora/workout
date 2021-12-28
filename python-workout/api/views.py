from rest_framework import viewsets

# Create your pages here.
from workouts.models import Exercise
from api.serializers import ExerciseSerializer


class ExerciseViewSet(viewsets.ModelViewSet):
    queryset = Exercise.objects.all()
    serializer_class = ExerciseSerializer
