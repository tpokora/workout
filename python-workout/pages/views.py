from django.http import HttpResponse
from django.shortcuts import render

# Create your pages here.
from django.template import loader
from django.views import generic

from workouts.models import Exercise

class ExercisesListPageView(generic.ListView):
    template_name = "workouts/exercises.html"
    context_object_name = "exercises_list"

    def get_queryset(self):
        return Exercise.objects.all()