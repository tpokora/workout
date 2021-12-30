from django.db import models

# Create your models here.


class Exercise(models.Model):
    name = models.CharField(max_length=50)
    description = models.CharField(max_length=500)

    def __str__(self):
        return f"Exercise{{name: '{self.name}', description: '{self.description}'}}"

    def __repr__(self):
        return self.__str__()


class Workout(models.Model):
    name = models.CharField(max_length=50)

    def __str__(self):
        return f"{{name: '{self.name}'}}"

    def __repr__(self):
        return self.__str__()


class WorkoutSection(models.Model):
    name = models.CharField(max_length=50)
    workout = models.ForeignKey(Workout, on_delete=models.CASCADE)

    def __str__(self):
        return f"{{name: '{self.name}'}}"

    def __repr__(self):
        return self.__str__()


class ExerciseSet(models.Model):
    sets = models.IntegerField()
    reps = models.IntegerField()
    exercise = models.ForeignKey(Exercise, on_delete=models.DO_NOTHING)
    workout_section = models.ForeignKey(WorkoutSection, on_delete=models.CASCADE, default=None)

    def __str__(self):
        return f"{{sets: {self.sets}, reps: {self.reps}, exercise: {self.exercise.__str__()}}}"

    def __repr__(self):
        return self.__str__()
