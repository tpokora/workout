"""workout URL Configuration

The `urlpatterns` list routes URLs to pages. For more information please see:
    https://docs.djangoproject.com/en/4.0/topics/http/urls/
Examples:
Function pages
    1. Add an import:  from my_app import pages
    2. Add a URL to urlpatterns:  path('', pages.home, name='home')
Class-based pages
    1. Add an import:  from other_app.pages import Home
    2. Add a URL to urlpatterns:  path('', Home.as_view(), name='home')
Including another URLconf
    1. Import the include() function: from django.urls import include, path
    2. Add a URL to urlpatterns:  path('blog/', include('blog.urls'))
"""
from django.contrib import admin
from django.urls import path

from api import views as api_views
from pages import views as pages_views

urlpatterns = [
    path('admin/', admin.site.urls),

    # Workout API
    path('api/workouts/', api_views.WorkoutAPIView.as_view(), name='workouts'),
    path('api/workouts/<int:pk>/', api_views.WorkoutDetailAPIView.as_view(), name='workout_details'),

    # Exercise API
    path('api/workouts/exercises/', api_views.ExerciseAPIView.as_view(), name='exercises'),
    path('pages/workouts/exercises/', pages_views.ExercisesListPageView.as_view(), name='exercises')
]
