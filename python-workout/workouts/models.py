from dataclasses import dataclass

from django.db import models

# Create your models here.


@dataclass(init=False)
class Exercise(models.Model):
    name = models.CharField(max_length=50)
    description = models.CharField(max_length=500)

    def __str__(self):
        return "{name: '%s', description: '%s'}" % (self.name, self.description)

    def __repr__(self):
        return self.__str__()


@dataclass(init=False)
class ExerciseSet(models.Model):
    sets = models.IntegerField()
    reps = models.IntegerField()
    exercise = models.OneToOneField(
        Exercise,
        on_delete=models.DO_NOTHING,
        primary_key=True
    )

    def __str__(self):
        return "{sets: %s, reps: %s, exercise: %s}" % (self.sets, self.reps, self.exercise.__str__())

    def __repr__(self):
        return self.__str__()
