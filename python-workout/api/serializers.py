from rest_framework import serializers
from workouts.models import Exercise, ExerciseSet, WorkoutSection, Workout


class WorkoutSerializer(serializers.ModelSerializer):
    class Meta:
        model = Workout
        fields = ['id', 'name']


class WorkoutSectionSerializer(serializers.ModelSerializer):
    class Meta:
        model = WorkoutSection
        fields = ['name', 'workout']

    def to_representation(self, instance):
        self.fields['workout'] = WorkoutSerializer(read_only=True)
        return super(WorkoutSectionSerializer, self).to_representation(instance)


class ExerciseSetSerializer(serializers.ModelSerializer):
    class Meta:
        model = ExerciseSet
        fields = ['sets', 'reps', 'exercise', 'workout_section']

    def to_representation(self, instance):
        self.fields['exercise'] = ExerciseSerializer(read_only=True)
        self.fields['workout_section'] = WorkoutSectionSerializer(read_only=True)
        return super(ExerciseSetSerializer, self).to_representation(instance)


class ExerciseSerializer(serializers.ModelSerializer):
    class Meta:
        model = Exercise
        fields = ['id', 'name', 'description']
