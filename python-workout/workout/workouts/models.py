from django.db import models

# Create your models here.


class Exercise(models.Model):
    name = models.CharField(max_length=50)
    description = models.CharField(max_length=500)
