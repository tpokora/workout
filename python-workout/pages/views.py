from django.http import HttpResponse
from django.shortcuts import render

# Create your pages here.
from django.template import loader

from workouts.models import Exercise


def exercises(request):
    exercises_list = Exercise.objects.all()
    template = loader.get_template("workouts/exercises.html")
    context = {
        'exercises_list': exercises_list
    }
    return HttpResponse(template.render(context, request))