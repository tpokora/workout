from rest_framework import serializers
from workouts.models import Exercise, ExerciseSet, WorkoutSection, Workout


class ExerciseSerializer(serializers.ModelSerializer):
    class Meta:
        model = Exercise
        fields = ['id', 'name', 'description']


class ExerciseSetSerializer(serializers.ModelSerializer):
    exercise = ExerciseSerializer(read_only=True)

    class Meta:
        model = ExerciseSet
        fields = ['sets', 'reps', 'exercise']


class WorkoutSectionSerializer(serializers.ModelSerializer):
    exercise_sets = ExerciseSetSerializer(many=True, read_only=True)

    class Meta:
        model = WorkoutSection
        fields = ['name', 'exercise_sets']


class WorkoutSerializer(serializers.ModelSerializer):
    class Meta:
        model = Workout
        fields = ['id', 'name']


class WorkoutDetailSerializer(serializers.ModelSerializer):
    workout_sections = WorkoutSectionSerializer(many=True, read_only=True)

    class Meta:
        model = Workout
        fields = ['id', 'name', 'workout_sections']
