from rest_framework import serializers
from workouts.models import Exercise


class ExerciseSerializer(serializers.HyperlinkedModelSerializer):
    class Meta:
        model = Exercise
        fields = ['name', 'description']
