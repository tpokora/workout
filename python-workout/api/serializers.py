from rest_framework import serializers
from workouts.models import Exercise, ExerciseSet, WorkoutSection, Workout


class WorkoutSerializer(serializers.ModelSerializer):
    class Meta:
        model = Workout
        fields = ['id', 'name']


class WorkoutSectionSerializer(serializers.ModelSerializer):
    class Meta:
        model = WorkoutSection
        fields = ['name']


class WorkoutDetailSerializer(serializers.ModelSerializer):
    workout_sections = WorkoutSectionSerializer(many=True, read_only=True)

    class Meta:
        model = Workout
        fields = ['name', 'workout_sections']


class ExerciseSetSerializer(serializers.ModelSerializer):
    class Meta:
        model = ExerciseSet
        fields = ['sets', 'reps', 'exercise']


class ExerciseSerializer(serializers.ModelSerializer):
    class Meta:
        model = Exercise
        fields = ['id', 'name', 'description']
