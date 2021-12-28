from django.http import HttpResponse
from django.shortcuts import render

# Create your pages here.


def index(request):
    return HttpResponse("Hello View");