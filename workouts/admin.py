from django.contrib import admin

# Register your models here.
from .models import Exercise, ExerciseSet, WorkoutSection, Workout

admin.site.register(Exercise)
admin.site.register(ExerciseSet)
admin.site.register(WorkoutSection)
admin.site.register(Workout)

